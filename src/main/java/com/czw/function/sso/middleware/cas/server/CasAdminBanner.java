package com.czw.function.sso.middleware.cas.server;


import org.apereo.cas.util.spring.boot.AbstractCasBanner;

public class CasAdminBanner extends AbstractCasBanner {
    @Override
    protected String getTitle() {
        return "CAS Admin Server";
    }
}