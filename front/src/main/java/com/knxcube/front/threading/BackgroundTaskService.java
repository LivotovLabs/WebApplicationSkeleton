/*
 * Copyright (c) 2016. Livotov Labs Ltd.
 */

package com.knxcube.front.threading;

import com.google.inject.Singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 03/06/2016
 */
@Singleton
public class BackgroundTaskService
{

    private ExecutorService service;

    public BackgroundTaskService()
    {
        service = Executors.newFixedThreadPool(10);
    }

    public void execute(Runnable task)
    {
        service.execute(task);
    }
}
