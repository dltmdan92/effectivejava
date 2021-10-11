package ObjectCreationDescruction.preventUnneccessaryInstance;

import java.util.regex.Pattern;

/**
 * 불필요한 인스턴스 생성 막기
 * ex) 메서드를 호출할 때 마다 instance가 생성된다면?? -> 성능에 큰 영향
 */
public class RomanNumerals {

    /*
    String.matches 메서드를 매번 사용하게 된다.
    Pattern 인스턴스가 한번 쓰고 버려져서 곧바로 가비지 컬렉션 대상이 된다.
    특히 Pattern은 생성 비용이 비쌈.
     */
    static boolean isRomanNumeral(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    private static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})"
            + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$"
            );

    // 이렇게 개선할 수 있다. (성능)
    // Pattern은 단 한번만 생성
    static boolean isRomanNumeralGood(String s) {
        return ROMAN.matcher(s).matches();
    }

}
