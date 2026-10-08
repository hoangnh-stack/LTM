import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class TCP2{
    public static void main(String[] args) throws Exception{
        Socket mySocket = new Socket("36.50.135.242",2206);
        mySocket.setSoTimeout(5000);

        InputStream in = mySocket.getInputStream();
        OutputStream out = mySocket.getOutputStream();

        out.write("B23DCCN419;qLdXmbUA".getBytes());
        out.flush();

        byte[] buffer = new byte[4096];
        int n = in.read(buffer);
        String s = new String(buffer,0,n,"UTF-8");

        String[] words = s.trim().split(",");
        ArrayList<Integer> ds = new ArrayList<>();
        for(String x : words){
            ds.add(Integer.parseInt(x));
        }
        Collections.sort(ds);
        int so1=0,so2=0,min=999999999;
        for(int i =0;i<ds.size()-1;i++){
            if(ds.get(i + 1) -ds.get(i)<min){
                min = ds.get(i+1) - ds.get(i);
                so1 = ds.get(i);
                so2 = ds.get(i+1);
            }
        }
        String res = String.valueOf(min) + "," + String.valueOf(so1) + "," + String.valueOf(so2);

        out.write(res.getBytes(StandardCharsets.UTF_8));
        out.flush();

    }
}