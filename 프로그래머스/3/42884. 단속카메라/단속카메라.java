import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        
        int answer = 1;
        int lastCam = routes[0][1];
        // 가장 뒤에 있는 카메라의 위치를 저장하면서 거기에 포함되는지 확인
        for (int[] route : routes) {
            if (route[0] <= lastCam) {
                continue;
            }
            
            lastCam = route[1];
            answer++;
        }
        
        return answer;
    }
}