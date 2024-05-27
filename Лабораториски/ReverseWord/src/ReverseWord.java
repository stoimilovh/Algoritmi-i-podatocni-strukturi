import java.util.Scanner;

public class ReverseWord {

    public static void printReversed(String word) {
        int tmp=word.length();
        for(int i=tmp-1; i>=0; i--){
            System.out.print(word.charAt(i));
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        for(int i=0; i<n; i++){
            String zbor= sc.next();
            printReversed(zbor);
        }
    }
}
