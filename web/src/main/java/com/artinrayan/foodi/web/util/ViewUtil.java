package com.artinrayan.foodi.web.util;

/**
 * Created by asus on 6/16/2017.
 */
public class ViewUtil {

    public enum Views
    {
        HOSTSUCCESS("host/hostRegistrationSuccess"),
        HOSTLIST("host/hostList"),
        ALLHOSTLIST("host/"),
        MANAGEHOSTATTACHMENT("attachment/manageHostAttachment"),
        HOSTREGISTRATION("host/hostRegistration"),
        ACCESSDENIED("error/accessDenied"),
        HOME("home"),
        LOGIN("auth/login"),
        USERLIST("user/userList"),
        USEREGISTRATIONSUCCESS("user/userRegistrationSuccess"),
        USERREGISTRATION("user/userRegistration"),
        HOST("host/host"),
        ERRORPAGE("error/error");



        private String viewName;

        Views(String viewName) {
            this.viewName = viewName;
        }

        public String getViewName() {
            return viewName;
        }
    }
}
