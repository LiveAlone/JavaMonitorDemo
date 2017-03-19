package org.yqj.monitor.demo;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.interceptor.JamonPerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yaoqijun on 2017/3/19.
 */
@Configuration
public class MyConfiguration {

    @Bean
    public JamonPerformanceMonitorInterceptor jamonPerformanceMonitorInterceptor(){
        return new JamonPerformanceMonitorInterceptor();
    }

    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setBeanNames("commonService");
        beanNameAutoProxyCreator.setInterceptorNames("jamonPerformanceMonitorInterceptor");
        return beanNameAutoProxyCreator;
    }

}
