import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String input = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if (K == 1) {
                System.out.println(1 + " " + 1);
                continue;
            }

            int minLength = Integer.MAX_VALUE;
            int maxLength = -1;

            for (char c = 'a'; c <= 'z'; c++) {
                Deque<Integer> deque = new ArrayDeque<>();

                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) == c) {
                        deque.add(i);

                        if (deque.size() == K) {
                            int first = deque.peekFirst();
                            int last = deque.peekLast();
                            int len = last - first + 1;

                            minLength = Math.min(minLength, len);
                            maxLength = Math.max(maxLength, len);

                            deque.pollFirst(); // 다음 슬라이딩 윈도우 준비
                        }
                    }
                }
            }

            if (maxLength == -1)
                System.out.println(-1);
            else
                System.out.println(minLength + " " + maxLength);
        }
    }
}
