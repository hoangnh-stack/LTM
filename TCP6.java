import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCP6 {
    public static void main(String[] args) throws Exception {
        Socket mySocket = new Socket("36.50.135.242",2207);
        mySocket.setSoTimeout(5000);

        DataInputStream in = new DataInputStream(mySocket.getInputStream());
        DataOutputStream out = new DataOutputStream(mySocket.getOutputStream());

        out.writeUTF("B23DCCN419;wTMHpl9X");
        out.flush();

        String Ceaser = in.readUTF();
        int s = in.readInt();
        String reCeaser = "";
        for (int i = 0; i < Ceaser.length(); i++) {
            char c = Ceaser.charAt(i);
            if (Character.isUpperCase(c)) {
                char x = (char) ((c - 'A' + s) % 26 + 'A');
                reCeaser += x;
            } else if (Character.isLowerCase(c)) {
                char x = (char) ((c - 'a' + s) % 26 + 'a');
                reCeaser += x;
            } else {
                reCeaser += c;
            }
        }

        out.writeUTF(reCeaser);
        out.flush();
    }
}