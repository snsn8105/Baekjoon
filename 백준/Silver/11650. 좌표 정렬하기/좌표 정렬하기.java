import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    int x, y;
    public Point(int x, int y) { this.x = x; this.y = y; }
    @Override
    public int compareTo(Point o) {
        if (this.x != o.x) return this.x - o.x;
        return this.y - o.y;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Point[] arr = new Point[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Point(Integer.parseInt(st.nextToken()),
                               Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(arr);  // O(N log N), 내부 최적화된 정렬

        StringBuilder sb = new StringBuilder();
        for (Point p : arr) {
            sb.append(p.x).append(' ').append(p.y).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }
}
