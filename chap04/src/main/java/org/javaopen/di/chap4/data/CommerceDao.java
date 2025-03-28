package org.javaopen.di.chap4.data;

import org.javaopen.di.chap4.domain.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommerceDao {
    private final String connectionString;
    public CommerceDao(String connectionString) {
        this.connectionString = connectionString;
    }

    public List<Product> getProduct() {
        List<Product> products = new ArrayList<Product>();
        try (Connection con = DriverManager.getConnection(connectionString)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Name, UnitPrice, IsFeatured FROM products");
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("Name"),
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
