/* ************************************************************************** */
/*                                                                            */
/*                                                      :::    :::    :::     */
/*   Problem Number: 2164                              :+:    :+:      :+:    */
/*                                                    +:+    +:+        +:+   */
/*   By: snsn8105 <boj.kr/u/snsn8105>                +#+    +#+          +#+  */
/*                                                  +#+      +#+        +#+   */
/*   https://boj.kr/2164                           #+#        #+#      #+#    */
/*   Solved: 2025/08/08 16:53:35 by snsn8105      ###          ###   ##.kr    */
/*                                                                            */
/* ************************************************************************** */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = 0 ;
        N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();
        
        for(int i = 0; i < N ; i++){
            queue.add(i+1);
        }

        int ans = 0;
        ans = Shuffle(queue, N);

        System.out.println(ans);
    }

    public static int Shuffle(Queue<Integer> queue, int N){
        int tmp1 = 0;
        int tmp2 = 0;
        while(true){
            tmp1 = queue.poll();

            if(queue.isEmpty()){
                return tmp1;
            } else{
                tmp2 = queue.poll();
                queue.add(tmp2);
            }
        }
    }
}