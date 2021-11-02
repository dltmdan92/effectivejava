package enum_and_annotation.extend_enum;

/**
 * 열거 타입 자체는 확장할 수 없지만, 인터페이스와 그 인터페이스를 구현하는 기본 열거 타입을 함께 사용해 같은 효과를 낼 수 있다.
 */
public interface Operation {
    double apply(double x, double y);
}
