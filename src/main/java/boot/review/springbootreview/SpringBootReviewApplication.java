package boot.review.springbootreview;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// 구성 정보를 가지고 있는 class 명시
// Bean factory method 를 가지는 것 이상 으로 전체 application 을 구성 하는데 중요한 정보를 담음
@Configuration
public class SpringBootReviewApplication {

	// java factory method
	@Bean
	public HelloController helloController(HelloService helloService){
		return new HelloController(helloService);
	}

	// java factory method
	@Bean
	public HelloService helloService(){
		return new SimpleHelloService();
	}

	public static void main(String[] args) {

		// AnnotationConfigWebApplicationContext -> @Configuration 가 붙은 class 를 이용 해서 설정 정보를 읽어 옴
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext() {

			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

				// servlet container 를 만드는 함수
				// 다른 서버도 사용할 수 있도록 추상화 했기 때문에 webServer 를 사용
				WebServer webServer = serverFactory.getWebServer(servletContext -> {

					// dispatcherServlet 생성
					servletContext.addServlet("dispatcherServlet",
									// 요청을 위임할 servlet container 지정
									new DispatcherServlet(this))
							.addMapping("/*");
				});

				// tomcat servlet container 실행
				webServer.start();
			}
		};

		// java code 로 된 구성 정보 -> SpringBootReviewApplication 에 @Bean 을 사용
		context.register(SpringBootReviewApplication.class);
		context.refresh();
	}
}
