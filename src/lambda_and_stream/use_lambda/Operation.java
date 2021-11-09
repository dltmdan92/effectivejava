package lambda_and_stream.use_lambda;

import java.util.function.DoubleBinaryOperator;

public enum Operation {

    PLUS("+", Double::sum), // 상수별 메서드 구현
    MINUS("-", (x, y) -> x - y),
    TIMES("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y);


    private final String symbol;
    private final DoubleBinaryOperator op;

    // enum 타입 생성자가 실행될 때, 접근할 수 있는 필드는 자기 자신의 상수 변수 뿐이다.
    Operation(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }

}
