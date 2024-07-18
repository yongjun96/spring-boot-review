package boot.review.springbootreview;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {

    public static void run(Class<?> applicationClass, String[] args) {

        // AnnotationConfigWebApplicationContext -> @Configuration 가 붙은 class 를 이용 해서 설정 정보를 읽어 옴
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext() {

            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

                // servlet container 를 만드는 함수
                // 다른 서버도 사용할 수 있도록 추상화 했기 때문에 webServer 를 사용
                WebServer webServer = serverFactory.getWebServer(servletContext -> {

                    // dispatcherServlet 생성
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                            .addMapping("/*");
                });

                // tomcat servlet container 실행
                webServer.start();
            }
        };

        // java code 로 된 구성 정보 -> SpringBootReviewApplication 에 @Bean 을 사용
        context.register(applicationClass);
        context.refresh();
    }

}
