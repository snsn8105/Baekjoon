import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int a, b;
        int count;

        boolean[] isPrime = new boolean[N+1];

        // 1. 소수 리스트 생성
        for (int i = 1; i <= N; i++) {
            isPrime[i] = true;
        }

        for(int i = 2 ; i*i <= N ; i++){
            if(isPrime[i]){
                for(int j = i*i ; j<=N ; j+= i){
                    isPrime[j] = false;
                }
            }
        }
        int[] primeCount = new int[N+1];
        for(int i = 1 ; i<=N ; i++){
            int plus = 0;
            if(isPrime[i]){
                plus = 1;
            }
            primeCount[i] = primeCount[i-1] + plus;
        }

        // 2. 쿼리 처리

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            count = primeCount[b] - (a > 1 ? primeCount[a - 1] : 0); // a = 1인 경우 처리
            bw.write(count + "\n");
        }

        bw.flush();
        bw.close();
    }
}
