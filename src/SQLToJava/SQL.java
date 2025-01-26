package SQLToJava;



import java.sql.*;


public class SQL {

    private final String host = "localhost";
    private final String port = "5432";
    private final String dbName = "hihihaha";
    private final String login = "postgres";
    private final String password = "12345";

    private Connection dbCon; //подкл к бд

    private Connection getDbConnection() {

        //адрес нашей бд
        String str = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Соединение установлено");
        } catch (ClassNotFoundException e) {
            System.out.println("Соединение не установлено");
        }
        try {
            dbCon = DriverManager.getConnection(str, login, password);
        } catch (SQLException e) {
            System.out.println("Неверный логин и пароль");
        }
        return dbCon;
    }

    public void isConnection() throws SQLException, ClassNotFoundException {
        dbCon = getDbConnection();
        System.out.println(dbCon.isValid(1000));
    }

    public void createTable(String tableName) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName +
                " (id INT PRIMARY KEY, name VARCHAR(50))";
        try {
            Statement statement = getDbConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица создалась");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTable(String table) {
        try {
            Statement statement = getDbConnection().createStatement();
            int rows = statement.executeUpdate("INSERT INTO " + table + "(id, name) "
                    + "VALUES (1, 'Рок');");
            System.out.printf("Добавлено %d строк ", rows);
        } catch (SQLException e) {
            System.out.println("Не удалось добавить");
        }

    }

    public void updateTable(String table) {
        try {
            Statement statement = getDbConnection().createStatement();
            int update = statement.executeUpdate("UPDATE " + table + " SET name = 'Рокешник' WHERE name = 'Рок' ");
            System.out.printf("Добавлено %d строк ", update);
        } catch (SQLException e) {
            System.out.println("Не удалось обновить");
        }
    }

    public void deleteTable(String table) {
        try {
            Statement statement = getDbConnection().createStatement();
            int delete = statement.executeUpdate("DELETE FROM " + table + " WHERE id = 1");
            System.out.printf("Добавлено %d строк ", delete);
        } catch (SQLException e) {
            System.out.println("Не удалось удалить");
        }
    }
}