package ObjectCreationDescruction.dependencyInjection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WrongStaticUtilClass {
    // 정적 유틸리티를 잘못 사용한 예
    // 여러 사전을 쓸 수 없다.
    // 유연하지 않고 테스트하기 어렵다.
    private static final Map<String, String> dictionary = new HashMap<>();

    // 객체 생성 방지
    private WrongStaticUtilClass() {}

    public static boolean isValid(String word) {
        return true;
    }
    public static List<String> suggestions(String type) {
        return null;
    }
}
