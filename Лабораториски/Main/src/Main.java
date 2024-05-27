import java.util.Arrays;
import java.util.Scanner;

class QuarterlySales {

    private int numOfSales;
    private int [] revenues;
    private int quarterNo;

    public QuarterlySales(int numOfSales, int[] revenues, int quarterNo) {
        this.numOfSales = numOfSales;
        this.revenues = revenues;
        this.quarterNo = quarterNo;
    }
    @Override
    public String toString() {
        return "numOfSales";
    }

    public int getNumOfSales() {
        return numOfSales;
    }
    public int getRevenues(int n){
        return revenues[n];
    }
    public void setNumOfSales(int numOfSales) {
        this.numOfSales = numOfSales;
    }
}

class SalesPerson {

    private String name;
    private QuarterlySales [] quarters;

    public SalesPerson(String name, QuarterlySales[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }
    @Override
    public String toString() {
        return name;
    }
    int sumSales(SalesPerson sp){
        int num=0;
        for(int i=0; i<4; i++){
            for(int j=0; j<sp.quarters[i].getNumOfSales(); j++)
                num+=sp.quarters[i].getRevenues(j);
        }
        return num;
    }
    int zbir(int n){
        int num=0;
        for(int i=0; i<quarters[n].getNumOfSales(); i++){
            num+=quarters[n].getRevenues(i);
        }
        return num;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setQuarters(QuarterlySales[] quarters) {
        this.quarters = quarters;
    }
}



public class Main {
    public static SalesPerson salesChampion(SalesPerson [] arr)
    {
        int n=arr.length, max=0, index=0;
        for(int i=0; i<n; i++){
            if(arr[i].sumSales(arr[i])>max){
                max=arr[i].sumSales(arr[i]);
                index=i;
            }
        }
        return arr[index];
    }
    public static void table(SalesPerson [] arr)
    {
        System.out.println("SP   1   2   3   4   Total");
        for(int i=0; i< arr.length; i++){
            System.out.print(arr[i].getName()+"   ");
            for(int j=0; j<4; j++){
                System.out.print(arr[i].zbir(j)+"   ");
            }
            System.out.print(arr[i].sumSales(arr[i]));
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        SalesPerson [] arr = new SalesPerson[n];
        for(int i=0;i<n;i++)
        {
            //your code goes here
            String name=input.next();
            //arr[i].setName(name);
            QuarterlySales []qs=new QuarterlySales[4];
            for(int y=0; y<4; y++){
                int brojka=input.nextInt();
                int []prodazba=new int[brojka];
                for(int j=0; j<brojka; j++){
                    prodazba[j]=input.nextInt();
                }
                qs[y]=new QuarterlySales(brojka, prodazba, y+1);
            }
            arr[i]=new SalesPerson(name, qs);
        }

        table(arr);
        System.out.println("SALES CHAMPION: " + salesChampion(arr).getName());

    }
}