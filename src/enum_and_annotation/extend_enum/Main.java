package enum_and_annotation.extend_enum;

import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test1(ExtendedOperation.class, x, y);
        test2(List.of(ExtendedOperation.values()), x, y);
    }

    // <T extends Enum<T> & Operation> : T는 enum 타입 and Operation의 하위 타입
    private static <T extends Enum<T> & Operation> void test1(Class<T> opEnumType, double x, double y) {
        for (Operation op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }

    // test1에서 값 타입 매개변수 선언부가 복잡하므로 아래와 같이 메서드를 만들수도 있다.
    private static void test2(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}
