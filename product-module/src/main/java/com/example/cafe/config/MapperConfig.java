package com.example.cafe.config;

import liquibase.integration.spring.SpringLiquibase;
import org.modelmapper.ModelMapper;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper()
    {
        return  new ModelMapper();
    }


    @Bean
    public SpringLiquibase springLiquibase()
    {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog-master.yml");
        liquibase.setDataSource(DataSourceBuilder.create()
                .username("root")
                .password("")
                .url("jdbc:mysql://localhost:3306/cafe")
                .driverClassName("com.mysql.jdbc.Driver")
                .build());
        return liquibase;
    }
}
