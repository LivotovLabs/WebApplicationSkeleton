/*
 * Copyright (c) 2016. Livotov Labs Ltd.
 */

package llabsapptemplatepkg.front.app;

import com.google.inject.Inject;
import com.google.inject.Injector;
import llabsapptemplatepkg.front.app.pages.LoginPage;
import llabsapptemplatepkg.front.app.pages.LogoutPage;
import llabsapptemplatepkg.front.app.pages.StartPage;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 25/06/2016
 */
public class FrontApp extends AuthenticatedWebApplication
{

    private final Injector injector;

    @Inject
    public FrontApp(Injector injector)
    {
        this.injector = injector;
    }

    @Override
    public Class<StartPage> getHomePage()
    {
        return StartPage.class;
    }

    @Override
    public void init()
    {
        super.init();
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, injector));

        installBootstrap();
        mountPages();

        // webjars usage
        // WicketWebjars.install(this);
        //
        // <link rel='stylesheet' href='/webjars/jquery/1.11.0/jquery.js'>
        // or
        //
        // @Override
        // public void renderHead(IHeaderResponse response)
        // {
        //      super.renderHead(response);
        //      response.render(JavaScriptHeaderItem.forReference(new WebjarsJavaScriptResourceReference("jquery/1.11.0/jquery.js")));
        // }
        //
        // NB - replace version 1.11.0 to "current" to always get a most recent one
    }

    private void installBootstrap()
    {
        final IBootstrapSettings settings = new BootstrapSettings();
        settings.useCdnResources(true);
        settings.setAutoAppendResources(true);
        Bootstrap.install(this, settings);
    }

    private void mountPages()
    {
        mountPage("/login", LoginPage.class);
        mountPage("/logout", LogoutPage.class);
    }

    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass()
    {
        return AppSession.class;
    }

    protected Class<? extends WebPage> getSignInPageClass()
    {
        return LoginPage.class;
    }
}
