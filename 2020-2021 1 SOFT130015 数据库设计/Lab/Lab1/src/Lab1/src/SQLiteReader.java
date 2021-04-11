import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class SQLiteReader {
    private static final String Class_Name = "org.sqlite.JDBC";
    private String dbAddress;
    private String tableName;
    private Connection connection;

    SQLiteReader(String dbAddress, String tableName) {
        this.dbAddress = dbAddress;
        this.tableName = tableName;
        this.connection = null;
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //获取SQLite表的字段名称和数据类型
    private List<Column> getLabels() throws SQLException {
        ArrayList<Column> labels = new ArrayList<>();
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from " + this.tableName);
        ResultSetMetaData metaData = rs.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            labels.add(new Column(metaData.getColumnName(i), metaData.getColumnTypeName(i)));
        }
        return labels;
    }

    //db读取
    void SQLite2MySQL(Statement statementTo, String tableNameTo) {
        try {
            getDataAndWrite(statementTo, tableNameTo);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //创建Sqlite数据库连接
    private Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName(Class_Name);
        return DriverManager.getConnection(dbAddress);
    }

    //连接SQLite数据库读取数据，然后写入mySql数据库
    private void getDataAndWrite(Statement statementTo, String tableNameTo) throws SQLException {
        String tempSQL;
        List<Column> labelList = getLabels();
        Statement statementFrom = this.connection.createStatement();
        ResultSet rs = statementFrom.executeQuery("select * from " + this.tableName);
        while (rs.next()) {
            tempSQL = "insert into " + tableNameTo + " values(";
            for (int i = 0; i < labelList.size(); i++) {
                tempSQL = tempSQL + "'" + rs.getString(labelList.get(i).getLabel()) + "'";
                if (i != labelList.size() - 1) {
                    tempSQL = tempSQL + ", ";
                }
            }
            tempSQL = tempSQL +  ")";
            statementTo.executeUpdate(tempSQL);
        }
        statementFrom.close();
    }

    //关闭连接
    void close() {
        try {
            if (this.connection != null)
                this.connection.close();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode());
        }
    }
}
