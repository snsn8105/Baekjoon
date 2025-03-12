import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int i = 0; i < T; i++) {
            String input = br.readLine(); // 한 줄 입력 받기
            
            LinkedList<Character> password = new LinkedList<>();
            ListIterator<Character> cursor = password.listIterator(); // 커서 역할 수행

            for (char c : input.toCharArray()) {
                if (c == '<') { // 왼쪽 이동
                    if (cursor.hasPrevious()) {
                        cursor.previous();
                    }
                } else if (c == '>') { // 오른쪽 이동
                    if (cursor.hasNext()) {
                        cursor.next();
                    }
                } else if (c == '-') { // 백스페이스
                    if (cursor.hasPrevious()) {
                        cursor.previous();
                        cursor.remove();
                    }
                } else { // 일반 문자 입력
                    cursor.add(c);
                }
            }

            // 결과 출력
            StringBuilder sb = new StringBuilder();
            for (char ch : password) {
                sb.append(ch);
            }
            System.out.println(sb.toString());
        }
    }
}
