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

import java.util.Properties;

/**
 * Configuration of scheduler.
 *
 * @since 1.1
 */
class SchedulerConfiguration implements SchedulerConfigurationBuilder
{

    private boolean manualStart = false;

    /**
     * @since 1.3
     */
    private Properties properties;

    public SchedulerConfigurationBuilder withManualStart()
    {
        manualStart = true;
        return this;
    }

    /**
     * @since 1.3
     */
    public SchedulerConfigurationBuilder withProperties(Properties properties)
    {
        this.properties = properties;
        return this;
    }

    boolean startManually()
    {
        return manualStart;
    }

    /**
     * @return
     * @since 1.3
     */
    Properties getProperties()
    {
        return properties;
    }

}
