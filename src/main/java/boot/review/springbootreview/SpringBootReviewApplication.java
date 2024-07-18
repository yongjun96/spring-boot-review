package boot.review.springbootreview;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

// 구성 정보를 가지고 있는 class 명시
// Bean factory method 를 가지는 것 이상 으로 전체 application 을 구성 하는데 중요한 정보를 담음
@Configuration
// component 가 붙은 class 를 찾아서 Bean 으로 등록해 줌
// 모든 하위에 있는 경로를 찾아서 등록
@ComponentScan
public class SpringBootReviewApplication {

	public static void main(String[] args) {
		MySpringApplication.run(SpringBootReviewApplication.class, args);
	}


	@Bean
	public ServletWebServerFactory servletWebServerFactory(){
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet(){
		return new DispatcherServlet();
	}
}
