/*
 * Copyright (c) 2016. Livotov Labs Ltd.
 */

package llabsapptemplatepkg.front.app.pages;

import llabsapptemplatepkg.front.app.FrontApp;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCDNCSSReference;
import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.PriorityHeaderItem;
import org.apache.wicket.markup.html.WebPage;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 25/06/2016
 */
public abstract class AuthenticatedBasePage extends WebPage
{

    @Override
    protected void onConfigure()
    {
        super.onConfigure();
        FrontApp app = (FrontApp) Application.get();
        if (!AuthenticatedWebSession.get().isSignedIn())
        {
            app.restartResponseAtSignInPage();
        }
    }

    @Override
    public void renderHead(IHeaderResponse response)
    {
        super.renderHead(response);
        response.render(new PriorityHeaderItem(CssHeaderItem.forReference(FontAwesomeCDNCSSReference.instance())));
    }

}
