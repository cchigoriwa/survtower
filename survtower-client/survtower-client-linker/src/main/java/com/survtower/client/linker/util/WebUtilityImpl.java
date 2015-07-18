package com.survtower.client.linker.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Charles Chigoriwa
 */
public class WebUtilityImpl {

    private static HttpServletRequest request;

    public static String getBasePath() {
        request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return getUrlBase(request);
    }

    public static String getUrlBase(HttpServletRequest request) {

        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        return url;
    }
}
