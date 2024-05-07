import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class viewinfo {
    private static String vehiclepath = "D:\\vehicle.csv";
    private static String salespath = "D:\\sales.csv";
    private static String employeepath = "D:\\employee.csv";
    private static String custpath = "D:\\cust.csv";
    //status是用户等级，1是管理员 2是普通员工
    private String status;
    private String employeeId;

    // 设定员工id和status的构造器
    public viewinfo(String employeeId) throws IOException {
        this.employeeId = employeeId;
        this.status = getStatus(employeeId);
    }

    public static void viewvehicle() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(vehiclepath));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    // 判断员工等级的函数
    public static String getStatus(String employeeId) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(employeepath));
        String line;
        while ((line = br.readLine()) != null) {
            String info[] = line.split(",");
            if (info[0].equals(employeeId)) {
                return info[2];
            }
        }
        return null;
    }

    public void viewsales() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(salespath));
        String line;
        if (this.status != null && this.status.equals("0")) {
            System.out.println("salesId,dateTime,carPlate,custId,employeeId");
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                if (info[4].equals(employeeId)) {
                    System.out.println(line);
                }
            }
            br.close();
        } else if (this.status != null && this.status.equals("1")) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } else if (this.status != null) {
            System.out.println("无权访问");
        }
    }

    public void viewcust() throws IOException {
        if (this.status != null && this.status.equals("0")) {
            ArrayList<String> custid = new ArrayList<String>();

            try (BufferedReader br = new BufferedReader(new FileReader(salespath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] info = line.split(",");
                    if (info[4].equals(employeeId)) {
                        custid.add(info[3]);
                    }
                }
                br.close();
            }

            try (BufferedReader br = new BufferedReader(new FileReader(custpath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] info = line.split(",");
                    if (custid.contains(info[0])) {
                        System.out.println(line);
                    }
                }
                br.close();
            }
        } else if (this.status != null && this.status.equals("1")) {
            BufferedReader br = new BufferedReader(new FileReader(custpath));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

        } else if (this.status != null) {
            System.out.println("无权访问");
        }

    }

    public void viewemployee() throws IOException {
        if (this.status != null && this.status.equals("1")) {
            BufferedReader br = new BufferedReader(new FileReader(employeepath));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } else
            System.out.println("无权访问");
    }

    public static void main(String[] args) {
        System.out.println("helloworld");
        System.out.println("pull test");
    
    }
}


