package me.hyunsoo.springbootmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    /**
     *  $(async function(arguments){
     *         alert(arguments);
     *         let result = await callApi();
     *         console.log(result);
     *         async function callApi(){
     *             let data = {};
     *             data = await axios.get("http://localhost:8080/cookieCheck", {withCredentials : true});
     *             return data;
     *         }
     *     });
     * @param registry
     */

    //오리진이란 ? schema(http/https) + hostName(localhost) + Port(10010)
    //CORS 이슈 해결을 위해서
    //1. header -> Access-Control-Allow-Origin : 요청하는 클라이언트 오리진을 설정 / 혹은 * 으로 다 열어주기
    //2. 쿠키는?
        //1. 클라이언트에서 withCredentials : true로 꼭 보내줘야 하며
        //2. 서버에서 Access-Control-Allow-Credentials 를 true로 설정해 줘야만
        //3. Cross Origin 환경에서도 쿠키값을 사용할 수 있다.

    //3. 일반적으로 -> Origin이 다를 때, 즉 origin이 다른 http 통신에서는
    //requestHeader에 쿠키가 자동으로 들어가지 않습니다. 직접 위 처럼 설정을 해줘야 합니다.

    /*
        CORS
        우선적으로, HTTP 요청은 기본적으로는 Cross-Site Http Request가 가능합니다.
        img tage, src, css link, 등, script src 등, 다른 도메인의 데이터를 가져와 사용할 수 있습니다.

        하지만, <script></script> 내부에서 생성되는 Cross-Site HTTP Request는 Same Origin Policy의
        통제를 받기 떄문에 제한됩니다.

        Ajax가 등장하고, 널리 사용되어지며 개발자들은 <scirpt></script> 내부에서도 XMLHttpRequest를 사용하여
        Cross-Site Http Request가 가능해야 한다는 요구가 늘었고, 이에 W3C에서
        CORS 라는 개념을 내오게 됩니다.

        Same Origin Policy :
            The same-origin policy controls interactions between two different origins,
            and these interactions can be placed in three categories.

            Cross-origin write is allowed : links, redirects and form submission

            Cross-origin embed is allowed : Java Script Tag, Css Link, images <video> <embed> .. etc

            ***** Cross-origin read is Not allowed : XMLHttpRequest, Fetch API *****

            CORS 란 결국 ?  Cross-Origin-Resources-Sharing

            Cross-site-http-request 가 가능 하게 하는 표준 규약입니다.

            웹 브라우저가 XmlHttpRequest나 Fetch Api로 외부 서버의 데이터에 접근하려는 경우, SOP 에 의해 cross-origin
            문제가 야기 됩니다.
            하지만, 이 문제를 CORS를 사용하여 해결할 수 있습니다.

            1. Proxy Server 만들기

            2. Access-Control-Allow-Origin을 response Header에 설정해주기.
            Access-Control-Allow-Header는 Form이 아닌, 비동기 요청임을 알려주기 위함입니다.
            X-Requested-With 로 설정 가능
     */
    
    /*
    TODO SimpleRequest VS PreFlight Request ?


     */


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOriginPatterns("*").allowCredentials(true);
    }
}
