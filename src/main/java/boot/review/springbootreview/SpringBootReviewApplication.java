package boot.review.springbootreview;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;


public class SpringBootReviewApplication {

	public static void main(String[] args) {

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

		// servlet container 를 만드는 함수
		// 다른 서버도 사용할 수 있도록 추상화 했기 때문에 webServer 를 사용
		WebServer webServer = serverFactory.getWebServer(servletContext -> {

			HelloController helloController = new HelloController();

			// frontController 생성
            servletContext.addServlet("frontController", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

					// 요청 생성 : 인증, 보안, 다국어 등... (공통 기능)

					// GetMethod 로 들어 오는 경우만 처리
					if(req.getRequestURI().equals("/servlet-request") && req.getMethod().equals(HttpMethod.GET.name())){
						String name = req.getParameter("name");

						// 요청 에서 받아온 Parameter 를 가지고 helloController 에 Method 에서 로직을 수행한 뒤 리턴 받을 수도 있음
						String ret = helloController.hello(name);

						// 응답 생성
						resp.setStatus(HttpStatus.OK.value());
						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
					}

					else if (req.getRequestURI().equals("/user")) {
						// 해당 요청의 응답 작성
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
