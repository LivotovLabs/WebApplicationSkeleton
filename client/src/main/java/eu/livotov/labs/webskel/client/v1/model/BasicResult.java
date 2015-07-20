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

import java.io.Serializable;

public class BasicResult implements Serializable
{

    int error;
    String message;

    public BasicResult()
    {
    }

    public static <T extends BasicResult> T forbiddenResult(T r)
    {
        r.setError(403);
        r.setMessage("Requested operation is forbidden due to lack of privileges.");
        return r;
    }

    public static <T extends BasicResult> T forbiddenResult(T r, final String msg)
    {
        r.setError(403);
        r.setMessage("Forbidden: " + msg);
        return r;
    }

    public static <T extends BasicResult> T notFoundResult(T r, final String id)
    {
        r.setError(404);
        r.setMessage("Requested object or records was not found: " + id);
        return r;
    }

    public static <T extends BasicResult> T notAuthenticatedResult(T r)
    {
        r.setError(401);
        r.setMessage("Requested operation requires authentication or your current session is already expired or not valid.");
        return r;
    }

    public static <T extends BasicResult> T exceptionResult(T r, final Throwable err)
    {
        r.setError(500);
        r.setMessage("Internal Server Error: " + err.getMessage());
        return r;
    }

    public static <T extends BasicResult> T authenticationFailed(T r)
    {
        r.setError(401);
        r.setMessage("Invalid username/password or token.");
        return r;
    }

    public static <T extends BasicResult> T unprocessable(T r)
    {
        r.setError(422);
        r.setMessage("Requested operation is unprocessable for current conditions/environments/case");
        return r;
    }

    public static <T extends BasicResult> T unprocessable(T r, final String msg)
    {
        r.setError(422);
        r.setMessage("Requested operation is unprocessable: " + msg);
        return r;
    }

    public int getError()
    {
        return error;
    }

    public BasicResult setError(final int error)
    {
        this.error = error;
        return this;
    }

    public String getMessage()
    {
        return message;
    }

    public BasicResult setMessage(final String message)
    {
        this.message = message;
        return this;
    }

    public boolean isSuccessfull()
    {
        return error == 0;
    }
}
