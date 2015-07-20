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

import com.google.inject.Injector;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

import javax.inject.Inject;

/**
 * A {@code JobFactory} implementation that delegates Guice creating {@code Job} instances.
 */
final class InjectorJobFactory implements JobFactory
{

    /**
     * The delegated {@link Injector}.
     */
    @Inject
    private Injector injector;

    /**
     * Set the delegated {@link Injector}.
     *
     * @param injector The delegated {@link Injector}
     */
    public void setInjector(Injector injector)
    {
        this.injector = injector;
    }

    /**
     * {@inheritDoc}
     */
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler)
            throws SchedulerException
    {
        Class<? extends Job> jobClass = bundle.getJobDetail().getJobClass();
        return this.injector.getInstance(jobClass);
    }

}
