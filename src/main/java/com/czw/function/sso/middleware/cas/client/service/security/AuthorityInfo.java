package com.czw.function.sso.middleware.cas.client.service.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 加载当前用户的权限信息
 *
 * @author ZeviChen , 2017/6/9 09:37
 */
public class AuthorityInfo implements GrantedAuthority {
    private static final long serialVersionUID = -175781100474818800L;

    /**
     * 权限CODE
     */
    private String authority;

    public AuthorityInfo(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}  