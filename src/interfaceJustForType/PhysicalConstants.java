package interfaceJustForType;

/**
 * 상수 인터페이스 안티패턴 - 사용금지!!
 *
 * 클래스 내부에서 사용하는 상수는 외부 인터페이스가 아니라, 내부 구현에 해당한다.
 * 따라서 상수 인터페이스를 구현하는 것은, 이 내부 구현을 클래스의 API로 노출하는 행위.
 *
 * 또한 하위클래스가 아래 상수들을 사용하는 경우, interface의 내부 구현에 따라 영향을 받게 되는 위험도 존재
 */
public interface PhysicalConstants {
    // 아보가드로 수 (1/몰)
    static final double AVOGADROS_NUMBER = 6.022_140_857e23;

    // 볼츠만 상수 (J/K)
    static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;

    // 전자 질량 (kg)
    static final double ELECTRON_MASS = 9.109_383_56e-31;
}
