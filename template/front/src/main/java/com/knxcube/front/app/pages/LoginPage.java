/*
 * Copyright (c) 2016. Livotov Labs Ltd.
 */

package com.knxcube.front.app.pages;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.knxcube.front.app.panels.CopyrightFooter;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 25/06/2016
 */
public class LoginPage extends BasePage
{

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private boolean rememberme;

    @Inject private Injector injector;

    @Override
    protected void onInitialize()
    {
        super.onInitialize();

        StatelessForm form = new StatelessForm("loginForm")
        {
            @Override
            protected void onSubmit()
            {
                if (Strings.isEmpty(username))
                {
                    return;
                }

                boolean authResult = AuthenticatedWebSession.get().signIn(username, password);

                if (authResult)
                {
                    continueToOriginalDestination();
                } else
                {
                    error(getString("invalid.login"));
                }
            }
        };

        form.setDefaultModel(new CompoundPropertyModel(this));

        form.add(new EmailTextField("username"));
        form.add(new PasswordTextField("password"));
        form.add(new CheckBox("rememberme"));

        add(form);
        add(new NotificationPanel("errorMessage"));
        add(new CopyrightFooter("footer"));
    }
}
