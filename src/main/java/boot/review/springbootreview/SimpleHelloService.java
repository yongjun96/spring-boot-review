package boot.review.springbootreview;


public class SimpleHelloService implements HelloService {

    @Override
    public String hello(String name){
        return "hello " + name;
    }
}
