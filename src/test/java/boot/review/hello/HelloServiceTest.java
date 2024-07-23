package boot.review.hello;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HelloServiceTest {

    @Test
    @DisplayName("simpleHelloService 성공")
    void simpleHelloService(){
        SimpleHelloService helloService = new SimpleHelloService();

        String re = helloService.hello("Test");

        Assertions.assertThat(re).isEqualTo("hello Test");
    }


    @Test
    @DisplayName("helloDecorator 성공")
    void helloDecorator(){
        HelloDecorator decorator = new HelloDecorator(name -> name);

        String re = decorator.hello("Test");

        Assertions.assertThat(re).isEqualTo("*Test*");
    }
}