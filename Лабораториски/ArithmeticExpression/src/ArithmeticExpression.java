import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        // Vasiot kod tuka
        Stack<Character>znaci=new Stack<>();
        Stack<Integer>broevi=new Stack<>();
        int a, b, broj;
        char znak;
        for(int i=0; i<c.length; i++){
            if(Character.isDigit(c[i])){
                broevi.push(Character.getNumericValue(c[i]));
            }
            else if(c[i]=='+'||c[i]=='-'){
                znaci.push(c[i]);
            }
            else if(c[i]==')'){
                b=broevi.pop();
                a=broevi.pop();
                znak=znaci.pop();
                broj=operacija(a, b, znak);
                broevi.push(broj);
            }
        }
        return broevi.pop();
    }

    public static int operacija(int a, int b, char c){
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

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
