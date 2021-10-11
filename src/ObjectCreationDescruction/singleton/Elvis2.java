package ObjectCreationDescruction.singleton;

import java.io.Serializable;

public class Elvis2 implements Serializable {
    private static final Elvis2 INSTANCE = new Elvis2();
    // private 생성자는 Elvis.INSTANCE를 호출할 때 딱 한번 생성된다.
    // BUT reflection을 통한 인스턴스 생성은 방어코드를 넣어줘야 한다.
    private Elvis2() {

    }

    // 방법 2. public static 멤버로 정적 팩토리 메서드 제공
    public static Elvis2 getInstance() {
        return INSTANCE;
    }

    // 직렬화 -> 역직렬화 시, 또 다른 Elvis instance 가 생성되는 문제 가 있다.
    // readResolve 메서드를 선언해서 진짜 'Elvis'를 반환하고, 가짜 Elvis는 GC에 맡긴다.
    // Serializable 의 doc 참고하기
    private Object readResolve() {
        return INSTANCE;
    }
}
