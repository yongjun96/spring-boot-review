package boot.review.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// ElementType.TYPE -> class, interface, enum
@Target(ElementType.TYPE)
@Configuration
@ComponentScan
@EnableMyAutoConfig
public @interface MySpringBootApplication {
}
