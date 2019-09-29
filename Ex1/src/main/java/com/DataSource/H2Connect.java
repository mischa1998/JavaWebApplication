package com.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Cat on 28.09.2019.
 */
public class H2Connect {
    private String dataBaseurl = "jdbc:h2:~/test1";
    private String userName = "sa";
    private String password = "sa";

    public Connection getH2Connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection (dataBaseurl, userName, password);
        return conn;
    }
}
