/*
 * Copyright 2015 Livotov Labs Ltd.
 * Copyright 2009-2012 The 99 Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.livotov.labs.webskel.core.quartz.guice;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.quartz.*;
import org.quartz.spi.JobFactory;

import java.util.TimeZone;

import static com.google.inject.Scopes.SINGLETON;
import static com.google.inject.multibindings.Multibinder.newSetBinder;
import static java.util.TimeZone.getTimeZone;

/**
 * Quartz (http://www.quartz-scheduler.org/) Module as Google-Guice extension.
 */
public abstract class QuartzModule extends AbstractModule
{

    private Multibinder<JobListener> jobListeners;

    private Multibinder<TriggerListener> triggerListeners;

    private Multibinder<SchedulerListener> schedulerListeners;

    private SchedulerConfiguration schedulerConfiguration;

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void configure()
    {
        jobListeners = newSetBinder(binder(), JobListener.class);
        triggerListeners = newSetBinder(binder(), TriggerListener.class);
        schedulerListeners = newSetBinder(binder(), SchedulerListener.class);
        schedulerConfiguration = new SchedulerConfiguration();

        try
        {
            schedule();
            bind(JobFactory.class).to(InjectorJobFactory.class).in(SINGLETON);
            bind(Scheduler.class).toProvider(SchedulerProvider.class).asEagerSingleton();
            bind(SchedulerConfiguration.class).toInstance(schedulerConfiguration);
        } finally
        {
            jobListeners = null;
            triggerListeners = null;
            schedulerListeners = null;
            schedulerConfiguration = null;
        }
    }

    /**
     * Part of the EDSL builder language for configuring {@code Job}s.
     * Here is a typical example of scheduling {@code Job}s when creating your Guice injector:
     * <p/>
     * <pre>
     * Guice.createInjector(..., new QuartzModule() {
     *
     *     {@literal @}Override
     *     protected void schedule() {
     *       <b>scheduleJob(MyJobImpl.class).withCronExpression("0/2 * * * * ?");</b>
     *     }
     *
     * });
     * </pre>
     *
     * @see eu.livotov.labs.webskel.core.quartz.guice.JobSchedulerBuilder
     */
    protected abstract void schedule();

    /**
     * Allows to configure the scheduler.
     * <p/>
     * <pre>
     * Guice.createInjector(..., new QuartzModule() {
     *
     *     {@literal @}Override
     *     protected void schedule() {
     *       configureScheduler().withManualStart().withProperties(...);
     *     }
     *
     * });
     * </pre>
     *
     * @since 1.1
     */
    protected final SchedulerConfigurationBuilder configureScheduler()
    {
        return schedulerConfiguration;
    }

    /**
     * Add the {@code JobListener} binding.
     *
     * @param jobListenerType The {@code JobListener} class has to be bound
     */
    protected final void addJobListener(Class<? extends JobListener> jobListenerType)
    {
        doBind(jobListeners, jobListenerType);
    }

    /**
     * Add the {@code TriggerListener} binding.
     *
     * @param triggerListenerType The {@code TriggerListener} class has to be bound
     */
    protected final void addTriggerListener(Class<? extends TriggerListener> triggerListenerType)
    {
        doBind(triggerListeners, triggerListenerType);
    }

    /**
     * Add the {@code SchedulerListener} binding.
     *
     * @param schedulerListenerType The {@code SchedulerListener} class has to be bound
     */
    protected final void addSchedulerListener(Class<? extends SchedulerListener> schedulerListenerType)
    {
        doBind(schedulerListeners, schedulerListenerType);
    }

    /**
     * Allows {@code Job} scheduling, delegating Guice create the {@code Job} instance
     * and inject members.
     * <p/>
     * If given {@code Job} class is annotated with {@link Scheduled}, then {@code Job}
     * and related {@code Trigger} values will be extracted from it.
     *
     * @param jobClass The {@code Job} has to be scheduled
     * @return The {@code Job} builder
     */
    protected final eu.livotov.labs.webskel.core.quartz.guice.JobSchedulerBuilder scheduleJob(Class<? extends Job> jobClass)
    {
        try
        {
            eu.livotov.labs.webskel.core.quartz.guice.JobSchedulerBuilder builder = new eu.livotov.labs.webskel.core.quartz.guice.JobSchedulerBuilder(jobClass);

            if (jobClass.isAnnotationPresent(Scheduled.class))
            {
                Scheduled scheduled = jobClass.getAnnotation(Scheduled.class);

                builder
                        // job
                        .withJobName(scheduled.jobName())
                        .withJobGroup(scheduled.jobGroup())
                        .withRequestRecovery(scheduled.requestRecovery())
                        .withStoreDurably(scheduled.storeDurably())
                        // trigger
                        .withCronExpression(scheduled.cronExpression())
                        .withTriggerName(scheduled.triggerName());

                if (!Scheduled.DEFAULT.equals(scheduled.timeZoneId()))
                {
                    TimeZone timeZone = getTimeZone(scheduled.timeZoneId());
                    if (timeZone != null)
                    {
                        builder.withTimeZone(timeZone);
                    }
                }
            }

            requestInjection(builder);
            return builder;
        } catch (Throwable err)
        {
            err.printStackTrace();
            return null;
        }
    }

    /**
     * Utility method to respect the DRY principle.
     *
     * @param <T>
     * @param binder
     * @param type
     */
    protected final <T> void doBind(Multibinder<T> binder, Class<? extends T> type)
    {
        binder.addBinding().to(type);
    }

}
