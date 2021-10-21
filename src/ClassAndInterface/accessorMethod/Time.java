package ClassAndInterface.accessorMethod;

/**
 * public class는 절대로! 가변 필드를 직접 노출해서는 안된다.
 * package-private class OR private 중첩 클래스에서는 종종 필드를 노출할 수도 있다.
 */
public class Time {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;

    /**
     * public class의 field가 불변 - 기본 타입이므로 불변성 OK
     * 하지만 단점이 많다.
     * - API를 변경하지 않고는 표현 방식을 바꿀 수 없음
     * - 필드를 읽을 때 부수 작업을 수행할 수 없다는 단점
     *
     */
    public final int hour;
    public final int minute;

    public Time(int hour, int minute) {
        if (hour < 0 || hour >= HOURS_PER_DAY)
            throw new IllegalArgumentException("시간: " + hour);
        if (minute < 0 || minute >= MINUTES_PER_HOUR)
            throw new IllegalArgumentException("분: " + minute);
        this.hour = hour;
        this.minute = minute;
    }

}
