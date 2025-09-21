package com.prokofeva.dbplannyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
//@EnableJpaRepositories
public class DbPlannyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbPlannyServiceApplication.class, args);
    }

}
