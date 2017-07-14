package com.czw.spring.proxy;

/**
 * Created by zevi on 2017/7/14.
 */
public class InfoServiceImpl implements IUserInfoService {

    @Override
    public String getUsername(String id) {
        System.out.println("InfoServiceImpl.getUsername  id=" + id);
        return id + ":zhangsan";
    }
}
