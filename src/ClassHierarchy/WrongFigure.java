package ClassHierarchy;

/**
 * BAD CASE
 * 태그 달린 클래스 - 클래스 계층 구조에 비해 훨씬 나쁘다!!
 *
 * 단점
 * - enum 선언, 태그 필드, switch 문 등 쓸데없는 코드가 많다.
 * - 여러 구현이 한 클래스에 혼합돼 있어서 가독성 나쁨.
 *
 *
 */
public class WrongFigure {
    enum Shape {
        RECTANGLE, CIRCLE
    }

    // 태그 필드 - 현재 모양을 나타낸다.
    final Shape shape;

    // 다음 필드들은 모양이 사각형(RECTANGLE)일 때만 쓰인다.
    double length;
    double width;

    // 다음 필드는 모양이 원(CIRCLE)일 때만 쓰인다.
    double radius;

    // 원용 생성자
    public WrongFigure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    // 사각형 용 생성자
    public WrongFigure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    double area() {
        switch (shape) {
            case CIRCLE:
                return Math.PI * (radius * radius);
            case RECTANGLE:
                return length * width;
            default:
                throw new AssertionError(shape);
        }
    }
}
