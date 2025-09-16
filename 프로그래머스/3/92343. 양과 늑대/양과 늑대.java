import java.util.*;

class Solution {
    static ArrayList<Integer>[] tree;
    static int[] nodeInfo;
    static int maxSheeps = 0;
    
    public int solution(int[] info, int[][] edges) {
        nodeInfo = info;
        tree = new ArrayList[info.length];
        
        for(int i = 0 ; i<info.length ; i++){
            tree[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            tree[edge[0]].add(edge[1]);
        }
        
        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.addAll(tree[0]); // 0번 노드의 자식들을 다음 방문 목록에 추가

        dfs(nextNodes, 1, 0);
        
        int answer = maxSheeps;
        return answer;
    }
    
    public void dfs(List<Integer> nextNodes, int sheeps, int wolves){
        List<Integer> newNextNodes;
        
        maxSheeps = Math.max(maxSheeps, sheeps);
        
        for(int node : nextNodes){
            int nextSheeps = sheeps;
            int nextWolves = wolves;
            
            if(nodeInfo[node] == 0)
                nextSheeps++;
            else
                nextWolves++;
            
            if(nextSheeps <= nextWolves)
                continue;
            
            newNextNodes = new ArrayList<>(nextNodes);
            newNextNodes.remove(Integer.valueOf(node));
            newNextNodes.addAll(tree[node]);
            dfs(newNextNodes, nextSheeps, nextWolves);
        }
    }
}