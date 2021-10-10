package ObjectCreationDescruction.factoryMethod;

public class BooleanFactory {

    /**
     * static factory method rathen than public constructor
     * 디자인 패턴의 팩토리 메서드 패턴과는 다름!
     *
     * 장점 1 : 이름을 가질 수 있음
     * 장점 2 : 호출할 때 마다 인스턴스 생성 불필요
     *      - 인스턴스 캐싱한거를 재사용
     *      - 인스턴스 통제
     *      - immutable class 활용
     * 장점 3 : 반환 타입의 하위 타입 객체를 반환할 수 있음
     *      - 인터페이스 타입을 리턴해서 유연성 UP
     *      - 자바8 인터페이스에서 활용하면 좋다.
     * 장점 4 : 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환 가능
     *      - 반환 타입의 하위타입 이기만 하면, 어떤 클래스의 객체를 반환하든 상관없다.
     * 장점 5 : 정적 팩토리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
     *
     */
    public static Boolean valueof(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
}
