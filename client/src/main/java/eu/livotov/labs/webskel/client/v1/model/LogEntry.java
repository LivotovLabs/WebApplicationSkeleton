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

package eu.livotov.labs.webskel.client.v1.model;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 21/07/2015
 */
public class LogEntry
{

    long date;
    String message;

    public LogEntry(final long date, final String message)
    {
        this.date = date;
        this.message = message;
    }

    public long getDate()
    {
        return date;
    }

    public void setDate(final long date)
    {
        this.date = date;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(final String message)
    {
        this.message = message;
    }

    public String toString()
    {
        return "LogEntry{" +
                       "date=" + date +
                       ", message='" + message + '\'' +
                       '}';
    }
}
