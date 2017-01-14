/*
 * Copyright (c) 2016. Livotov Labs Ltd.
 */

package com.knxcube.front.bootstrap;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.knxcube.front.core.bus.SystemEventBus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * (c) Livotov Labs Ltd. 2016
 * Date: 03/06/2016
 */
@WebServlet(value = "/bootstrapFrontService", loadOnStartup = 1)
public class BootstrapServlet extends HttpServlet
{

    @Inject
    SystemEventBus eventBus;

    @Override
    public void init() throws ServletException
    {
        Injector injector = (Injector) getServletContext().getAttribute(Injector.class.getName());
        injector.injectMembers(this);
        super.init();
    }
}
