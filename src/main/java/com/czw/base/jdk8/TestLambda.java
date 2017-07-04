package com.czw.base.jdk8;

import org.apache.woden.wsdl20.Interface;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author ZeviChen , 2017/6/6 11:07
 */
public class TestLambda {

    @Test
    public void test1() {
        System.out.println("add: " + sum(() -> 5));
        System.out.println("--------------------------------");
        System.out.println("add: " + sum(() -> {
            return 4;
        }));
        System.out.println("--------------------------------");

        a = 4;
        b = 2;
        System.out.println("subtract: " + diff((a, b) -> a - b));
        System.out.println("--------------------------------");

        s = "hello";
        print((s) -> System.out.println("print: " + s));
        System.out.println("--------------------------------");

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        SimpleRun run = (l) -> l.toString();
        System.out.println(run.run(list));
        System.out.println("--------------------------------");

//        println(System::println);
    }

//    public <T> void println(Consumer<? super T> consumer) {
//        if (value != null)
//            consumer.accept(value);
//    }


    String s;

    public void print(SimplePrint sp) {
        sp.print(s);
    }

    public int sum(SimpleAdd cbf) {
        return cbf.add();
    }

    int a, b;

    public int diff(SimpleSubtract ss) {
        return ss.subtract(a, b);
    }

}

interface SimpleAdd {
    int add();
}

interface SimpleSubtract {
    int subtract(int a, int b);
}

interface SimplePrint {
    void print(String s);
}

interface SimpleRun {
    Object run(List list);
}
