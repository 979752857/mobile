package com.tendy.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author dida
 */
public class ConfigUtil {

        private static final String[] CONFIGFILES = {"config.properties"};
        private static Properties properties;

        static {
            try {
                properties = new Properties();
                String path = ConfigUtil.class.getResource("/").getPath();
                for(String CONFIGFILE : CONFIGFILES){
                    FileInputStream fis = new FileInputStream(path + CONFIGFILE);
                    //处理中文乱码问题
                    InputStreamReader reader = new InputStreamReader(fis,"UTF-8");
                    BufferedReader br = new BufferedReader(reader);
                    properties.load(br);
                    fis.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static String getValue(String key) {
            return properties.getProperty(key);
        }

        public static Integer getValueAsInt(String key) {
            return Integer.valueOf(getValue(key));
        }
}