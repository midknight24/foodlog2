package com.midknight.foodlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Onlyme on 10/1/2017.
 */

@EnableAutoConfiguration
@ComponentScan
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
