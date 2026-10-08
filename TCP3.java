import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.TreeMap;

public class TCP3{
    public static void main(String[] args) throws Exception{
        Socket mySocket = new Socket("36.50.135.242",2206);
        mySocket.setSoTimeout(5000);

        InputStream in = mySocket.getInputStream();
        OutputStream out = mySocket.getOutputStream();

        out.write("B23DCCN419;pgZnlSBs".getBytes());
        out.flush();

        byte[] buffer = new byte[4096];
        int n = in.read(buffer);
        String s = new String(buffer,0,n,"UTF-8");

        String[] words = s.trim().split(",");
        TreeMap<Integer,Integer> mp = new TreeMap<>();
        for(int i=0;i<words.length;i++) {
            mp.merge(Integer.parseInt(words[i]),i,Integer::sum);
        }

        int second = mp.lowerKey(mp.lastKey());
        int locate2 = mp.get(second);
        String res = String.valueOf(second) + "," + String.valueOf(locate2);

        out.write(res.getBytes(StandardCharsets.UTF_8));
        out.flush();

    }
}