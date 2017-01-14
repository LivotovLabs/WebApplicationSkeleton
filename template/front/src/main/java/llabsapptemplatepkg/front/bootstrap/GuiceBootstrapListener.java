/*
 * Copyright (c) 2016. Livotov Labs Ltd.
 */

package llabsapptemplatepkg.front.bootstrap;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import llabsapptemplatepkg.front.app.WebappsGuiceModule;

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
                install(new WebappsGuiceModule());
            }
        });
    }
}
