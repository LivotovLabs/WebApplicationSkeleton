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

package eu.livotov.labs.webskel.core.bus;

import com.google.inject.Singleton;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.BusConfiguration;
import net.engio.mbassy.bus.config.Feature;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 03/06/2016
 */

@Singleton
public class SystemEventBus
{

    private MBassador transport;

    public SystemEventBus()
    {
        super();

        new BusConfiguration()
                .addFeature(Feature.SyncPubSub.Default())
                .addFeature(Feature.AsynchronousHandlerInvocation.Default())
                .addFeature(Feature.AsynchronousMessageDispatch.Default());

        transport = new MBassador();
    }

    public void subscribe(Object subscriber)
    {
        transport.subscribe(subscriber);
    }

    public void unsubscribe(Object subscriber)
    {
        transport.unsubscribe(subscriber);
    }

    public void post(Object event)
    {
        transport.post(event).asynchronously();
    }

    public void postDelayed(Object event)
    {
        new Thread(() ->
                   {
                       try
                       {
                           Thread.sleep(3000L);
                       } catch (InterruptedException e)
                       {
                       }

                       transport.post(event).now();
                   }).start();
    }

    public void postSynchronously(Object event)
    {
        transport.post(event).now();
    }
}
