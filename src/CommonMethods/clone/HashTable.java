package CommonMethods.clone;


public class HashTable implements Cloneable{
    private Entry[] buckets;


    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        public Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        Entry deepCopy() {
            // 이거를 재귀호출 시 Stack overflow가 발생할 위험이 있다.
            //return new Entry(key, value, next == null ? null : next.deepCopy());

            // StackOverflow를 피하기 위해 deepCopy를 재귀호출 대신 반복자를 써어 순회하는 방향으로 수정.
            Entry result = new Entry(key, value, next);
            for (Entry p = result; p.next != null; p = p.next)
                p.next = new Entry(p.next.key, p.next.value, p.next.next);
            return result;
        }
    }

    /**
     * 잘못된 clone 메서드 - 가변 상태를 공유한다!
     *
     * 복제본은 자신만의 버킷 배열을 갖지만, 이 배열은 원본과 같은 list를 참조하여 원본과 복제본 모두 예기치 않게 동작할 가능성이 생김.
     * 이를 해결하려면 각 버킷을 구성하는 연결 리스트를 복사해야한다.
     */
    public HashTable wrongClone() {
        try {
            HashTable result = (HashTable) super.clone();
            result.buckets = buckets.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * 연결 리스트를 복사해서 해결하도록 한다.
     * 그리고 element들을 실제 객체 생성(deepCopy)해서 넣어준다. (깊은 복사)
     *
     * BUT loop문에서 buckets 리스트의 원소 수 만큼 stack frame을 소비하여, 리스트가 길면 Stack Overflow를 일으킬 위험이 있다.
     * 이 문제를 피하려면 deepCopy를 재귀호출 대신 반복자를 써어 순회하는 방향으로 수정해야 한다.
     */
    @Override
    public HashTable clone() {
        try {
            HashTable result = (HashTable) super.clone();
            result.buckets = new Entry[buckets.length];

            /*for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != null)
                    result.buckets[i] = buckets[i].deepCopy();
            }*/

            int i = 0;
            for (Entry entry = buckets[0].deepCopy(); entry.next != null; entry = entry.next) {
                result.buckets[i] = entry;
                i++;
            }

            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
