package hyext.ebs.examples.utils;

import jdk.internal.util.xml.PropertiesDefaultHandler;

import java.io.*;
import java.util.Properties;

/**
 * @description:
 * @author: 周世焕
 * @time: 2020-08-11 17:09
 */
public class SysConfigUtil {

    private static Properties properties = null;

    public static Properties getSysConfigUtil(String s) throws IOException {
        if (properties == null){
            InputStream in = ClassLoader.getSystemResourceAsStream(s);
            properties = new Properties();
            properties.load(in);
        }
        return properties;
    }
}
