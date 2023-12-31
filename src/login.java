import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class login {
    static String filepath = "D:\\employee.csv";

    public static boolean check(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] check = line.split(",");
                if (check[0].equals(username) && check[3].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}



