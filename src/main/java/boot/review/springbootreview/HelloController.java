package boot.review.springbootreview;

import java.util.Objects;

public class HelloController {

    public String hello(String name){

        SimpleHelloService simpleHelloService = new SimpleHelloService();

        // Objects.requireNonNull -> null 인 경우를 방지 하고 null 이 아닌 경우만 사용
        return simpleHelloService.hello(Objects.requireNonNull(name));
    }

}
