package boot.review.springbootreview;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;


public class SpringBootReviewApplication {

	public static void main(String[] args) {

		// Spring Container == ApplicationContext
		GenericApplicationContext context = new GenericApplicationContext();

		// HelloController 를 Bean 으로 등록
		context.registerBean(HelloController.class);

		// 등록된 Bean 을 만들 어서 가지고 있음 (초기화 작업)
		context.refresh();


		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

		// servlet container 를 만드는 함수
		// 다른 서버도 사용할 수 있도록 추상화 했기 때문에 webServer 를 사용
		WebServer webServer = serverFactory.getWebServer(servletContext -> {

			// frontController 생성
            servletContext.addServlet("frontController", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

					// 요청 생성 : 인증, 보안, 다국어 등... (공통 기능)

					// GetMethod 로 들어 오는 경우만 처리
					if(req.getRequestURI().equals("/servlet-request") && req.getMethod().equals(HttpMethod.GET.name())){
						String name = req.getParameter("name");

						// 동록된 Bean 을 가져 와서 사용
						HelloController helloController = context.getBean(HelloController.class);

						String ret = helloController.hello(name);

						// 응답 생성
						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
					}
					else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}

				}
				// 모든 요청에 대응할 수 있도록 /* 사용
			}).addMapping("/*");
        });

		// tomcat servlet container 실행
		webServer.start();
	}
}
