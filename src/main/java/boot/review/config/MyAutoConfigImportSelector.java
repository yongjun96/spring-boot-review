package boot.review.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    // String 으로 리턴 시키면 String 값과 같은 class 이름을 가진 구성 정보를 import
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
            "boot.review.config.autoconfig.DispatcherServletConfig",
            "boot.review.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
