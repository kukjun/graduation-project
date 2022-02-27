package spms.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


// Component 어노테이션을 적용한 클래스는 자동 생성되도록 사용함.
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String value() default "";
}
