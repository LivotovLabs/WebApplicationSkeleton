/*
 * Copyright 2015 Livotov Labs Ltd.
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

package eu.livotov.labs.webskel.client.v1;

import eu.livotov.labs.webskel.client.v1.model.LogResult;
import eu.livotov.labs.webskel.client.v1.resources.LogResource;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class ApiClient implements RequestInterceptor
{

    private String endpoint = "http://localhost:8080/server/api/v1";
    private RestAdapter adapter;
    private LogResource logResource;


    public ApiClient()
    {
        rebuildAdapter();
    }

    public ApiClient(final String enpoint)
    {
        this.endpoint = enpoint;
        rebuildAdapter();
    }

    public String getEndpoint()
    {
        return endpoint;
    }

    protected synchronized LogResource getLogResource()
    {
        if (logResource == null)
        {
            logResource = adapter.create(LogResource.class);
        }

        return logResource;
    }

    private void rebuildAdapter()
    {
        adapter = new RestAdapter.Builder().setEndpoint(endpoint).setRequestInterceptor(this).setLogLevel(RestAdapter.LogLevel.FULL).build();
        logResource = null;
    }

    public static void main(String[] args)
    {
        ApiClient api = new ApiClient();
        LogResult res = api.getLogResource().getLogs();
        System.out.println(res);
    }

    public void intercept(final RequestFacade request)
    {
        // Add REST requests headers here
        request.addHeader("name", "value");
    }

}
