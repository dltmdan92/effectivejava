package ObjectCreationDescruction.cleaner;

import java.lang.ref.Cleaner;

/**
 * cleaner를 안전망으로 활용하는 AutoCloseable 클래스
 *
 * finalizer 뿐만 아니라 cleaner 또한 가비지 처리를 함에 있어, 불 확실성 및 성능 저하 이슈를 갖고 있기 때문에 쓰는 것을 지양한다.
 * BUT cleaner를 auto close의 안전망으로 사용할 수는 있다.
 */
public class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();

    // 청소가 필요한 자원, 절대 Room을 참조해서는 안된다!!
    // Room을 참조할 경우, 순환참조가 생겨 GC가 Room 인스턴스를 회수해갈 기회가 오지 않는다.
    // 이 때문에 static 클래스로 만들었음.
    private static class State implements Runnable {
        int numJunkPiles; // 방 (Room) 안의 쓰레기 수

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        // 호출 케이스는 두 개
        // 1. Room.close 메서드에서 Cleanable의 clean을 호출하면 run도 호출하게 된다.
        // 2. 혹은 GC가 Room을 회수 할 때까지 클라이언트가 close를 호출하지 않는다면, cleaner가 State의 run을 호출한다.
        @Override
        public void run() {
            System.out.println("방 청소");
            numJunkPiles = 0;
        }
    }

    // 방의 상태, cleanable과 공유한다.
    private final State state;

    // cleanable 객체, 수거 대상이 되면 방을 청소한다.
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() {
        cleanable.clean();
    }
}
