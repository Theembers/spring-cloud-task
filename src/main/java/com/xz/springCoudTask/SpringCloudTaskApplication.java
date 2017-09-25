package com.xz.springCoudTask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableTask
public class SpringCloudTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTaskApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new TestCommandLineRunner();
    }

    public static class TestCommandLineRunner implements CommandLineRunner {
        @Override
        public void run(String... strings) throws Exception {
            System.out.println("this is a Test about spring cloud task.");
            try{
                List<String> list = new ArrayList<>();
                list.get(1);
            }catch (Exception e){
                System.err.println("Error");
                throw e;
            }
        }
    }
}
