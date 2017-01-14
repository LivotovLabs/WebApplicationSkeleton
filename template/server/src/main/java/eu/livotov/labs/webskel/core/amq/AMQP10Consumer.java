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

package eu.livotov.labs.webskel.core.amq;

import org.apache.qpid.proton.TimeoutException;
import org.apache.qpid.proton.message.Message;
import org.apache.qpid.proton.messenger.Messenger;
import org.apache.qpid.proton.messenger.Tracker;

import java.io.IOException;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 08/01/2017
 */
public class AMQP10Consumer
{

    private Messenger messenger;
    private MessageCallback callback;
    private volatile boolean done = false;
    private Thread listenerThread;

    public AMQP10Consumer(MessageCallback callback) throws IOException
    {
        this.callback = callback;
        this.messenger = Messenger.Factory.create();
        this.messenger.start();
    }

    private void listen()
    {
        listenerThread = new Thread(() ->
                                    {
                                        while (!done && messenger != null)
                                        {
                                            try
                                            {
                                                messenger.recv(1);
                                                while (messenger.incoming() > 0)
                                                {
                                                    if (callback != null)
                                                    {
                                                        Message msg = messenger.get();
                                                        Tracker tracker = messenger.incomingTracker();

                                                        try
                                                        {
                                                            if (callback.onMessage(AMQP10Consumer.this, msg))
                                                            {
                                                                messenger.accept(tracker, 0);
                                                            } else
                                                            {
                                                                messenger.reject(tracker, 0);
                                                            }
                                                        } catch (Throwable err)
                                                        {
                                                            err.printStackTrace();
                                                            messenger.reject(tracker, 0);
                                                        }

                                                    }
                                                }
                                            } catch (TimeoutException tmo)
                                            {
                                            } catch (Throwable err)
                                            {
                                                err.printStackTrace();
                                                AMQP10Consumer.this.stop();
                                            }
                                        }
                                    });

        listenerThread.start();
    }

    /**
     * Subscribes to a queue or exchange
     *
     * @param broker broker complete uri like amqp://host:port/test-exchange-or-queue-name
     */
    public synchronized void subscribe(final String broker)
    {
        messenger.subscribe(broker);

        if (listenerThread == null)
        {
            listen();
        }
    }

    public void send()
    {
        messenger.send();
    }

    public void stop()
    {
        if (messenger != null)
        {
            messenger.stop();
            messenger = null;
        }

        callback = null;
        done = true;
    }

    public interface MessageCallback
    {

        boolean onMessage(AMQP10Consumer consumer, Message message);
    }
}
