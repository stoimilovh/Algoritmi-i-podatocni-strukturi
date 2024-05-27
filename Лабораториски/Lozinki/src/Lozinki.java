import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class Lozinki {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<String, String> password=new HashMap<>();

        for(int i=1;i<=N;i++){
            String imelozinka = br.readLine();
            String[] pom = imelozinka.split(" ");

            password.put(pom[0], pom[1]);
        }

        String c=br.readLine();

        while (!c.equals("KRAJ")){
            String[] pom = c.split(" ");
            if(password.containsKey(pom[0])){
                String tmp= password.get(pom[0]);
                if(tmp.equals(pom[1])){
                    System.out.println("Najaven");
                    return;
                }
                else{
                    System.out.println("Nenajaven");
                }
            }
            else{
                System.out.println("Nenajaven");
            }

            c=br.readLine();
        }


    }
}
