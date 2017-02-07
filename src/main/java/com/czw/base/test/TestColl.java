package com.czw.base.test;

import com.czw.beans.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZeviChen , 2016/11/30 14:52
 */
public class TestColl {
    public static void main(String[] args) throws InterruptedException {
        tlist();
    }

    public static void tlist() throws InterruptedException {
        List<User> ul = new ArrayList<>();
        User u = new User();
        u.setName("张三");
        u.setId(1);
        ul.add(u);
        User u1 = new User();
        u.setName("李四");
        u.setId(2);
        ul.add(u1);


        for(int i = 0;i<ul.size();i++){
            User user =  ul.get(i);
            user.setId(user.getId()+10);
            ul.add(i,user);
        }


        for(User utmp1:ul){
            System.out.println(utmp1.getId()+" , "+utmp1.getName());
        }

    }
}
