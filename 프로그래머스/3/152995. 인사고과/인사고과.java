import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int[] wan = scores[0];
        int wanSum = wan[0] + wan[1];
        
        Arrays.sort(scores, new Comparator<int[]>() { 
            @Override 
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) return a[1] - b[1]; // 두 번째 점수는 오름차순
                return b[0] - a[0]; // 첫 번째 점수는 내림차순
            }
        });
        
        int answer = 1;
        int maxSecond = 0;
        for (int i = 0 ; i < scores.length ; i++) {
            if (maxSecond > scores[i][1])
                continue;
            if (scores[i][0] > wan[0] && scores[i][1] > wan[1])
                return -1;
            
            maxSecond = Math.max(scores[i][1], maxSecond);
            
            if (scores[i][0] + scores[i][1] > wanSum)
                answer++;
        }
        
        return answer;
    }
}