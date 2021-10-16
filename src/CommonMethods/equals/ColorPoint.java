package CommonMethods.equals;

/**
 * 추이성을 위배하는 case
 * 구체 클래스를 확장해 새로운 값을 추가하면서 equals 규약을 만족시킬 방법은 존재하지 않는다!!
 * 하위클래스에서 원소가 늘어나는 경우, 상속이 아닌 "컴포지션"을 통해 해결한다.
 *
 * 참고로 Point가 추상클래스 라면 이런 문제는 발생하지 않는다.
 * 상위클래스가 인스턴스화 될 수 없는 경우라면 아래의 이슈는 발생하진 않는다.
 *
 */
public class ColorPoint extends Point{
    private final Point point;
    private final Color color;

    public Point getPoint() {
        return point;
    }

    public Color getColor() {
        return color;
    }

    public ColorPoint(int x, int y, Point point, Color color) {
        super(x, y);
        this.point = point;
        this.color = color;
    }

    // 잘못된 코드 : 대칭성 위배!!
    public boolean equals1(Object obj) {
        if (!(obj instanceof ColorPoint))
            return false;

        // Point의 equals는 Color를 무시한다.
        // ColorPoint의 equals는 Point의 경우 Color 매개변수가 없으므로 false를 반환한다.
        // Point p = new Point(1, 2); ColorPoint cp = new ColorPoint(1, 2, Color.RED)
        // p.equals(cp) = true , cp.equals(p) = false => 대칭성 위배
        return super.equals(obj) && ((ColorPoint) obj).getColor() == this.color;
    }

    // 잘못된 코드 : 추이성 위배!
    public boolean equals2(Object obj) {
        if (!(obj instanceof Point))
            return false;

        // obj가 일반 Point면 색상을 무시하고 비교한다.
        if (!(obj instanceof ColorPoint))
            return obj.equals(this);

        // o가 ColorPoint면 색상까지 비교한다.
        return super.equals(obj) && ((ColorPoint) obj).getColor() == this.color;

        /*
        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
        p1.equals(p2) = true;
        p2.equals(p3) = true;
        p1.equals(p3) = false; ==> 추이성 위배!!!

        또한 이 방식은 무한 재귀에 빠질 수 도 있다.
         */
    }

    // SOLUTION
    // 하위클래스에서 원소가 늘어나는 경우, 상속이 아닌 컴포지션을 통해 해결한다.
    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ColorPoint))
            return false;
        ColorPoint cp = (ColorPoint) obj;
        return cp.getPoint().equals(this.point) && cp.getColor().equals(this.color);
    }
}
