/*
 * Copyright 2015 Livotov Labs Ltd.
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

package eu.livotov.labs.webskel.core.quartz;

import eu.livotov.labs.webskel.core.quartz.guice.QuartzModule;
import eu.livotov.labs.webskel.core.quartz.jobs.SimpleEchoJob;


public class QuartzTimerGuiceModule extends QuartzModule
{

    @Override
    protected void schedule()
    {
//        addJobListener( com.acme.MyJobListener.class );
//        addTriggerListener( com.acme.MyTriggerListener.class );
//        addSchedulerListener( com.acme.MySchedulerListener.class );
        scheduleJob(SimpleEchoJob.class);
    }
}
