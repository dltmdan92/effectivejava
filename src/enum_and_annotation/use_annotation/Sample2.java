package enum_and_annotation.use_annotation;

import java.util.ArrayList;
import java.util.List;

public class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    public static void m1() { // 성공해야 한다.
        int i = 0;
        i = i / i;
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m2() { // 실패 : 다른 예외가 발생했음.
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m3() {} // 실패 : 명시한 예외가 발생하지 않음.

    // 배열로 선언된 annotation value()
    /*@ExceptionTest({
            IndexOutOfBoundsException.class,
            NullPointerException.class
    })*/
    @ExceptionTest(IndexOutOfBoundsException.class)
    @ExceptionTest(NullPointerException.class)
    public static void doublyBad() { // 성공해야 한다.
        List<String> list = new ArrayList<>();
        // NullPointerException을 던질 수 있다.
        list.addAll(5, null);
    }
}
