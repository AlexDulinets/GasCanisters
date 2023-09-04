import java.util.ArrayList;
import java.util.List;

public class Balon {
    @Override
    public String toString() {
        return "Balon{" +
                "value=" + value +
                ", status=" + status + " № балона= " + id +
                '}';
    }


    private static int Gid;
    public int id;
    public int value;
    public int countDoz;
    public List balonsDozs = new ArrayList();
    public List countDozs = new ArrayList();
    public boolean status;

    public Balon(int value, boolean status) {
        Gid++;
        id = Gid;
        this.value = value;
        this.status = status;
    }
}