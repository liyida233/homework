import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registeration {
    private static String filepath = "D:\\employee.csv";
    private String password;
    private String username;

    final static String secretKey = "lecar";

    public registeration(String Username, String password) {
        this.password = password;
        this.username = Username;
    }

    //获得当前员工最大Id的函数
    public static String MaxId() {
        String maxId = "E0000";
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 4 && !values[0].equals("employeeId")) {
                    String id = values[0];
                    if (id.compareTo(maxId) > 0) {
                        maxId = id;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxId;
    }

    //获得将要添加员工的ID
    public static String NextId(String currentMaxId) {
        Pattern pattern = Pattern.compile("E(\\d+)");
        Matcher matcher = pattern.matcher(currentMaxId);
        if (matcher.find()) {
            int idNumber = Integer.parseInt(matcher.group(1));
            idNumber++;
            return String.format("E%04d", idNumber);
        }
        return null;
    }

    // 将传入的新员工姓名和密码加入员工csv并生成一个新的员工id
    public void addToCsv() {
        String currentMaxId = MaxId();
        String newId = NextId(currentMaxId);
        if (newId != null) {
            String newUserRecord = newId + "," + this.username + "," + 0 + "," + this.password;
            try (FileWriter fw = new FileWriter(filepath, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bw)) {
                pw.println(newUserRecord);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //判断用户输入秘钥是否正确
    public static void checkkey() {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入秘钥");
        String userInputKey = s.nextLine();
        if (!userInputKey.equals(secretKey)) {
            System.out.println("密钥错误，注册进程结束。");
            System.exit(0); // 密钥错误，结束程序
        }
        System.out.println("密钥正确，继续注册流程...");
    }
}



