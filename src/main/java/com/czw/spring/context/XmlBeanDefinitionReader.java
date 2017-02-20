package com.czw.spring.context;

import com.czw.util.ComUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

/**
 * @author ZeviChen , 2017/2/20 0020 上午 11:22
 */
public class XmlBeanDefinitionReader {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private DefBeanFactory defBeanFactory;

    public XmlBeanDefinitionReader(DefBeanFactory defBeanFactory) {
        this.defBeanFactory = defBeanFactory;
    }


    public DefBeanFactory loadBeanDefinitions() {
        String resouce = ComUtils.getFilePath("com.czw.spring.context", "FSContext.xml", true);
        saxReader(resouce);
        return defBeanFactory;
    }

    public void saxReader(String path) {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new FileInputStream(new File(path)));
            Element root = document.getRootElement();

            List<Element> beans = root.elements();
            for (int i = 0;i<beans.size();i++) {
                Element element = beans.get(i);
                BeanDefinition definition = new BeanDefinition();
                String beanName = element.attribute("id").getText();
                definition.setBeanName(beanName);
                String beanPath = element.attribute("class").getText();
                beanPath = beanPath.substring(0,beanPath.lastIndexOf("."));
                definition.setBeanClass(beanPath);
                definition.setInitMethodName(element.attribute("init").getText());
                defBeanFactory.registerBeanDefinition(beanName, definition);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        DefBeanFactory defBeanFactory = new DefBeanFactory();
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(defBeanFactory);
        xmlReader.loadBeanDefinitions();
        BeanDefinition beanDefinition = (BeanDefinition) defBeanFactory.getBean("merchant");
        System.out.println(beanDefinition);

    }


}
