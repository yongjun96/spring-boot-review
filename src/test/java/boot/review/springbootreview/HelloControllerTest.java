package boot.review.springbootreview;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


class HelloControllerTest {

    @Test
    @DisplayName("helloController 성공")
    void helloController(){

        // test 용도로 사용 하는 stub (name -> name) : 일종의 DI (수동 DI)
        HelloController helloController = new HelloController(name -> name);

        String test = helloController.hello("Test");

        Assertions.assertThat(test).isEqualTo("Test");
    }

    @Test
    @DisplayName("helloController 실패 : name 이 Null 이거나 빈 값인 경우")
    void failsHelloController(){
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalStateException.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(IllegalStateException.class);
    }

}