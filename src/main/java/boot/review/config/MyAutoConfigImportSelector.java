package boot.review.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.StreamSupport;


public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    // String 으로 리턴 시키면 String 값과 같은 class 이름을 가진 구성 정보를 import
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 어노테이션의 클레스 object 넣음
        // 자동 구성에 사용할 구성 요소들이 들어가 있게 됨
        ImportCandidates candidates =  ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);
    }
}
