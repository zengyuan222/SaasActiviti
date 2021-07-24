package com.lzqq.activiti;


import org.activiti.api.process.runtime.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableScheduling
public class ActivitispringbootTest {
    private Logger logger = LoggerFactory.getLogger(ActivitispringbootTest.class);

    public static void main(String[] args) {
        SpringApplication.run(ActivitispringbootTest.class, args);
    }
    @Bean
    public Connector testConnector(){
        return integrationContext -> {
            logger.info("以前叫代理，现在叫连接器被调用啦~~");
            return integrationContext;
        };
    }
}
