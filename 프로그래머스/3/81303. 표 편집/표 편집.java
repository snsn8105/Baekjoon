import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        TreeSet<Integer> table = new TreeSet<>();
        Stack<Integer> removed = new Stack<>();

        // 모든 행을 TreeSet에 추가 (O(N log N))
        for (int i = 0; i < n; i++) {
            table.add(i);
        }

        int currentPoint = k;

        for (String s : cmd) {
            if (s.charAt(0) == 'U') { // 위로 이동 (O(log N))
                int move = Integer.parseInt(s.substring(2));
                while (move-- > 0) {
                    currentPoint = table.lower(currentPoint); // 삭제된 칸 무시하고 이동
                }
            } 
            else if (s.charAt(0) == 'D') { // 아래로 이동 (O(log N))
                int move = Integer.parseInt(s.substring(2));
                while (move-- > 0) {
                    currentPoint = table.higher(currentPoint); // 삭제된 칸 무시하고 이동
                }
            } 
            else if (s.equals("C")) { // 삭제 (O(log N))
                removed.push(currentPoint);
                table.remove(currentPoint);

                // 삭제 후 위치 이동 (가장 가까운 남아있는 행으로)
                if (table.higher(currentPoint) != null) {
                    currentPoint = table.higher(currentPoint);
                } else {
                    currentPoint = table.lower(currentPoint);
                }
            } 
            else if (s.equals("Z")) { // 복구 (O(log N))
                table.add(removed.pop());
            }
        }

        // 최종 결과 문자열 생성 (O(N))
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (table.contains(i)) sb.append("O");
            else sb.append("X");
        }

        return sb.toString();
    }
}
