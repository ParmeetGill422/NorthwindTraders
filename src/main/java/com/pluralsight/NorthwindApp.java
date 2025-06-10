package com.pluralsight;

import java.sql.*;

public class NorthwindApp {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        if (args.length != 2) {
            System.out.println("Usage: java com.pluralsight.NorthwindApp <username> <password>");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];


        Class.forName("com.mysql.cj.jdbc.Driver");


        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:5577/northwind",
                username,
                password
        );

        String sql = "SELECT id, product_name, list_price, target_level FROM products";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet results = statement.executeQuery();

        System.out.printf("%-5s %-40s %-12s %-10s\n", "ID", "Product Name", "List Price", "In Stock");
        System.out.println("----------------------------------------------------------------------------");

        while (results.next()) {
            int id = results.getInt("id");
            String name = results.getString("product_name");
            double price = results.getDouble("list_price");
            int stock = results.getInt("target_level");

            System.out.printf("%-5d %-40s $%-11.2f %-10d\n", id, name, price, stock);
        }


        results.close();
        statement.close();
        connection.close();
    }
}
