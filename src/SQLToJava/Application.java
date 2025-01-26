package SQLToJava;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQL db = new SQL();
        db.isConnection();
        db.createTable("playlists");
        db.addTable("playlists");
        db.updateTable("playlists");
        db.deleteTable("playlists");
    }
}