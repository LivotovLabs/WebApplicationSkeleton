/*
 * Copyright (c) 2016. Livotov Labs Ltd.
 */

package llabsapptemplatepkg.front.app;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import java.util.HashMap;
import java.util.Map;

public class WebappsGuiceModule extends ServletModule
{

    @Override
    protected void configureServlets()
    {
        filter("/*").through(WicketFilter.class, createWicketFilterInitParams());
        bind(WebApplication.class).to(FrontApp.class);
        bind(WicketFilter.class).to(CustomWicketFilter.class).in(Scopes.SINGLETON);
    }

    @Singleton
    private static class CustomWicketFilter extends WicketFilter
    {

        @Inject
        private Provider<WebApplication> webApplicationProvider;

        @Override
        protected IWebApplicationFactory getApplicationFactory()
        {
            return new IWebApplicationFactory()
            {
                @Override
                public WebApplication createApplication(WicketFilter filter)
                {
                    return webApplicationProvider.get();
                }

                @Override
                public void destroy(WicketFilter filter)
                {
                }
            };
        }
    }

    private Map<String, String> createWicketFilterInitParams()
    {
        Map<String, String> wicketFilterParams = new HashMap<String, String>();
        wicketFilterParams.put(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        wicketFilterParams.put("applicationClassName", "llabsapptemplatepkg.front.app.FrontApp"); //todo: replace package name (and maybe class name) after skeleton refactoring
        return wicketFilterParams;
    }
}