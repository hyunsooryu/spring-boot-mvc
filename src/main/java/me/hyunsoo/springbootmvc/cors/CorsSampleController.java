package me.hyunsoo.springbootmvc.cors;

import org.springframework.web.bind.annotation.RestController;

/**
 * Single Origin Policy(SOP) -> 같은 오리진에만 요청을 보낼 수 있다.
 *
 * 기본적으로는 SOP가 적용 되어있습니다.
 *
 * Cross Origin Resource Sharing -> 서로 다른 origin 까지 리소스를 쉐어할 수 있는 표준 / SOP를 우회하기 위한 표준 기술
 *
 * Origin?
 *  URI 스키마 : http / https
 *  hostname : whiteship.me, localhost
 *  port : 8080, 18080
 *
 * 이 3가지를 조합한것을 -> Origin이라고 합니다. 이 Origin이 다르다면 ? CORS 이슈가 생기게 됩니다.
 *
 * AJAX 초창기에 다양한 많은 해결법 제공이 되었는데,
 *
 * 이제 Spring MVC는 이 CORS 이슈를 해결할 수 있는 강력한 기능을 제공합니다.
 *
 * 아무런 Bean 설정없이, Spring MVC가 제공하는 @Cors 를 사용하면 됩니다.
 *
 * @Controller, @RequestMapping에 추가하거나, WebMvcConfigurer 사용해서 글로벌 설정이 가능합니다.
 *
 *
 *
 */


@RestController
public class CorsSampleController {
}
