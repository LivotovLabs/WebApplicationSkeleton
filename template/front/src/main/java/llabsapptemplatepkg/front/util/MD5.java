/*
 * Copyright (c) 2016. Livotov Labs Ltd.
 */

package llabsapptemplatepkg.front.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 04/07/2016
 */
public class MD5
{

    public static String hex(byte[] array)
    {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < array.length; ++i)
        {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }

        return sb.toString();
    }

    public static String md5Hex(String message)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e)
        {
        } catch (UnsupportedEncodingException e)
        {
        }

        return null;
    }
}
