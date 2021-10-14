package ObjectCreationDescruction.trywithresource;

import java.io.*;

/**
 * try-finally : 더 이상 자원을 회수하는 최선의 방책이 아님!
 */
public class TryFinally {
    private static int BUFFER_SIZE = 100;

    static String firstLineOfFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        // br.readLine()가 Exception이 발생하면
        // br.close()도 Exception이 발생하게 되며, 이게 앞의 Exception을 잡아먹어서 stackTrace가 남지 않게 된다.
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    // 매우 보기 안좋아진다..
    static void copy(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

}
