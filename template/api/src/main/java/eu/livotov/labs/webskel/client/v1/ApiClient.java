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

import eu.livotov.labs.webskel.client.v1.model.ApiError;
import eu.livotov.labs.webskel.client.v1.resources.TestResource;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;

public class ApiClient
{

    private final static String http_cache_control_header_in = "public, max-age=60, max-stale=2419200";
    private final static String http_cache_control_header_out_online = "public, max-age=60";
    private final static String http_cache_control_header_out_offline = "public, only-if-cached, max-stale=2419200";

    private String endpoint = "http://localhost:8080/server/api/v1/";
    private Retrofit adapter;
    private Cache cache;
    private boolean debug = false;
    private TestResource testResource;


    public ApiClient()
    {
        rebuildAdapter();
    }

    public ApiClient(final boolean debuggable)
    {
        this.debug = debuggable;
        rebuildAdapter();
    }

    public ApiClient(final String enpoint)
    {
        this.endpoint = enpoint;
        rebuildAdapter();
    }

    public ApiClient(final String enpoint, final boolean debuggable)
    {
        this.endpoint = enpoint;
        this.debug = debuggable;
        rebuildAdapter();
    }

    /**
     * Provides folder to store responses cache in. Override this method to return your own location.
     *
     * @return local filesystem folder for long term cache. Folder must exist and be writable.
     */
    public File provideCacheFolder()
    {
        File file = new File(System.getProperty("user.home") + File.separator + ".cache");
        if (!file.exists())
        {
            file.mkdir();
        }

        return file;
    }

    /**
     * Provides internet/network connection status. Override this method to perform your own connection status checks.
     *
     * @return <code>true</code> if internet/network connection is active or <code>false</code> if we're offline
     */
    public boolean isNetworkConnected()
    {
        return true;
    }

    public TestResource getTestResource()
    {
        return testResource;
    }

    private void rebuildAdapter()
    {
        cache = new Cache(provideCacheFolder(), 100000);
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();

        if (debug)
        {
            httpClientBuilder.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        httpClientBuilder.addNetworkInterceptor(chain ->
                                                {
                                                    final Request request = chain.request();

                                                    if ("get".equalsIgnoreCase(request.method()))
                                                    {
                                                        Response originalResponse = chain.proceed(request);
                                                        return originalResponse.newBuilder().removeHeader("Access-Control-Allow-Origin").removeHeader("X-Cache").removeHeader("X-Cache-Hit").removeHeader("Cache-Control").removeHeader("Pragma").header("Cache-Control", http_cache_control_header_in).build();
                                                    } else
                                                    {
                                                        Response originalResponse = chain.proceed(request);
                                                        return originalResponse.newBuilder().build();
                                                    }
                                                });

        httpClientBuilder.cache(cache);
        httpClientBuilder.addInterceptor(chain -> appendHttpHeaders(chain));

        adapter = new Retrofit.Builder().baseUrl(endpoint).client(httpClientBuilder.build()).addConverterFactory(GsonConverterFactory.create()).build();
        testResource = adapter.create(TestResource.class);
    }

    private Response appendHttpHeaders(Interceptor.Chain chain) throws IOException
    {
        final Request request = chain.request();
        Request.Builder bulder = request.newBuilder();

        // Request cached-responses first
        if ("get".equalsIgnoreCase(request.method()))
        {
            if (isNetworkConnected())
            {
                bulder.addHeader("Cache-Control", http_cache_control_header_out_online).build();
            } else
            {
                bulder.addHeader("Cache-Control", http_cache_control_header_out_offline).build();
            }
        }

        // Add your custom request headers here, for instace - authorization, stats, etc...
        // bulder.addHeader("HeaderName, "HeaderValue);

        return chain.proceed(bulder.build());
    }

    public ApiError getError(retrofit2.Response<?> response)
    {
        retrofit2.Converter<okhttp3.ResponseBody, ApiError> converter = adapter.responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError error;

        try
        {
            error = converter.convert(response.errorBody());
        } catch (Throwable e)
        {
            error = new ApiError();
            error.setError(response.code());
            error.setMessage(response.message());
        }

        return error;
    }

}
