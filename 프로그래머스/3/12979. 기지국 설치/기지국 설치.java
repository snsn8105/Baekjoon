class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int range = w*2 + 1;
        // stations로 구간을 분리해서 설치가 필요한 칸에 설치?
        // w만큼씩 건너뛰면서 설치
        // 길이 / (w * 2) 로 바로 계산, 나누어 떨어지지 않으면 올림
        
        // 1, 2, 3, 4, 5, 6, 7, 8, 9
        for (int i = 0 ; i < stations.length ; i++) {
            // 0일땐 앞 뒤 모두 계산
            int length = 0;
            int end = 0;
            if (i == 0) {
                end = stations[i] - w - 1;
                length = end;
                
                if (length > 0) {
                    if (length % range == 0) {
                        answer += length / range;
                    } else {
                        answer += length / range + 1;
                    }
                }
                
            }
            
            // 뒤쪽 계산
            if (i < stations.length - 1) {
                end = stations[i+1];
                length = end - stations[i] - 2 * w - 1; 
            } else {
                end  = n;
                length = end - stations[i] - w;
            }
            
            if (length > 0) {
                if (length % range == 0) {
                    answer += length / range;
                } else {
                    answer += length / range + 1;
                }
            }
        }
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return answer;
    }
}