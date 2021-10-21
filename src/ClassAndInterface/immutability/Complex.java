package ClassAndInterface.immutability;

/**
 * 불변 복소수 클래스
 * 불변성을 유지함으로써 변경 가능성을 최소화 해야 한다.
 *
 * 특히나 가변 클래스를 참조하는 필드는 접근자 메서드에서 절대로 필드 자체를 리턴해서는 안된다.
 *
 *
 * 해당 클래스에서는 메서드들이 인스턴스 자신은 수정 X,
 * 새로운 Complex 인스턴스를 만들어 반환한다.
 * --> 피연산자에 함수를 적용해 그 결과를 반환하지만, 피연산자 자체는 그대로인 패턴은 = 함수형 프로그래밍 이라고 한다.
 * 이와 달리 절차적 or 명령형 프로그래밍에서는 메서드에서 피연산자인 자신을 수정하여 자신의 상태가 바뀌게 됨.
 *
 * 함수형 프로그래밍을 통해 불변의 영역이 높아지는 이점을 누릴 수 있다.
 * 불변 객체는 단순성을 가진다. -> 생성된 시점의 상태를 파괴될 때까지 간직한다.
 * 불변 객체는 근본적으로 스레드 안전하다. (훼손 안됨)
 * 불변 객체는 최대한 재활용하라.
 *
 * 불변 클래스는 자주 사용되는 인스턴스를 Caching 하여 같은 인스턴스를 중복 생성되지 않도록 static factory를 제공한다.
 */
public final class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double realPart() {
        return re;
    }

    public double imaginaryPart() {
        return im;
    }

    public Complex plus(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    public Complex minus(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Complex))
            return false;
        Complex c = (Complex) obj;

        // == 대신 compare를 사용한다. (이유는 이펙티브 자바 63쪽 확인)
        return Double.compare(c.re, re) == 0 && Double.compare(c.im, im) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }

    @Override
    public String toString() {
        return "Complex{" +
                "re=" + re +
                ", im=" + im +
                '}';
    }
}
