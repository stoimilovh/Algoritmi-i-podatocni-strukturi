//На автобуската станица во ФинТаун имало N возрасни и M деца кои што сакале да патуваат
//        заедно за соседниот град МинТаун. Цената на еден билет е 100 денари. Ако некој
//        возрасен сака да патува со K деца, треба да плати еден билет за него и K-1 билети
//        за децата (за едно дете не мора да плаќа билет). Исто така, возрасен може да се вози
//        и сам, во тој случај ќе си плати еден билет за него. Дополнително знаеме дека децата
//        не можат да се возат без да се придружени од некој возрасен.
//
//        Во првиот ред од влезот е даден бројот N. Во вториот ред е даден бројот M. Потребно е
//        да пресметате колкав е минималниот и максималниот број на денари што може да ги платат
//        патниците за билети и да ги испечатите во две линии. Во автобусот ќе има најмалку еден возрасен.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        // Vasiot kod tuka
        int min=9999999, max=-1;
        if(N==0){
            min=max=0;
        }
        else if(M==0){
            max=min=N*100;
        }
        else if(N>0&&M==1){
            max=min=N*100;
        }
        else if(N>M){
            min=N*100;
            max=(N*100)+(M-1)*100;
        }
        else if(N<M){
            min=(N*100)+(M-N)*100;
            max=(N*100)+(M-1)*100;
        }
        System.out.println(min);
        System.out.println(max);
    }
}
