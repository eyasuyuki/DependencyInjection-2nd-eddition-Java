package org.javaopen.di.chap3.data;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommerceDao {
    private String getJdbcUrl() {
        try {
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            return (String) env.lookup("jdbc/sqlite");
        } catch (NamingException e) {
            throw new RuntimeException("JDBC URL lookup failed.", e);
        }
    }
    public List<Product> getProduct() {
        List<Product> products = new ArrayList<Product>();
        try (Connection con = DriverManager.getConnection(getJdbcUrl())) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Id, Name, Description, UnitPrice, IsFeatured FROM products");
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("Id"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getDouble("UnitPrice"),
                        rs.getInt("IsFeatured") == 1);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
