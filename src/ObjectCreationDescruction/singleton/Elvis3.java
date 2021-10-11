package ObjectCreationDescruction.singleton;

// 방법 3. enum을 통해 리플렉션 공격도 완벽히 방어가능하다.
// 대부분 상황에서 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법
// BUT 싱글턴이 enum외에 클래스를 상속해야 한다면 이 방법은 불가하다.
public enum Elvis3 {
    INSTANCE;

    public void leaveTheBuilding() {
        // ...
    }
}
