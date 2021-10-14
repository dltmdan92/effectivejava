package ObjectCreationDescruction.trywithresource;

import java.io.*;

/**
 * java 7에서 등장한 try-with-resources
 * AutoCloseable을 구현해야 한다.
 */
public class TryWithResources {
    private static int BUFFER_SIZE = 100;

    static String firstLineOfFile(String path) throws IOException {
        // readLine과 close 둘다 예외가 발생하면, close에서 발생한 에러는 숨겨지고
        // readLine에서 발생한 예외가 기록된다.
        // BUT 숨겨진 예외들도 그냥 버려지지는 않고, 스택 추적 내역에 '숨겨졌다(suppressed)'라는 꼬리표를 달고 출력된다.
        // 또한 자바 7에서 Throwable에 추가된 getSuppressed 메서드를 통해 프로그램 코드에서도 가져올 수 있다.
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    // 훨씬 깔끔해졌다.
    static void copy(String src, String dst) throws IOException {

        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst);) {

            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }

    static String firstLineOfFile(String path, String defaultVal) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultVal;
        }
    }
}
