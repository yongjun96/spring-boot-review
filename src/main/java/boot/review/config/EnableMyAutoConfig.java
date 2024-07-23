package boot.review.config;

import boot.review.config.autoconfig.DispatcherServletConfig;
import boot.review.config.autoconfig.TomcatWebServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// Component Annotation 이 붙은 Class 들을 지정 해서 MetaAnnotation 으로 넣어 줄 수 있다.
@Import({DispatcherServletConfig.class, TomcatWebServerConfig.class})
public @interface EnableMyAutoConfig {
}
