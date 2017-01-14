/*
 * Copyright 2017 Livotov Labs Ltd.
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

package llabsapptemplatepkg.server.core.amq;

import org.apache.qpid.proton.amqp.messaging.AmqpValue;
import org.apache.qpid.proton.message.Message;
import org.apache.qpid.proton.messenger.Messenger;

import java.io.IOException;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 08/01/2017
 */
public class AMQP10Producer
{

    private final Messenger messenger;

    public AMQP10Producer() throws IOException
    {
        this.messenger = Messenger.Factory.create();
        this.messenger.start();
    }

    /**
     *
     * @param broker broker complete uri like amqp://host:port/test-exchange-or-queue-name
     * @param data text payload
     */
    public void addToQueue(final String broker, final String routingKey, final String data)
    {
        Message msg = Message.Factory.create();
        msg.setAddress(broker);
        msg.setBody(new AmqpValue(data));

        if (routingKey!=null)
        {
            msg.setSubject(routingKey);
        }

        messenger.put(msg);
    }

    public void send()
    {
        messenger.send();
    }

    public void stop()
    {
        messenger.send();
        messenger.stop();
    }
}
