package com.czw.toolkit.netty.http1;

import com.czw.util.ComUtils;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author ZeviChen , 2017/7/5 12:52
 */
public class DefaultPage {

    public final static String PAGE_ROOT = ComUtils.getFilePath("com.czw.toolkit.netty.http1.page", "", true);
    public final static StringBuilder INDEX = new StringBuilder();
    public final static StringBuilder NOT_FOUND = new StringBuilder();
    public final static StringBuilder EMPTY = new StringBuilder();


    static {
        try {
            String index = PAGE_ROOT + "index.html";
            String notFound = PAGE_ROOT + "404.html";
            INDEX.append(Files.toString(new File(index), Charset.forName("UTF-8")));
            NOT_FOUND.append(Files.toString(new File(notFound), Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
