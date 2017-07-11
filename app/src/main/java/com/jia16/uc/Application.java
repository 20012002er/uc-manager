package com.jia16.uc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by lazyb on 2017/7/7.
 */
@SpringBootApplication
@ComponentScan
@ImportResource("classpath*:/dubbo/dubbo-config.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
