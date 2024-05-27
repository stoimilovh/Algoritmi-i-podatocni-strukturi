import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class CheckXML {

    public static boolean check(String []redovi){
        Stack<String>stack=new Stack<>();
        for (String red:redovi) {
            if(red.startsWith("[/")){
                if(stack.isEmpty()||!stack.pop().equals(red.substring(2))){
                    return false;
                }
            }
            else if(red.startsWith("[")){
                stack.push(red.substring(1));
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        int valid;

        // Vasiot kod tuka
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni
        boolean flag=check(redovi);
        if(flag){
            valid=1;
        }
        else {
            valid=0;
        }

        System.out.println(valid);

        br.close();
    }
}