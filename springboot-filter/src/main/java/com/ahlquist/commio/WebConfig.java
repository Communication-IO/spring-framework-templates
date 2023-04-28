package com.ahlquist.commio;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean greetingFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("greeting");
        GreetingFilter greetingFilter = new GreetingFilter();
        registrationBean.setFilter(greetingFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean helloFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("hello");
        HelloFilter helloFilter = new HelloFilter();
        registrationBean.setFilter(helloFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }

}