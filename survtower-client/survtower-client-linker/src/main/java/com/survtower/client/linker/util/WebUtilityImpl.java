package com.survtower.client.linker.util;

import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Charles Chigoriwa
 */
public class WebUtilityImpl {

    @Autowired
    private static HttpServletRequest request;

    public static String getBasePath() throws MalformedURLException {

        return getUrlBase(request);
    }

    public static String getUrlBase(HttpServletRequest request)
            throws MalformedURLException {
        URL requestUrl = new URL(request.getRequestURL().toString());
        String portString
                = requestUrl.getPort() == -1 ? "" : ":" + requestUrl.getPort();
        return requestUrl.getProtocol()
                + "://" + requestUrl.getHost() + portString + "/";
    }

    public static void main(String[] args) throws MalformedURLException {
        System.out.println(getBasePath());;
    }
}
