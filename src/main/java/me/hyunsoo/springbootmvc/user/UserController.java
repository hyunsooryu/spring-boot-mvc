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
 *  5부. 웹 Jar
 *
 *  WebJar -> jquery. vue.js 등을 jar로 추가할 수 잇습니다.
 *  /webjars/**
 *  버전 생략하고 사용하려면 webjars-locator-core 의존성을 추가해야 합니다.
 *
 *
 *  6부. index Page & Favicon ?
 *
 *  웰컴 페이지
 *  index.html 찾아보고 있으면 제공
 *  index.template 찾아보고 있으면 제공
 *  둘 다 없다면, 에러 페이지.
 *
 *  파비콘
 *  favicon.ico
 *
 *
 *  7부. Thymeleaf ?
 *  스프링부트가 자동 설정을 지원하는 템플릿 엔진
 *  FreeMarker, Groovy, Thymeleaf, Mustache
 *
 *  JSP를 권장하지 않는 이유
*   JAR 패키징 할 때는 동작하지 않고, WAR 패키징을 해야 하기 떄문입니다.
 *  또한 Undertow는 JSP를 아예 지원하지도 않습니다.
 *
 *  Thymeleaf를 사용하려면 -> spring-boot-starter-thymeleaf 라는 의존성을 추가하여,
 *  ClassPath에서 찾을 수 있게 해줘야 합니다.
 *
 *  8부. HtmlUnit ?
 *
 *  HTML 템플릿 뷰 테스트를 보다 전문적으로 할 수 있습니다.
 *
 *  9부. ExceptionHandler?
 *
 *  스프링 @MVC 예외 처리 방법
 * @ControllerAdvice
 * @ExchangepHandler
 *
 * 스프링 부트가 제공하는 기본 예외 처리기
 * BasicErrorController - AutoConfigurer로 제공 됨.
 * HTML, JSON 응답 지원 - curl http://localhost:8080 -> JSON 리턴 해 줌
 *
 * 커스터 마이징 방법
 * ErrorController 구현 -> BasicErrorController 를 상속받아서 만들어도 좋습니다.
 *
 * 커스텀 에러 페이지
 *
 * 상태 코드 값에 따라 에러페이지 보여주기
 *
 * src/main/resources/static|templae/error/ ~
 *
 * 404.html
 * 5xx.html
 * ErrorViewResolver 구현
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

    @GetMapping("/hello2")
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
