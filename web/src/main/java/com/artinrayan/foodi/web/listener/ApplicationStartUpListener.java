package com.artinrayan.foodi.web.listener;

import com.artinrayan.foodi.web.util.ApplicationHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by asus on 6/8/2017.
 */
@WebListener()
public class ApplicationStartUpListener implements ServletContextListener {



//    @Resource
//    private CacheManager cacheManager;

    @Override
    public void contextInitialized(ServletContextEvent event) {
//        CacheManager cacheManager = WebApplicationContextUtils.getRequiredWebApplicationContext(
//                event.getServletContext()).getBean(CacheManager.class);
//        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//        try {
//            cacheManager.();
//        } catch (Exception e) {
//            // rethrow as a runtime exception
//            throw new IllegalStateException(e);
//        }
        System.out.println("---- initialize servlet context -----");
        ApplicationHelper.setServletContext(event.getServletContext());
//        SystemCache.getInstance();
        // add initialization code here
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("---- destroying servlet context -----");
        // clean up resources
    }


}
