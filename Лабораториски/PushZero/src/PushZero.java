import java.util.Arrays;
import java.util.Scanner;

public class PushZero {
    static void pushZerosToBeginning(int arr[], int n)
    {
        int []pomosna=new int[n];
        int j=0, h=0;
        for(int i=0; i<n; i++){
            if(arr[i]==0){
                j++;
            }
        }
        for(int i=0; i<n; i++){
            if(arr[i]==0){
                pomosna[h]=arr[i];
                h++;
            }
            if(arr[i]!=0){
                pomosna[j]=arr[i];
                j++;
            }
        }
        System.out.println("Transformiranata niza e: ");
        for(int i=0; i<n; i++){
            System.out.print(pomosna[i]+" ");
        }
    }

    public static void main (String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int []niza=new int [n];
        for(int i=0; i<n; i++){
            niza[i]= sc.nextInt();
        }
        pushZerosToBeginning(niza, n);
    }
}