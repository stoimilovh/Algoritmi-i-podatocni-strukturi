import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    K key;
    E value;
    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }


    @Override
    public String toString() {
        return element.toString();
    }
}



class CBHT<K extends Comparable<K>, E> {
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }



    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }
}

class IP{
    String ip1;
    String ip2;
    String ip3;

    public IP(String ip1, String ip2, String ip3) {
        this.ip1 = ip1;
        this.ip2 = ip2;
        this.ip3 = ip3;
    }
}

public class RoutingHashJava {
    public static void main (String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        CBHT<String, ArrayList<IP>>router=new CBHT<String, ArrayList<IP>>(n*2);
        for (int i=0; i<n; i++){
            String key=br.readLine();
            String value=br.readLine();
            String []ips=value.split(",");
            ArrayList<IP> lista=new ArrayList<>();
            if(ips.length==0){
                String []ipsadd=value.split("\\.");
                IP ipad=new IP(ipsadd[0], ipsadd[1], ipsadd[2]);
                lista.add(ipad);
            }
            for(int j=0; j<ips.length; j++){
                String []ipsadd=ips[j].split("\\.");
                if(ipsadd.length>2){
                    IP ipad=new IP(ipsadd[0], ipsadd[1], ipsadd[2]);
                    lista.add(ipad);
                }
            }
            router.insert(key, lista);
        }
        int m=Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            String key=br.readLine();
            String value=br.readLine();
            boolean flag=false;
            if (router.search(key) != null) {
                String []ip=value.split("\\.");
                SLLNode<MapEntry<String,ArrayList<IP>>>tmp=router.search(key);
                ArrayList<IP>iplist=tmp.element.value;
                for (IP ipValue:iplist) {
                    if(ipValue.ip1.equals(ip[0])&&ipValue.ip2.equals(ip[1])&&ipValue.ip3.equals(ip[2])){
                        flag=true;
                        break;
                    }
                }
                if(flag){
                    System.out.println("postoi");
                }
                else {
                    System.out.println("ne postoi");
                }
            }
            else {
                System.out.println("ne postoi");
            }
        }
    }
}