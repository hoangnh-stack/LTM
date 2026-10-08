import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedHashMap;

public class TCP4{
    public static void main(String[] args) throws Exception{
        Socket mySocket = new Socket("36.50.135.242",2208);
        mySocket.setSoTimeout(5000);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));

        out.write("B23DCCN419;GWvmNbXu");
        out.newLine();
        out.flush();

        String s = in.readLine();
        String[] words = s.trim().split("\\s+");
        LinkedHashMap<Character,Integer> mp = new LinkedHashMap<>();
        for(String x : words){
            for(Character y : x.toCharArray()){
                mp.merge(y,1,Integer::sum);
            }
        }
        String res ="";
        for(Character y : mp.keySet()){
            if(mp.get(y)>1) res += y + ":" + mp.get(y) + ",";
        }

        out.write(res);
        out.newLine();
        out.flush();
    }
}