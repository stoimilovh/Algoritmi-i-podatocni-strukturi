import java.util.Scanner;

public class CountWordPairs {

    //TODO: implement function
    public static int countWordPairs(String [] words) {
        int n=words.length, brojach=0;
        for(int i=0; i<n; i++){
            if(i+1>=n){
                break;
            }
            for(int j=i+1; j<n; j++){
                if(words[i].charAt(0)==words[j].charAt(0)){
                    brojach++;
                }
            }
        }
        return brojach;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();

        String words[] = new String[N];

        for(int i=0;i<N;i++) {
            words[i] = input.next();
        }

        System.out.println(countWordPairs(words));

    }
}