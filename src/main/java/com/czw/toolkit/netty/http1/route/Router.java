package com.czw.toolkit.netty.http1.route;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZeviChen , 2017/7/5 15:53
 */
public class Router {

    private final static RouterMapper mapping = RouterMapper.INSTANCE;

    public static RouterMapper register(String path, Controller controller) {
        return mapping.add(path, controller);
    }

    public static Controller getControl(String path) {
        return mapping.get(path);
    }

    public static RouterMapper remove(String path) {
        return mapping.remove(path);
    }


    enum RouterMapper {
        INSTANCE;

        private Map<String, Controller> mapping;

        RouterMapper() {
            mapping = new HashMap<>();
        }

        public RouterMapper add(String path, Controller controller) {
            mapping.put(path, controller);
            return this;
        }

        public RouterMapper remove(String path) {
            mapping.remove(path);
            return this;
        }

        public Controller get(String path) {
            return mapping.get(path);
        }

    }

}
