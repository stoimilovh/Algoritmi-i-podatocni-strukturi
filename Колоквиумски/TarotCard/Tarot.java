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
            insertFirst(o);
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
public class Tarot {
    public static void izmena(SLL<Integer> lista1, SLL<Integer> lista2){
        SLLNode<Integer>l1=lista1.getFirst();
        SLLNode<Integer>l2=lista2.getFirst();
        lista2.insertLast(lista1.getFirst().element);
        lista1.delete(l1);
        lista1.insertLast(lista2.getFirst().element);
        lista2.delete(lista2.getFirst());
        l1=lista1.getFirst();
        SLLNode<Integer>prvaListaPrethoden=null;
        while(l1.succ.succ!=null){
            prvaListaPrethoden=l1;
            l1=l1.succ;
        }
        SLLNode<Integer>prvaListaZaVmetnuvanje=l1;
        while(l1.succ!=null){
            l1=l1.succ;
        }
        SLLNode<Integer>prvaListaPosleden=l1;
        l2=lista2.getFirst();
        int brojach=0;
        while (brojach<2){
            l2=l2.succ;
            brojach++;
        }
        SLLNode<Integer>tmp=l2.succ;
        l2.succ=prvaListaZaVmetnuvanje;
        prvaListaZaVmetnuvanje.succ=tmp;
        prvaListaPrethoden.succ=prvaListaPosleden;
    }

    public static void main(String[] args) {
        SLL<Integer>list1=new SLL<>(null);
        SLL<Integer>list2=new SLL<>(null);
        Scanner sc=new Scanner(System.in);
        //int n=sc.nextInt();

        list1.insertLast(6);
        list1.insertLast(7);
        list1.insertLast(8);
        list1.insertLast(9);
        list1.insertLast(1);
        list1.insertLast(2);

        list2.insertLast(5);
        list2.insertLast(4);
        list2.insertLast(3);
        list2.insertLast(2);
        list2.insertLast(1);
        list2.insertLast(9);

        System.out.println(list1.toString());
        System.out.println(list2.toString());

        izmena(list1, list2);

        System.out.println("------------------------");

        System.out.println(list1.toString());
        System.out.println(list2.toString());

    }
}
