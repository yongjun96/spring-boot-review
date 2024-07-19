package boot.review.springbootreview;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleHelloServiceTest {

    @Test
    @DisplayName("simpleHelloService 성공")
    void simpleHelloService(){
        SimpleHelloService helloService = new SimpleHelloService();

        String re = helloService.hello("Test");

        Assertions.assertThat(re).isEqualTo("hello Test");
    }
}