package com.hwx.redis.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件工具类
 *
 * @author: Huawei Xie
 * @date: 2019/7/28
 */
public class PropertiesUtil {

    /**
     * 通过key获取对应的value
     * @param key
     * @return
     */
    public static String getProperties(String key) {
        Properties properties;
        String value = null;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
            value = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
