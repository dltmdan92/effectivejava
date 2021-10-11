package ObjectCreationDescruction.preventInstance;

/**
 * 인스턴스화를 막으려 거든 private 생성자를 사용할 것
 * static member만 담은 Utility Class
 * -> private 생성자를 통해 인스턴스화를 막자
 */
public class UtilityClass {
    // private 생성자는 상속 또한 막는다.
    private UtilityClass() {
        // private 생성자 이긴 하지만, 클래스 내부에서도 생성자를 호출하지 못하게 AssertionError를 선언했음.
        throw new AssertionError();
    }
}
