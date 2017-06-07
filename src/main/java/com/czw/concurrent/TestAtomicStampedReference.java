package com.czw.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS:内存中存在v,提供旧值a和新值b，如果v和a相同,就把v修改为b.
 * CAS中存在的问题,v可能在修改前被另外一个操作从a修改为c在修改为a过.所以
 * ABA就是解决这种问题问题而存在的,记录v的修改状态,确保在另一个操作修改过这个值,
 * 让其他操作知道这个值曾经被改变过.
 *
 * CAS（Central Authentication Service）单点登录解决方案,属于Web SSO
 * 1、开源的、多协议的SSO解决方案；Protocols：Custom Protocol、CAS、OAuth、OpenID、RESTful API、SAML1.1、SAML2.0等
 * 2、支持多种认证机制：Active Directory、JAAS、JDBC、LDAP、X.509 Certificates等
 * 3、安全策略：使用票据（Ticket）来实现支持的认证协议
 * 4、支持授权：可以决定哪些服务可以请求和验证服务票据（Service Ticket）
 * 5、提供高可用性：通过把认证过的状态数据存储在TicketRegistry组件中，这些组件有很多支持分布式环境的实现，
 *    如：BerkleyDB、Default 、EhcacheTicketRegistry、JDBCTicketRegistry、JBOSS TreeCache、JpaTicketRegistry、
 *    MemcacheTicketRegistry等
 * 6、支持多种客户端： Java、 .Net、 PHP、 Perl、 Apache, uPortal等
 *
 * @author ZeviChen , 2017/6/7 16:05
 */
public class TestAtomicStampedReference {

    private static AtomicInteger atomicInt = new AtomicInteger(100);
    private static AtomicStampedReference atomicStampedRef = new AtomicStampedReference(100, 0);

    public static void main(String[] args) throws InterruptedException {
        atomicInt.compareAndSet(100, 101);
        atomicInt.compareAndSet(101, 100);
        boolean c3 = atomicInt.compareAndSet(100, 101);
        System.out.println(c3); // true

        System.out.println("-------------------------------");

        int stamp = atomicStampedRef.getStamp();
        atomicStampedRef.compareAndSet(100, 101,
                atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
        atomicStampedRef.compareAndSet(101, 100,
                atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);

        c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
        System.out.println(c3); // false

    }

}
