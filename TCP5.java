import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCP5{
    public static void main(String[] args) throws Exception{
        Socket mySocket = new Socket("36.50.135.242",2207);
        mySocket.setSoTimeout(5000);

        DataInputStream in = new DataInputStream(mySocket.getInputStream());
        DataOutputStream out = new DataOutputStream(mySocket.getOutputStream());

        out.writeUTF("B23DCCN419;pIhmM1wX");
        out.flush();

        int a = in.readInt();
        int b = in.readInt();
        int sum = a + b;
        int tich = a * b;

        out.writeInt(sum);
        out.flush();
        out.writeInt(tich);
        out.flush();

    }
}