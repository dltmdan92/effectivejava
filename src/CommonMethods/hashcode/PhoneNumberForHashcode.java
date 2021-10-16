package CommonMethods.hashcode;

public final class PhoneNumberForHashcode {

    private final short areaCode, prefix, lineNum;

    public PhoneNumberForHashcode(short areaCode, short prefix, short lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "지역코드");
        this.prefix = rangeCheck(prefix, 999, "프리픽스");
        this.lineNum = rangeCheck(lineNum, 9999, "가입자 번호");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max)
            throw new IllegalArgumentException(arg + ": " + val);
        return (short) val;
    }

    /**
     * equals를 구현하는 원칙
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        // 1. == 을 사용하여 동일한 instance인지를 먼저 체크 한다.
        if (obj == this)
            return true;
        // 2. instanceof를 사용하여 type 체크를 한다.
        if (!(obj instanceof PhoneNumberForHashcode))
            return false;
        // 3. type casting을 해준다. (2단계에서 type check 했으므로 안전하다)
        PhoneNumberForHashcode pn = (PhoneNumberForHashcode) obj;
        // 4. 핵심 필드들이 일치하는 모두 체크한다.
        return pn.lineNum == this.lineNum && pn.prefix == this.prefix && pn.areaCode == this.areaCode;

        // 그리고 대칭적인지, 추이성이 있는지, 일관적인지 --> 이 세가지를 자문해본다.\
        // equals를 재정의할 때는 hashCode도 반드시 재정의하자
        // 복잡하게 하지 말자.
        // equals의 매개변수는 Object type으로 해라 (다른 타입 쓰지 말고)
        // public boolean equals(MyClass o) ==> 이거는 Object.equals를 재정의 한 것이 아님. (overloading 한것이다.) --> 이렇게 하지 말것!!!

        // 그리고 웬만해서는 그냥 Object.equals 그대로 써라
    }

    /**
     * 전형적인 hashCode 메서드
     * @return
     */
    @Override
    public int hashCode() {
        // 1. int 변수 result를 선언한 후, 첫번째 핵심 필드를 해시코드화 하여 초기화 한다. (핵심필드 = equals에 사용되는 필드)
        // 해시코드 화 할 때, primitive type field의 경우 Type.hashCode(f)를 한다. (Type은 boxing class)
        // 필드값이 null이면 0으로 쓴다.
        // 필드가 배열이면 핵심 원소 각각을 별도 필드처럼 다룬다. (없으면 0으로 사용) 모든 원소가 핵심원소이면 Arrays.hashCode를 사용
        // 그리고 클래스가 불변이고 hashCode를 계산하는 cost가 크다면 ==> 캐시하는 방법도 있다. (멤버변수에 hashCode 선언)
        int result = Short.hashCode(this.areaCode);
        result = 31 * result + Short.hashCode(this.prefix);
        result = 31 * result + Short.hashCode(this.lineNum);
        //Objects.hash(lineNum, prefix, areaCode); // 위의 로직과 비슷한 수준의 hashCode를 생성하나 성능이 좀 아쉽다. (배열 생성 및 boxing / unboxing을 함)
        return result;

        // hashCode를 다 구현했다면 동치인 인스턴스에 대해 똑같은 해시코드를 반환할지 자문해보자.
        // 31 * 곱셈은 비슷한 필드가 여러 개일 때 해시 효과를 크게 내준다.
        // 31인 이유는 홀수이면서 소수 이기 때문 (만약 이숫자가 짝수이고 overflow가 발생하면 정보를 잃게 됨)
        // 31 * 곱셉이 없으면 모든 아나그램(철자가 같은데 순서만 다른 문자열)의 해시코드가 같아 진다.
    }
}
