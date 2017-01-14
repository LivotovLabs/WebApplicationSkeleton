/*
 * Copyright (c) 2016. Livotov Labs Ltd.
 */

package llabsapptemplatepkg.front.app;

import llabsapptemplatepkg.client.v1.ApiClient;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 03/07/2016
 */
public class BackendConnection
{

    public static ApiClient get()
    {
        final ApiClient client = new ApiClient();
        final AuthenticatedWebSession session = AuthenticatedWebSession.get();

        if (session != null && session instanceof AppSession)
        {
            final String token = ((AppSession) session).token;
            if (token != null && !token.isEmpty())
            {
                // set token to client
            }
        }

        return client;
    }

    public static ApiClient get(final String token)
    {
        final ApiClient client = get();
        // set token to client
        return client;
    }
}
