package com.wisecoach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FabricFrameworkSampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FabricFrameworkSampleApplication.class, args);
        System.out.println();
    }

}
