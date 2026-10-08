import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.*;

public class TCP8 {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2210);

        GZIPInputStream gzIn = new GZIPInputStream(socket.getInputStream());
        GZIPOutputStream gzOut = new GZIPOutputStream(socket.getOutputStream(), true);

        gzOut.write(("B23DCCN419;fXCPrkOJ\n").getBytes());
        gzOut.flush();

        // can moi du lieu tra ve
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int c;
        while ((c = gzIn.read()) != '\n') {
            baos.write(c);
        }
        String s = baos.toString();

        ArrayList<Character> list = new ArrayList<>();
        for(char x: s.toCharArray()) {
            list.add(x);
        }
        Collections.sort(list);
        String res = "";
        for(char x: list){
            res += x;
        }

        String b64 = Base64.getEncoder().encodeToString(res.getBytes());

        String result = res + "\n";

        gzOut.write(result.getBytes());
        gzOut.flush();

        socket.close();
    }
}