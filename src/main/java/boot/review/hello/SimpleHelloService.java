package boot.review.hello;

import org.springframework.stereotype.Service;

// Spring container 에 들어 가는 component 지정
@Service
public class SimpleHelloService implements HelloService {

    @Override
    public String hello(String name){
        return "hello " + name;
    }
}
