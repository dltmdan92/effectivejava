package CommonMethods.equals;

public class Point {
    private final int x;
    private final int y;

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 잘못된 코드 : 리스코프 치환 원칙 위배!!
    // Point의 하위 클래스는 정의상 여전히 Point 이므로 어디서든 Point로써 활용될 수 있어야 한다.
    public boolean equals1(Object obj) {
        // Point.getClass()와 CounterPoint를 비교하면 false가 난다.
        // Point의 equals()는 하위 타입인 CounterPoint 클래스에 대해서도 true라고 return 해야 한다.
        // Point의 equals를 instanceof로 구현하면 된다.
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Point p = (Point) obj;
        return p.getX() == this.getX() && p.getY() == this.getY();
    }

    @Override
    public boolean equals(Object obj) {
        // instanceof 는 null 의 경우 false로 리턴한다. (묵시적 null check)
        if (!(obj instanceof Point))
            return false;
        Point p = (Point) obj;
        return p.getX() == this.x && p.getY() == this.y;
    }
}
