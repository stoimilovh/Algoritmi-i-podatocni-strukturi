import java.util.Scanner;

class SLLNode<E>{
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E element, SLLNode<E> succ) {
        this.element = element;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
class SLL<E>{
    protected SLLNode<E> first;

    public SLL(SLLNode<E> first) {
        this.first = first;
    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public void insertFirst(E o){
        SLLNode<E>ins=new SLLNode<>(o, first);
        first=ins;
    }
    public void insertBefore(E o, SLLNode<E> before){
        if(first!=null){
            SLLNode<E>tmp=first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            while(tmp.succ!=before&&tmp.succ.succ!=null){
                tmp=tmp.succ;
            }
            if(tmp.succ==before){
                tmp.succ=new SLLNode<E>(o, before);
            }
            else{
                System.out.println("Elementot ne postoi");
            }
        }
        else{
            System.out.println("Listata e prazna");
        }
    }
    public void insertAfter(E o, SLLNode<E> node){
        if(first!=null){
            SLLNode<E>ins=new SLLNode<>(o, node.succ);
            node.succ=ins;
        }
        else{
            System.out.println("Listata e prazna");
        }
    }
    public void insertLast(E o){
        if(first!=null){
            SLLNode<E>tmp=first;
            while(tmp.succ!=null){
                tmp=tmp.succ;
            }
            tmp.succ=new SLLNode<>(o, null);
        }
        else{
            first = new SLLNode<>(o, null);
        }
    }
    public E deleteFirst(){
        if(first!=null){
            SLLNode<E>tmp=first;
            first=first.succ;
            return tmp.element;
        }
        else{
            System.out.println("Listata e prazna");
            return null;
        }
    }
    public E delete(SLLNode<E>node){
        if(first!=null){
            SLLNode<E>tmp=first;
            if(first==node){
                return this.deleteFirst();
            }
            while(tmp.succ!=node&&tmp.succ.succ!=null){
                tmp=tmp.succ;
            }
            if(tmp.succ==node){
                tmp.succ=tmp.succ.succ;
                return node.element;
            }
            else{
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        }
        else{
            System.out.println("Listata e prazna");
            return null;
        }
    }
    public int size(){
        int brojach=0;
        SLLNode<E>tmp=first;
        while(tmp!=null){
            brojach++;
            tmp=tmp.succ;
        }
        return brojach;
    }
    public SLLNode<E> find(E o){
        if(first!=null){
            SLLNode<E>tmp=first;
            while(!tmp.element.equals(o)&&tmp.succ!=null){
                tmp=tmp.succ;
            }
            if(tmp.element.equals(o)){
                return tmp;
            }
            else{
                System.out.println("Elementot ne postoi");
            }
        }
        else{
            System.out.println("Listata e prazna");
        }
        return null;
    }
    public void merge(SLL<E>node){
        if(first!=null){
            SLLNode<E>tmp=first;
            while (tmp.succ!=null){
                tmp=tmp.succ;
            }
            tmp.succ=node.getFirst();
        }
        else{
            first=node.getFirst();
        }
    }
    public void mirror(){
        if(first!=null){
            SLLNode<E>prev=null;
            SLLNode<E>current=first;
            SLLNode<E>next;
            while(current!=null){
                next=current.succ;
                current.succ=prev;
                prev=current;
                current=next;
            }
            first=prev;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        SLLNode<E>current=first;
        while(current!=null){
            sb.append(current.toString());
            current=current.succ;
            if(current!=null){
                sb.append(" - ");
            }
        }
        return sb.toString();
    }
}
public class Animals {
    public static void putWordsTogether(SLL<String> lista1){
        SLLNode<String>pocetok=lista1.getFirst();
        SLL<String>krajna=new SLL<>(null);
        while (pocetok!=null && pocetok.element!=null){
            if(!pocetok.element.equals(",")){
                String word="";
                while(!pocetok.element.equals(",")&&pocetok.element!=null&&pocetok!=null){
                    word+=pocetok.element;
                    pocetok=pocetok.succ;
                    if(pocetok==null){
                        break;
                    }
                }
                krajna.insertLast(word);
            }
            else if (pocetok.element!=null){
                pocetok = pocetok.succ;
            }
        }
        lista1.first=krajna.first;
    }

    public static void main(String[] args) {
        SLL<String>list1=new SLL<>(null);
        Scanner sc=new Scanner(System.in);
        //int n=sc.nextInt();

        list1.insertLast("C");
        list1.insertLast("A");
        list1.insertLast("T");
        list1.insertLast(",");
        list1.insertLast("D");
        list1.insertLast("O");
        list1.insertLast("G");
        list1.insertLast(",");
        list1.insertLast("C");
        list1.insertLast("O");
        list1.insertLast("W");

        System.out.println(list1);

        putWordsTogether(list1);

        System.out.println("------------------------");

        System.out.println(list1);

    }
}
