package me.hyunsoo.springbootmvc.hateoas;

import me.hyunsoo.springbootmvc.user.User;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.RepresentationModel;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SampleController {

   // @CrossOrigin(origins = "http://localhost:10010")
    @GetMapping("/hello")
    public Hello hello(HttpServletResponse response){

        Cookie cookie = new Cookie("univ","soongsil");
        cookie.setPath("/"); //모든 경로에서 사용하겠다.
        cookie.setMaxAge(60 * 60 * 24 * 30); //30일 쿠키

        response.addCookie(cookie);
       // RepresentationModel<Hello> representationModel = new RepresentationModel<Hello>();
       // representationModel.add(Link.of())

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        //map.set("Access-Control-Allow-Origin", "*");

        /*
        ResponseEntity<Hello> helloResponseEntity = new ResponseEntity<Hello>(
                Hello.builder().prefix("Hey,").name("hyunsoo").build(),
                map,
                HttpStatus.OK
        );*/

        return   Hello.builder().prefix("Hey,").name("hyunsoo").build();
    }


    @GetMapping("/cookieCheck")
    public String cookieCheck(@CookieValue(name = "univ", required = false) Cookie univCookie){

        System.out.println("COOKIE : ");
        if(univCookie == null){
            System.out.println("NO COOKIE UNIV");
        }else{
            System.out.println("YES COOKIE UNIV -> VALUE : " + univCookie.getValue());
        }

        return "HI";
    }

}
