package boot.review.springbootreview;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
// Spring Container 에 같은 Bean 이 두개가 존재 하는 경우, @Primary 가 붙은 것을 우선 적으로 사용
@Primary
public class HelloDecorator implements HelloService {

    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String hello(String name) {
        return "*" + helloService.hello(name) + "*";
    }
}
