package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/*
 *用于读取文件并定于状态码
 */
public class TestBase {
    public Properties prop;//对象
    public int RESPNSE_STATUS_CODE_200 = 200;
    public int RESPNSE_STATUS_CODE_201 = 201;
    public int RESPNSE_STATUS_CODE_404 = 404;
    public int RESPNSE_STATUS_CODE_500 = 500;
    public TestBase(){

        prop=new Properties();
        try {
            //System.out.println(System.getProperty("user.dir"));//获取当前项目路径
            FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+
                    "/src/main/resources/config/config.properties");
            try {
                prop.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}