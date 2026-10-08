import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.*;

public class TCP7 {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2210);

        GZIPInputStream gzIn = new GZIPInputStream(socket.getInputStream());
        GZIPOutputStream gzOut = new GZIPOutputStream(socket.getOutputStream(), true);

        gzOut.write(("B23DCCN419;q7jzTo3W\n").getBytes());
        gzOut.flush();

        // can moi giu lieu ZIP
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int c;
        while ((c = gzIn.read()) != '\n') {
            baos.write(c);
        }
        String s = baos.toString();

        String rev = new StringBuilder(s).reverse().toString();

        String b64 = Base64.getEncoder().encodeToString(rev.getBytes());

        String res = rev + "|" + b64 + "\n";

        gzOut.write(res.getBytes());
        gzOut.flush();

        socket.close();
    }
}