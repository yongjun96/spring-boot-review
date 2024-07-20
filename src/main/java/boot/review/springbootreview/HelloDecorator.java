package boot.review.springbootreview;

import org.springframework.stereotype.Service;

@Service
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
