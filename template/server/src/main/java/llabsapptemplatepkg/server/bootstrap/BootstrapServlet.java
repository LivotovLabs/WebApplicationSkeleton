/*
 * Copyright 2016 Livotov Labs Ltd.
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

package llabsapptemplatepkg.server.bootstrap;

import com.google.inject.Injector;
import llabsapptemplatepkg.server.core.amq.AMQP10Consumer;
import org.apache.qpid.proton.message.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 14/08/2016
 */
@WebServlet(value = "/bootstrapService", loadOnStartup = 1)
public class BootstrapServlet extends HttpServlet implements AMQP10Consumer.MessageCallback
{

    @Override
    public void init() throws ServletException
    {
        Injector injector = (Injector) getServletContext().getAttribute(Injector.class.getName());
        injector.injectMembers(this);
        super.init();

        try
        {
            new AMQP10Consumer(this).subscribe("amqp://127.0.0.1:5672/xxx");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public boolean onMessage(final AMQP10Consumer consumer, final Message message)
    {
        System.err.println("AMQP Message: " + message.getBody().toString());
        return true;
    }
}