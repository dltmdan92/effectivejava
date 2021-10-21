package ClassAndInterface.immutability;

/**
 * 불변 클래스를 만드는 또 다른 방법
 * 생성자를 private으로 and public 정적 팩터리를 제공하는 방법
 */
public class Complex2 {
    private final double re;
    private final double im;

    private Complex2(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex2 valueOf(double re, double im) {
        return new Complex2(re, im);
    }
}
