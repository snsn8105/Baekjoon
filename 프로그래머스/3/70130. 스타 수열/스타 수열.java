import java.util.*;
class Solution {
    public int solution(int[] a) {
        int[][] counts = new int[a.length][2];
        for (int i = 0 ; i < counts.length ; i++) {
            counts[i][0] = i;
        }
        for (int c : a) {
            counts[c][1]++;
        }
        
        // 내림차순 정렬
        Arrays.sort(counts, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[1] - a[1];
            }
        });
        
        // 각 숫자를 스타 수열의 기준으로 두고 돌려보기
        int max = -1;
        for (int[] c : counts) {
            int target = c[0];
            int count = 0;
            
            if (max > c[1])
                break;
            for (int i = 0 ; i < a.length-1 ; ) {
                if (a[i] != a[i+1]) {
                    if (a[i] == target ) {
                        count++;
                        i += 2;
                    } else if (a[i+1] == target) {
                        count++;
                        i += 2;
                    } else
                        i++;
                } else
                    i++;
            }
            
            max = Math.max(max, count);
        }
        
        int answer = max*2;
        return answer;
    }
}