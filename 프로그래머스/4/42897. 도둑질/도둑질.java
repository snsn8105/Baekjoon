import java.util.*;
class Solution {
    public int solution(int[] money) {
        // 인접한 집은 털 수 없음
        // canSteal -> true면 가능, false면 못턺
        
        boolean[] canSteal = new boolean[money.length];
        
        // 첫번째 집을 터는 dp1
        int[] dp1 = new int[money.length];
        dp1[0] = money[0];
        dp1[1] = dp1[0];
        for (int i = 2 ; i < money.length - 1 ; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
        }
        // 마지막 집은 털 수 없음
        dp1[money.length-1] = dp1[money.length-2];
        
        // 첫번째 집 안터는 dp2
        int[] dp2 = new int[money.length];
        dp2[0] = 0;
        dp2[1] = money[1];
        
        for (int i = 2 ; i < money.length ; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        }
        
        int answer = Math.max(dp1[money.length - 1], dp2[money.length - 1]);
        return answer;
    }
}