package ObjectCreationDescruction.cleaner;

public class Adult {
    public static void main(String[] args) {
        // GOOD CASE
        // 이렇게 try with resource로 감쌌으므로 clean 로직이 실행 될 것이다.
        try(Room myRoom = new Room(7)) {
            System.out.println("안녕~");
        }

        // BAD CASE
        // clean 로직이 실행되지않는다. (실행하면 "방 청소" 가 출력되지 않을 것임)
        // cleaner 동작은 보장되지 않는다.!!!
        // System.gc()를 추가해서 clean 로직 및 "방 청소"가 출력될 수 있지만, 이것도 보장되지 않는다!!
        new Room(99);
        System.out.println("아무렴");
    }
}
