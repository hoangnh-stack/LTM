import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Main{
    public static void main(String[] args) throws Exception {
        Socket mySocket = new Socket("36.50.135.242",2209);
        mySocket.setSoTimeout(5000);

        ObjectOutputStream out = new ObjectOutputStream(mySocket.getOutputStream());
        out.flush();
        ObjectInputStream in = new ObjectInputStream(mySocket.getInputStream());

        out.writeObject("B23DCCN419;jn6MB3ea");
        out.flush();

        Customer a = (Customer) in.readObject();

        String name = a.getName();

        // c) sinh username tu ten goc - phai lam TRUOC khi doi name
        a.setUserName(a.chUser(name));

        // a) chuan hoa ten
        a.setName(a.chTen(name));

        // b) chuan hoa ngay sinh
        a.setDayOfBirth(a.chNs(a.getDayOfBirth()));

        System.out.println("Da chuan hoa: " + a);

        // Gui doi tuong da sua len server
        out.writeObject(a);
        out.flush();

    }
}

class Customer implements Serializable{
    private static final long serialVersionUID = 20170711L;

    private int id;
    private String code;
    private String name;
    private String dayOfBirth;
    private String userName;

    public Customer(int id, String code, String name, String dayOfBirth, String userName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String chTen(String name) {
        String[] words = name.trim().toLowerCase().split("\\s+");
        String lastword = words[words.length - 1].toUpperCase();

        StringBuilder rest = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            String x = words[i];
            String word = x.substring(0, 1).toUpperCase() + x.substring(1);
            rest.append(word);
            if (i != words.length - 2) rest.append(" ");
        }
        return lastword + ", " + rest.toString();
    }

    // Chuan hoa ngay sinh: mm-dd-yyyy -> dd/mm/yyyy
    public String chNs(String date) {
        String[] parts = date.trim().split("-");
        String mm = parts[0];
        String dd = parts[1];
        String yyyy = parts[2];
        return dd + "/" + mm + "/" + yyyy;
    }

    // Sinh username: "nguyen van hai duong" -> "nvhduong"
    public String chUser(String name) {
        String[] words = name.trim().toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            sb.append(words[i].charAt(0));
        }
        sb.append(words[words.length - 1]);
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}