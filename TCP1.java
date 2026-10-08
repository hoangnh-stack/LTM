import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TCP1 {
    public static void main(String[] args) throws Exception {
        Socket mySocket = new Socket("36.50.135.242",2208);
        mySocket.setSoTimeout(5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()));

        out.write("B23DCCN419;tlbqKftD");
        out.newLine();
        out.flush();

        String s = in.readLine();
        String[] emails= s.trim().split(",\\s*");
        String res = "";
        for(String x : emails){
            if(x.endsWith(".edu")){
                res += x + ", ";
            }
        }
        res = res.substring(0,res.length()-2);

        out.write(res);
        out.newLine();
        out.flush();
    }
}