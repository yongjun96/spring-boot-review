package boot.review.springbootreview;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


// 1. DispatcherServlet 이 정보를 뒤질 때 class level 의 정보를 먼저 확인
// Spring container 에 들어 가는 component 지정
@RestController
public class HelloController {

    // final -> 한번 할당 되면 이후 재 할당이 불가능
    private final HelloService helloService;

    // 처음 정의 부터 초기화
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    // 1. DispatcherServlet 이 생성된 Bean 을 다 뒤진다.
    // 2. 매핑 정보를 가지고 있는 class 의 정보를 찾는다.
    // String 으로 return 하게 되면 View page 를 찾게 된다. (@ResponseBody 를 사용 해야 함)
    @GetMapping("/hello")
    public String hello(String name){

        // Objects.requireNonNull -> null 인 경우를 방지 하고 null 이 아닌 경우만 사용
        return helloService.hello(Objects.requireNonNull(name));
    }

}
