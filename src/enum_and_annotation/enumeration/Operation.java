package enum_and_annotation.enumeration;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * 열거 타입을 써라
 * 정수 상수보다 훨씬 뛰어나고, 강력하고, 안전하다.
 */
public enum Operation {


    PLUS("+")    {public double apply(double x, double y){return x + y;}}, // 상수별 메서드 구현
    MINUS("-")   {public double apply(double x, double y){return x - y;}},
    TIMES("*")   {public double apply(double x, double y){return x * y;}},
    DIVIDE("/")  {public double apply(double x, double y){return x / y;}};


    private final String symbol;

    // enum 타입 생성자가 실행될 때, 접근할 수 있는 필드는 자기 자신의 상수 변수 뿐이다.
    Operation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public abstract double apply(double x, double y);


    // enum 타입 생성자가 실행되기 전에는 아직 static 요소들은 초기화 되기 전.
    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    // 기존 열거 타입에 상수별 동작을 넣을 때는 switch 문이 좋은 선택이 될 수 있다.
    public static Operation inverse(Operation op) {
        switch (op) {
            case PLUS:      return Operation.MINUS;
            case MINUS:     return Operation.PLUS;
            case TIMES:     return Operation.DIVIDE;
            case DIVIDE:    return Operation.TIMES;

            default:    throw new AssertionError("알 수 없는 연산: " + op);
        }
    }

    private static final Map<String, Operation> stringToEnum =
            Stream.of(values()).collect(toMap(Object::toString, e -> e));
}
