package me.hyunsoo.springbootmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

//추가적인 설정
@Configuration
//@EnableWebMvc -> 이게 붙는다면, spring-boot 가 제공하는 MvcAuto설정을 안줍니다.
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/m/**").addResourceLocations("classpath:/m/").setCachePeriod(20);
        registry.addResourceHandler("/pc/**").addResourceLocations("classpath:/pc/").setCachePeriod(20);
    }
}
