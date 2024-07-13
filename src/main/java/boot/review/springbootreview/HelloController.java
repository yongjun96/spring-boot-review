package boot.review.springbootreview;

import java.util.Objects;

public class HelloController {

    // final -> 한번 할당 되면 이후 재 할당이 불가능
    private final HelloService helloService;

    // 처음 정의 부터 초기화
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name){

        // Objects.requireNonNull -> null 인 경우를 방지 하고 null 이 아닌 경우만 사용
        return helloService.hello(Objects.requireNonNull(name));
    }

}
