package me.hyunsoo.springbootmvc.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 1부
 * Spring Boot 가 제공해주는 기본 설정
 *
 * spring.factories -> WebMvcAutoConfiguration 이라는 자동 설정 파일이 적용 되었기 때문에,
 * 아무 설정을 하지 않아도, 부트에서 MVC 기능을 사용할 수 있다.
 *
 * Spring Boot MVC
 *
 * 자동설정으로 제공하는 여러 기본 기능
 *
 * 스프링 MVC 확장
 * @Configuration + WebMvcConfigurer
 *
 * 스프링 MVC 재정의
 * @Configuration + @EnableWebMvc
 *
 *
 * 2부
 * HttpMessageConverter란?
 *
 * @MethodArgumentResolver
 *
 * SpringFramework에서 제공하는 인터페이스, HTTP 요청 본문을 객체로 변경하거나, 객체를 HTTP 응답 본문으로 변경할 때 사용
 * @RequestBody, @ResponseBody와 함께 사용되어집니다.
 *
 * 재미있는 사실은?
 * Accept : application/xml 시,
 * 해당 데이터를 줄 수 없다면, org.springframework.web.HttpMediaTypeNotAcceptableException 406 Error 가 발생.
 *
 *  3부 ContentNegotiationViewResolver란?
 *  ViewResolver 중에 하나인데, 이 리졸버를 통해 들어오는 요청의 accept-header 에 따라서, 응답이 달라집니다.
 *  최종적으로, 어떤 뷰를 고를까 고민하고 리턴을 해줍니다. - 이를 판단하는 데 가장 좋은 정보는 accept입니다.
 *  경우에 따라서는 이 accept를 제공하지 않는 request가 있습니다.
 *  ContentNegotiationViewResolver는 부트에서 이미 등록시켜줍니다.
 *
 *
 *  4부 정적 리소스 지원?
 *  정적 리소스 맵핑 "/**"
 *  기본 리소스 위치
 *  classpath:/static
 *  classpath:/public
 *  classpath:/resources/
 *  classpath:/META-INF/resources
 *
 *  spring.mvc.static-path-pattern : 맵핑 설정 변경 가능
 *  spring.mvc.static-locations : 리소스 찾을 위치 변경 가능
 *
 *  Last-Modified 헤더를 보고 304 응답을 보냄
 *
 *  ResourceHttpRequestHandler 가 처리함
 *  -> WebMvcConfigurer -> addResourceHandlers 로 커스터 마이징도 가능 함
 *
 *
 *  5부. 웹Jar
 *
 *  WebJar -> jquery. vue.js 등을 jar로 추가할 수 잇습니다.
 *  /webjars/**
 *  버전 생략하고 사용하려면 webjars-locator-core 의존성을 추가해야 합니다.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */


@Controller
@RequiredArgsConstructor
public class UserController {


    final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    final StringHttpMessageConverter stringHttpMessageConverter;

    //final MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //기본적으로는 Json Converter를 사용하고,
    //string 하나면 -> stringMessageConverter를 사용한다.
    //Mappin

    @PostMapping("/users/create")
    public @ResponseBody  User create(@RequestBody User user){

        return user;
    }

    @PostConstruct
    public void postC(){
        System.out.println(mappingJackson2HttpMessageConverter);
        System.out.println(stringHttpMessageConverter);
        //System.out.println(mappingJackson2XmlHttpMessageConverter);
    }


    @GetMapping("/main")
    public String main(@ModelAttribute User user){
        user.setAge(20);
        user.setName("hyunsoo");
        return "main";
    }


}
