package com.czw.base.colls.tcollection;

import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author ZeviChen , 2017/6/29 09:37
 */
public class TestMap {

    @Test
    public void linkedHashMap() {
        LinkedHashMap lhm = new LinkedHashMap();
        Hashtable hashtable = new Hashtable();
//        TreeMap<String, String> treeMap = new TreeMap();
//        treeMap.put("e", "aa");
//        treeMap.put("b", "bb");
//        treeMap.put("e", "aa1");
//        treeMap.put("c", "cc");
//        treeMap.put("c", null);
//        treeMap.put("d", null);
//        treeMap.get("c");
//        Set<Map.Entry<String, String>> entry = treeMap.entrySet();
//        for (Map.Entry e : entry) {
//            System.out.println(e.getKey() + " : " + e.getValue());
//        }


//        Map<String,String> weak = new WeakHashMap<>();
//        Map<String,String> map = new HashMap<>();
//        String a = "aaa";
//        String b = "bbb";
//        map.put(a,"a");
//        map.put(b,"b");
//        a = null;
//        b = null;
//        System.gc();
//        weak.put(a,"aa");
//        weak.put(b,"bb");
//        Set<String> s = weak.keySet();
//        for(String ss:s){
//            System.out.println(ss+" : "+weak.get(ss));
//        }
//
//        System.out.println(weak.get(b));
//        String a = new String("a");
//        String b = new String("b");
//        Map weakmap = new WeakHashMap();
//        Map map = new HashMap();
//        map.put(a, "aaa");
//        map.put(b, "bbb");
//
//
//        weakmap.put(a, "aaa");
//        weakmap.put(b, "bbb");
//
//        map.remove(a);
//
//        a = null;
//        b = null;
//
//        System.gc();
//        Iterator i = map.entrySet().iterator();
//        while (i.hasNext()) {
//            Map.Entry en = (Map.Entry) i.next();
//            System.out.println("map:" + en.getKey() + ":" + en.getValue());
//        }
//
//        Iterator j = weakmap.entrySet().iterator();
//        while (j.hasNext()) {
//            Map.Entry en = (Map.Entry) j.next();
//            System.out.println("weakmap:" + en.getKey() + ":" + en.getValue());
//
//        }


//        Map identityMap = new IdentityHashMap();
//        identityMap.put("a", 1);
//        identityMap.put(new String("a"), 2);
//        identityMap.put("a", 3);
//        System.out.println("Identity Map KeySet Size :: " +  identityMap.keySet().size());


//        EnumMap<Course, String> map = new EnumMap<>(Course.class);
//        map.put(Course.ONE, "语文");
//        map.put(Course.ONE, "政治");
//        map.put(Course.TWO, "数学");
//        map.put(Course.THREE, "英语");
//        for (Map.Entry<Course, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }


        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();

    }

    enum Course {
        ONE, TWO, THREE
    }


}
