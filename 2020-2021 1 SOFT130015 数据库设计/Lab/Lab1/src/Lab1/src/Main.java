import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Scanner;
import com.csvreader.CsvReader;

public class Main {
    public static void main(String[] args) throws Exception {
        String URL = "jdbc:mysql://localhost:3306/";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please input your MySql username： \n");
        String USER = scanner.nextLine();
        System.out.print("Please input your MySql password： \n");
        String PASSWORD = scanner.nextLine();
        System.out.print("Please input the database name to be created (not an existed one)： \n");
        String DB_NAME = scanner.nextLine();

        //创建数据库
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn1 = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt1 = conn1.createStatement();
            stmt1.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt1.close();
            conn1.close();
        }

        //连接到MySql数据库
        Class.forName("com.mysql.jdbc.Driver");
        String URL2 = URL + DB_NAME;
        Connection conn = DriverManager.getConnection(URL2, USER, PASSWORD);
        Statement stmt = conn.createStatement();

        //创建表db_room，从xxxdatabase.db中读取并写入
        {
            String sql_db1 = "CREATE TABLE db_room " +
                    "(kdno int(4), " +
                    " kcno int(1), " +
                    " ccno int(2), " +
                    " kdname varchar(4), " +
                    " time varchar(20), " +
                    " papername varchar(10))";
            stmt.executeUpdate(sql_db1);
            SQLiteReader sqLiteReader1 = new SQLiteReader("jdbc:sqlite:src\\xxxdatabase.db", "room");
            sqLiteReader1.SQLite2MySQL(stmt, "db_room");
            sqLiteReader1.close();
            System.out.println("Table db_room has been created successfully!");
        }

        //创建表db_student，从xxxdatabase.db中读取并写入
        {
            String sql_db2 = "CREATE TABLE db_student " +
                    "(registno varchar(7), " +
                    " name varchar(10), " +
                    " kdno int(4), " +
                    " kcno int(1), " +
                    " ccno int(2), " +
                    " seat int(1) )";
            stmt.executeUpdate(sql_db2);
            SQLiteReader sqLiteReader2 = new SQLiteReader("jdbc:sqlite:src\\xxxdatabase.db", "student");
            sqLiteReader2.SQLite2MySQL(stmt, "db_student");
            sqLiteReader2.close();
            System.out.println("Table db_student has been created successfully!");
        }

        //创建表csv_room，从room.csv中读取并写入
        {
            String path1 = "src/room.csv";
            String sql1 = "CREATE TABLE csv_room " +
                    "(kdno int(4), " +
                    " kcno int(1), " +
                    " ccno int(2), " +
                    " kdname varchar(4), " +
                    " exptime varchar(20), " +
                    " papername varchar(10))";
            stmt.executeUpdate(sql1);

            CsvReader reader1 = new CsvReader(path1, ',', Charset.forName("GBK"));
            String name1 = new BufferedReader(new FileReader(new File(path1))).readLine();
            reader1.readHeaders();

            int len1 = reader1.getHeaders().length;
            while (reader1.readRecord()) {
                String tmp = "insert into csv_room(" + name1 + ")values(" + reader1.get(0);
                for (int i = 1; i < len1 - 1; i++) {
                    tmp += "," + "'" + reader1.get(i).replaceAll("'", "\\\\'") + "'";
                }
                tmp += "," + "'" + reader1.get(len1 - 1).replaceAll("'", "\\\\'") + "');";
                stmt.execute(tmp);
            }
            System.out.println("Table csv_room has been created successfully!");
        }

        //创建表csv_student，从student.csv中读取并写入
        {
            String path2 = "src/student.csv";
            String sql2 = "CREATE TABLE csv_student " +
                    "(registno varchar(7), " +
                    " name varchar(10), " +
                    " kdno int(4), " +
                    " kcno int(1), " +
                    " ccno int(2), " +
                    " seat int(1) )";
            stmt.executeUpdate(sql2);

            CsvReader reader2 = new CsvReader(path2, ',', Charset.forName("GBK"));
            String name2 = new BufferedReader(new FileReader(new File(path2))).readLine();
            reader2.readHeaders();

            int len2 = reader2.getHeaders().length;
            while (reader2.readRecord()) {
                String tmp2 = "insert into csv_student(" + name2 + ")values(" + reader2.get(0);
                for (int i = 1; i < len2 - 1; i++) {
                    tmp2 += "," + "'" + reader2.get(i).replaceAll("'", "\\\\'") + "'";
                }
                tmp2 += "," + "'" + reader2.get(len2 - 1).replaceAll("'", "\\\\'") + "');";
                stmt.execute(tmp2);
            }
            System.out.println("Table csv_student has been created successfully!");
        }

        //关闭连接
        stmt.close();
        conn.close();
    }
}