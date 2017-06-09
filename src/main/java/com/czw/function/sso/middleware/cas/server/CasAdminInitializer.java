package com.czw.function.sso.middleware.cas.server;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class CasAdminInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder
                .sources(CASServer.class)
                .banner(new CasAdminBanner())
                .logStartupInfo(true);
    }
}