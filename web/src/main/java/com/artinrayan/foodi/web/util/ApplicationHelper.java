package com.artinrayan.foodi.web.util;

import javax.servlet.ServletContext;

/**
 * Created by asus on 8/2/2017.
 */
public class ApplicationHelper {

    private static ServletContext servletContext;

    public static ServletContext getServletContext() {
        return servletContext;
    }

    public static void setServletContext(ServletContext servletContext) {
        ApplicationHelper.servletContext = servletContext;
    }
}
