package ObjectCreationDescruction.singleton;

/**
 * 싱글톤 : 인스턴스 오직 하나만,
 * - stateless, 설계상 유일해야 하는 시스템 컴포넌트
 *
 */
public class Elvis {
    // 방법 1. public static final 필드 방식의 싱글턴
    public static final Elvis INSTANCE = new Elvis();
    // private 생성자는 Elvis.INSTANCE를 호출할 때 딱 한번 생성된다.
    // BUT reflection을 통한 인스턴스 생성은 방어코드를 넣어줘야 한다.
    private Elvis() {

    }

    public void leaveTheBuilding() {
        // ...
    }

    // 직렬화 -> 역직렬화 시, 또 다른 Elvis instance 가 생성되는 문제 가 있다.
    // readResolve 메서드를 선언해서 진짜 'Elvis'를 반환하고, 가짜 Elvis는 GC에 맡긴다.
    // Serializable 의 doc 참고하기
    private Object readResolve() {
        return INSTANCE;
    }
}
