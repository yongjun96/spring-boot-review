package boot.review.springbootreview;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;


public class SpringBootReviewApplication {

	public static void main(String[] args) {

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

		// servlet container 를 만드는 함수
		// 다른 서버도 사용할 수 있도록 추상화 했기 때문에 webServer 를 사용
		WebServer webServer = serverFactory.getWebServer(servletContext -> {

			// servlet 생성
            servletContext.addServlet("servlet", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 호출 생성
					String name = req.getParameter("name");

					// 응답 생성
					resp.setStatus(HttpStatus.OK.value());
					resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
					resp.getWriter().println("servlet Hello"+ name);
				}
				// /servlet-request 이라는 path 로 요청이 들어 오면 생성한 servlet 으로 처리
			}).addMapping("/servlet-request");
        });

		// tomcat servlet container 실행
		webServer.start();
	}
}
