package CommonMethods.equals;

public final class PhoneNumber {

    private final short areaCode, prefix, lineNum;

    public PhoneNumber(short areaCode, short prefix, short lineNum) {
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
        if (!(obj instanceof PhoneNumber))
            return false;
        // 3. type casting을 해준다. (2단계에서 type check 했으므로 안전하다)
        PhoneNumber pn = (PhoneNumber) obj;
        // 4. 핵심 필드들이 일치하는 모두 체크한다.
        return pn.lineNum == this.lineNum && pn.prefix == this.prefix && pn.areaCode == this.areaCode;

        // 그리고 대칭적인지, 추이성이 있는지, 일관적인지 --> 이 세가지를 자문해본다.\
        // equals를 재정의할 때는 hashCode도 반드시 재정의하자
        // 복잡하게 하지 말자.
        // equals의 매개변수는 Object type으로 해라 (다른 타입 쓰지 말고)
        // public boolean equals(MyClass o) ==> 이거는 Object.equals를 재정의 한 것이 아님. (overloading 한것이다.) --> 이렇게 하지 말것!!!

        // 그리고 웬만해서는 그냥 Object.equals 그대로 써라
    }
}
