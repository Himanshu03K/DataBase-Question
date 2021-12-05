import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MyException extends RuntimeException
{
    public MyException(String message)
    {
        super(message);
    }
}
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/E2data", "root", "");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Pattern p = Pattern.compile("[a-zA-Z0-9._]+@(gmail|yahoo|rediffmail)+.com");
        Pattern p1 = Pattern.compile("(0|91)?[96][0-9]{9}");
        Matcher m = p.matcher(str);
        Matcher m1 = p1.matcher(str);
        int c = 0, q = 0;

        PreparedStatement S = con.prepareStatement("INSERT INTO info VALUES (?,?)");

        while (m.find() && m1.find()) {
            c++;

            S.setString(1, m1.group());
            S.setString(2, m.group());
            S.executeUpdate();
        }
            try {
                if (c == 0 ) {
                    throw new MyException("no data found ");
                }
            } catch (MyException obj) {
                System.out.println(obj);
            }

    }
}

