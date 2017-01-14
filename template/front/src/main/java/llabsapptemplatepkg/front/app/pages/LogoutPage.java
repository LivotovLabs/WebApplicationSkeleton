/*
 * Copyright (c) 2016. Livotov Labs Ltd.
 */

package llabsapptemplatepkg.front.app.pages;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 25/06/2016
 */
public class LogoutPage extends BasePage
{

    private static final long serialVersionUID = 1L;
    @Inject private Injector injector;

    @Override
    protected void onInitialize()
    {
        super.onInitialize();
        AuthenticatedWebSession.get().signOut();
        throw new RestartResponseAtInterceptPageException(StartPage.class);
    }
}
