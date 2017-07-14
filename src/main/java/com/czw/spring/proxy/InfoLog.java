package com.czw.spring.proxy;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by zevi on 2017/7/14.
 */
public class InfoLog implements MethodBeforeAdvice, MethodInterceptor, AfterReturningAdvice {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        System.out.println("InfoLog.invoke invokeBefore");
        Object ret = methodInvocation.proceed();
        System.out.println("InfoLog.invoke invokeAfter");
        return ret;
    }

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("InfoLog.afterreturning");
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("InfoLog.before");
    }
}
