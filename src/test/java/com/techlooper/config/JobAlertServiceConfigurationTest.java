package com.techlooper.config;

import com.techlooper.service.JobAlertService;
import com.techlooper.service.impl.JobAlertServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by NguyenDangKhoa on 7/13/15.
 */
@Configuration
@PropertySources({@PropertySource("classpath:techlooper.properties")})
public class JobAlertServiceConfigurationTest {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JobAlertService jobAlertService() {
        return new JobAlertServiceImpl();
    }

    @Bean
    public Mapper dozerBeanMapper() {
        return new DozerBeanMapper();
    }

}