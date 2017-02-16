package com.czw.spring.context;

/**
 * @author ZeviChen , 2017/2/16 0016 上午 11:39
 */
public class FSContext extends AbstractFSContext {

    public FSContext(String configPath){
        resolvePath(configPath);
        refresh();
    }

    @Override
    public Object getBean(String name) {
        return null;
    }
}
