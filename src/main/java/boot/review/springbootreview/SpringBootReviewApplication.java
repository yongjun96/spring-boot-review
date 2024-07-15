package boot.review.springbootreview;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class SpringBootReviewApplication {

	public static void main(String[] args) {

		// Spring Container == ApplicationContext
		GenericWebApplicationContext context = new GenericWebApplicationContext() {

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

		// Bean 으로 등록
		context.registerBean(HelloController.class);
		context.registerBean(SimpleHelloService.class);

		// 등록된 Bean 을 만들 어서 가지고 있음 (초기화 작업)
		context.refresh();
	}
}
