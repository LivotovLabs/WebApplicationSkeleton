/*
 * Copyright (c) 2016. Livotov Labs Ltd.
 */

package llabsapptemplatepkg.front.app;

import llabsapptemplatepkg.client.v1.ApiClient;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 25/06/2016
 */
public class AppSession extends AuthenticatedWebSession
{

    String token;

    public AppSession(Request request)
    {
        super(request);
    }

    @Override
    public boolean authenticate(String username, String password)
    {
        ApiClient client = BackendConnection.get();

        try
        {
            //todo: authenticate and return true on success or false on failure: BackendConnection.get().login(...);
            return false;
        } catch (Throwable e)
        {
            token = null;
            return false;
        }
    }

    public String getToken()
    {
        return token;
    }

    public void signOut()
    {
        try
        {
            // todo: logoff form the server: BackendConnection.get().logout();
        } catch (Throwable ignored)
        {
        }

        super.signOut();
    }

    @Override
    public Roles getRoles()
    {
        return null;
    }

//todo: this is the sample of how to get gravatar url
//    public static String getAvatarUrl()
//    {
//        final AppSession session = (AppSession) get();
//
//        if (session != null && session.profile != null)
//        {
//            return String.format("https://www.gravatar.com/avatar/%s?w=120", MD5.md5Hex(session.profile.getEmail().trim()));
//        } else
//        {
//            return "https://www.gravatar.com/avatar/xyz.png";
//        }
//    }


}