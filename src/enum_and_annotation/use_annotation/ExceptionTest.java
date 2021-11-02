package enum_and_annotation.use_annotation;

import java.lang.annotation.*;

/**
 * 명시한 예외를 던져야만 성공하는 테스트 메서드용 애너테이션
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class)
public @interface ExceptionTest {

    /**
     * 애너테이션에 멀티 파라미터를 받게 하는 방법 1
     * -> 리턴 타입을 배열로 선언
     * @return
     */
    //Class<? extends Throwable>[] value();

    /**
     * 애너테이션에 멀티 파라미터를 받게 하는 방법 2
     * -> @Repeatable 애너테이션을 활용 (+ 컨테이너 애너테이션)
     * @return
     */
    Class<? extends Throwable> value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ExceptionTestContainer {
    ExceptionTest[] value();
}
