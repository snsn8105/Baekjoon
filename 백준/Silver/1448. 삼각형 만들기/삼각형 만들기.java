import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];

        for(int i = 0 ; i < N ; i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(A); // 길이를 오름차순 정렬

        int maxSum = -1;

        // 가장 긴 변을 i로 설정 (거꾸로 탐색)
        for(int i = N-1; i >= 2; i--){ // 최소 세 개의 변이 있어야 삼각형을 만들 수 있음
            int a = A[i];   // 가장 긴 변
            int b = A[i-1]; // 두 번째로 긴 변
            int c = A[i-2]; // 세 번째로 긴 변

            if(a < (b + c)){ // 삼각형 조건 만족
                maxSum = a + b + c;
                break; // 가장 큰 값을 찾으면 종료
            }
        }

        System.out.println(maxSum);
    }
}
