/*
 * Copyright (c) 2016. Livotov Labs Ltd.
 */

package com.knxcube.front.core.bus;

import com.google.inject.Singleton;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.BusConfiguration;
import net.engio.mbassy.bus.config.Feature;

import java.util.concurrent.TimeUnit;

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

    public void postDelayed(Object event, long delayMs)
    {
        transport.post(event).asynchronously(delayMs, TimeUnit.MILLISECONDS);
    }

    public void postSynchronously(Object event)
    {
        transport.post(event).now();
    }
}
