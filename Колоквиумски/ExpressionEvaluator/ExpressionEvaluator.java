//Да се напише алгоритам кој ќе пресметува (евалуира) математички израз составен од броеви и операциите за собирање (+) и множење (*).
//
//        Забелешка: Операцијата множење има предност пред операцијата собирање.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression){
        // Vasiot kod tuka
        Stack<Integer>broevi=new Stack<>();
        Stack<Character>znaci=new Stack<>();
        Stack<Integer>zbir=new Stack<>();
        int a, b, h=0, f=0, num=0;
        char c;
        for(int i=0; i<expression.length(); i++){
            c=expression.charAt(i);
            if(Character.isDigit(expression.charAt(i))){
                num=0;
                while (i<expression.length()&&Character.isDigit(expression.charAt(i))){
                    num=num*10+Character.getNumericValue(expression.charAt(i));
                    i++;
                }
                i--;
                broevi.push(num);
            }
            else{
                znaci.push(c);
            }
        }
        while(!broevi.isEmpty()){
            a=broevi.pop();
            if(znaci.isEmpty()){
                zbir.push(a);
                break;
            }
            c=znaci.pop();
            if(c=='+'||c=='-'){
                zbir.push(a);
            }
            else{
                b=broevi.pop();
                h=a*b;
                broevi.push(h);
            }
        }
        while(!zbir.isEmpty()){
            f+=zbir.pop();
        }
        return f;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}