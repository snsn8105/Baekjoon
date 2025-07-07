/* ************************************************************************** */
/*                                                                            */
/*                                                      :::    :::    :::     */
/*   Problem Number: 10870                             :+:    :+:      :+:    */
/*                                                    +:+    +:+        +:+   */
/*   By: snsn8105 <boj.kr/u/snsn8105>                +#+    +#+          +#+  */
/*                                                  +#+      +#+        +#+   */
/*   https://boj.kr/10870                          #+#        #+#      #+#    */
/*   Solved: 2025/07/07 11:07:43 by snsn8105      ###          ###   ##.kr    */
/*                                                                            */
/* ************************************************************************** */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if(n == 0){
            System.out.println(0);
            return;
        }
        int[] fib = new int[n+1];
        fib[0] = 0;
        fib[1] = 1;
        for(int i = 2 ; i<=n ; i++){
            fib[i] = fib[i-1] + fib[i-2];
        }

        System.out.println(fib[n]);
    }
}