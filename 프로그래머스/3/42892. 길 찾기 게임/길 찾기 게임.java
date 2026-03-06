import java.util.*;
class Node{
    int x;
    int y;
    int id;
    Node left;
    Node right;
    
    public Node(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
}
class Solution {
    public void insertNode(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null)
                parent.left = child;
            else
                insertNode(parent.left, child);
        }
        else {
            if (parent.right == null)
                parent.right = child;
            else
                insertNode(parent.right, child);   
        }
    }
    
    int idx = 0;
    // 전위 순회
    public void preOrder(Node n, int[][] answer) {
        if (n == null)
            return;
        answer[0][idx] = n.id;
        idx++;
        preOrder(n.left, answer);
        preOrder(n.right, answer);
        
        return;
    }
    // 후위 순회
    public void postOrder(Node n, int[][] answer) {
        if (n == null)
            return;
        
        postOrder(n.left, answer);
        postOrder(n.right, answer);
        answer[1][idx] = n.id;
        idx++;
        return;
    }
    public int[][] solution(int[][] nodeinfo) {
        // Node 클래스에 저장
        Node[] newnodeinfo = new Node[nodeinfo.length];
        
        int index = 0;
        for (int[] ni : nodeinfo) {
            Node n = new Node(ni[0], ni[1], index+1);
            newnodeinfo[index] = n;
            index++;
        }
        // y 내림차순, y가 같으면 x 오름차순으로 정렬
        Arrays.sort(newnodeinfo, new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                if (a.y == b.y)
                    return a.x - b.x;
                return b.y - a.y;
            }
        });
        
        Node root = newnodeinfo[0];
        
        for (int i = 1 ; i < newnodeinfo.length ; i++) {
            insertNode(root, newnodeinfo[i]);
        }
        
        int[][] answer = new int[2][nodeinfo.length];

        // 전위 순회
        idx = 0;
        preOrder(root, answer);
        
        // 후위 순회
        idx = 0;
        postOrder(root, answer);
        
        return answer;
    }
}