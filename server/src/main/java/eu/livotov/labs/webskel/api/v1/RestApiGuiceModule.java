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

package eu.livotov.labs.webskel.api.v1;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import eu.livotov.labs.webskel.api.v1.resources.LogResource;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.inject.Singleton;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.util.HashMap;

public class RestApiGuiceModule extends ServletModule
{

    @Override
    protected void configureServlets()
    {
        bind(LogResource.class).in(Singleton.class);
        bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
        bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);

        HashMap<String, String> options = new HashMap<String, String>();
        options.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        serve("/api/v1/*").with(GuiceContainer.class, options);
    }
}