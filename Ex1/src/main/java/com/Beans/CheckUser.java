package com.Beans;

import com.DataSource.H2Connect;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Cat on 28.09.2019.
 */
@Component
public class CheckUser {
    public CheckUser() {

    }
    public boolean isSuchUser(String name, String password) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = new H2Connect().getH2Connection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from users where name='" + name +"' and password='" + password +"'");
        if (rs.next()) {
            result = true;
        }
        try {
            if(rs != null) {
                rs.close();
            }
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } finally {}
            }
        }
        return result;
    }
}
