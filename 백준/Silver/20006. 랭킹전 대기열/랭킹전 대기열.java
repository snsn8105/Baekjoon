import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

class Player{
    int level;
    String name;

    public Player(int level, String name) {
        this.level = level;
        this.name = name;
    }

    
    
}
class PComparator implements Comparator<Player>{
    @Override
    public int compare(Player o1, Player o2) {
        String n1 = o1.name;
        String n2 = o2.name;

        return n1.compareToIgnoreCase(n2);
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 0번 player랑 비교하고 레벨 범위에 들어가면  같은 방에 추가, 아니면 새로운 방 생성
        ArrayList<ArrayList<Player>> list = new ArrayList<>(); 
        
        for(int i = 0 ; i<p ; i++){
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            
            boolean added = false;
            for (int j = 0; j < list.size(); j++) {
                ArrayList<Player> l = list.get(j);
                Player roomMaster = l.get(0);
                if (roomMaster.level + 10 >= level && roomMaster.level - 10 <= level && l.size() < m) {
                    l.add(new Player(level, name));
                    added = true;
                    break;
                }
            }
            if (!added) {
                ArrayList<Player> newRoom = new ArrayList<>();
                newRoom.add(new Player(level, name));
                list.add(newRoom);
            }

            
        }
        for(ArrayList<Player> l : list){
            l.sort(new PComparator());
            if(l.size() == m)
                System.out.println("Started!");
            else
                System.out.println("Waiting!");

            for(Player player : l){
                System.out.println(player.level + " " +player.name);
            }
        }
    }
}
