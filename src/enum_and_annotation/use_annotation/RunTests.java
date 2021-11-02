package enum_and_annotation.use_annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {

    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName(args[0]);
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(null);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m + " 실패 : " + exc);
                } catch (Exception exc) {
                    System.out.println("잘못 사용한 @Test: " + m);
                }
            }
            else if (m.isAnnotationPresent(ExceptionTest.class)
                    || m.isAnnotationPresent(ExceptionTestContainer.class)) {
                /*
                반복 가능 애너테이션은 처리할 때 주의를 요한다.

                반복 가능 애너테이션을 여러 개 달면 하나만 달았을 때와 구분하기 위해 해당 '컨테이너' 애너테이션 타입이 적용됨!!

                getAnnotationsByType 메서드는 이 둘을 구분하지 않아서, 반복 가능 애너테이션과 그 컨테이너 애너테이션을 모두 가져오지만

                isAnnotationPresent 메서드는 둘을 명확히 구분한다.
                --> 따라서 반복 가능 애너테이션을 여러 번 단 다음 isAnnotationPresent로 반복 가능 애너테이션이 달렸는지 검사한다면
                    "그렇지 않다" 라고 알려준다. (컨테이너 달렸기 때문에)
                    그 결과 애너테이션을 여러 번 단 메서드들을 모두 무시하고 지나간다.

                    같은 이유로 isAnnotationPresent로 "컨테이너" 애너테이션이 달렸는지 검사한다면
                    반복 가능 애너테이션을 한 번만 단 메서드를 무시한다.

                    그래서 달려있는 수와 상관없이 모두 검사항려면 둘을 OR 조건으로 확인해야 한다.

                */


                tests++;
                try {
                    m.invoke(null);
                    System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n", m);
                } catch (InvocationTargetException wrappedEx) {
                    Throwable exc = wrappedEx.getCause();
                    int oldPassed = passed;

                    /*Class<? extends Throwable>[] excTypes = m.getAnnotation(ExceptionTest.class).value();

                    for (Class<? extends Throwable> excType : excTypes) {
                        if (excType.isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }*/

                    ExceptionTest[] excTests = m.getAnnotationsByType(ExceptionTest.class);

                    for (ExceptionTest excTest : excTests) {
                        if (excTest.value().isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }

                    if (passed == oldPassed) {
                        System.out.printf("테스트 %s 실패: %s %n", m, exc);
                    }
                } catch (Exception exc) {
                    System.out.println("잘못 사용한 @ExceptionTest: " + m);
                }
            }
        }
        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
    }

}
