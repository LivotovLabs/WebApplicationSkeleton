/*
 * Copyright 2016 Livotov Labs Ltd.
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

package eu.livotov.labs.webskel.bootstrap;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import eu.livotov.labs.webskel.api.v1.RestApiGuiceModule;
import eu.livotov.labs.webskel.app.WebappsGuiceModule;
import eu.livotov.labs.webskel.core.quartz.QuartzTimerGuiceModule;

import javax.servlet.annotation.WebListener;

@WebListener
public class GuiceBootstrapListener extends GuiceServletContextListener
{

    @Override
    protected Injector getInjector()
    {
        return Guice.createInjector(new ServletModule()
        {
            @Override
            protected void configureServlets()
            {
                install(new JpaPersistModule("webskelJpaUnit"));
                filter("/*").through(PersistFilter.class);
                install(new QuartzTimerGuiceModule());
                install(new RestApiGuiceModule());
                install(new WebappsGuiceModule());
            }
        });
    }
}
