import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class PostFixEvaluation {

    static int evaluatePostfix(char [] izraz, int n)
    {
        ArrayStack<Integer>broevi=new ArrayStack<Integer>(100);
        //Stack<Character>znaci=new Stack<>();
        int num, a, b;
        char c;
        for(int i=0; i< izraz.length; i++){
            if(Character.isDigit(izraz[i])){
                num=0;
                while (izraz[i]!=' '&&i< izraz.length){
                    num=num*10+Character.getNumericValue(izraz[i]);
                    i++;
                }
                i--;
                broevi.push(num);
            }
            else if(izraz[i]!=' '){
                b=broevi.pop();
                a=broevi.pop();
                c=izraz[i];
                num=presmetaj(a, b, c);
                broevi.push(num);
            }
        }
        return broevi.pop();
    }

    public static int presmetaj(int a, int b, char c){
        if(c=='+'){
            return a+b;
        }
        if(c=='-'){
            return a-b;
        }
        if(c=='*'){
            return a*b;
        }
        if(c=='/'){
            return a/b;
        }
        return 0;
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = evaluatePostfix(exp, exp.length);
        System.out.println(rez);

        br.close();

    }

}