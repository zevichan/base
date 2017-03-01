package com.czw.spring.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ZeviChen , 2017/2/16 0016 上午 11:40
 */
public abstract class AbstractFSContext implements BeanFactory{

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private long startupDate;

    private String configLocation;

    private DefBeanFactory beanFactory;

    //并发锁
    private final Object beanFactoryMonitor = new Object();

    private final Object activeMonitor = new Object();
    private boolean active = false;

    //这个方法在AbstractApplicationContext中抽象
    protected void refresh(){
        prepareRefresh();

        //初始化DefBeanFactory并得到一个可以配置的ConfigureListableBeanFactory ,implements关系
        //refreshBeanFactory()加载xml配置文件，放入集合中
        beanFactory = obtainFreshBeanFactory();

        //设置beanfactory加载器
        //设置xml的解析器
        //设置可以加载实现了spring提供的相关XXAware接口的bean的控制器
        //自定义织入器，织入方式有：编译期织入、类加载期织入和运行期织入
        //设置系统环境变量
        //说明：对不同的spring.xml加载器会定制不同的prepareBeanFactory(xx),例如FileSystemXmlApplicationContext,ClassPathXmlApplicationContext
        prepareBeanFactory(beanFactory);

        //beanFactory初始化的后置处理
        postProcessBeanFactory(beanFactory);

        invokeBeanFactoryPostProcessors(beanFactory);


        registerBeanPostProcessors(beanFactory);

        //信息国际化
        initMessageSource();

        // Initialize event multicaster for this context.
        initApplicationEventMulticaster();

        //实现themeSource接口的刷新,spring框架提供的主题分类
        onRefresh();

        //先加载默认listeners,然后加载自定义listeners
        registerListeners();

        // Instantiate all remaining (non-lazy-init) singletons.
        finishBeanFactoryInitialization(beanFactory);

        // Last step: publish corresponding event.
        finishRefresh();

        boolean exp = true;
        if(exp){
            // Destroy already created singletons to avoid dangling resources.
            //beanFactory.destroySingletons();


            // Reset 'active' flag.
            //cancelRefresh(ex);
        }


    }

    protected void onRefresh() {
    }

    protected void registerListeners() {
    }

    protected void finishBeanFactoryInitialization(DefBeanFactory beanFactory) {
    }

    protected void finishRefresh() {
    }

    protected void initApplicationEventMulticaster() {
    }

    protected void registerBeanPostProcessors(DefBeanFactory beanFactory) {
    }

    protected void initMessageSource() {
    }

    protected void invokeBeanFactoryPostProcessors(DefBeanFactory beanFactory) {

    }

    protected void postProcessBeanFactory(DefBeanFactory beanFactory) {

    }

    protected void prepareBeanFactory(DefBeanFactory beanFactory) {
    }


    //路径校验及解析
    protected void resolvePath(String configPath){
        this.configLocation = configPath;
    }


    //启动监听器
    protected void prepareRefresh() {
        this.startupDate = System.currentTimeMillis();

        synchronized (this.activeMonitor) {
            this.active = true;
        }

        if (logger.isInfoEnabled()) {
            logger.info("Refreshing " + this);
        }

    }

    //每次new都会重新解析加载xml bean到BeanFactory内存
    protected DefBeanFactory obtainFreshBeanFactory() {
        //refreshBeanFactory(); -> loadBeanDefinitions(beanFactory); //解析xml


        return loadBeanDefinitions(new DefBeanFactory());
    }

    //AbstractXmlApplicationContext
    protected DefBeanFactory loadBeanDefinitions(DefBeanFactory defBeanFactory) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defBeanFactory);
        return reader.loadBeanDefinitions();
    }

    @Override
    public Object getBean(String name) {


        return beanFactory.getBean(name);
    }
}
