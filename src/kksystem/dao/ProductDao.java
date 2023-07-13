/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kksystem.dao;

import kksystem.service.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import kksystem.controller.ProductJFrame;

/**
 *
 * @author shini
 */
public class ProductDao {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;

    final String url = "jdbc:postgresql://localhost:5432/kaikei";
    final String user = "postgres";
    final String password = "postgres";

    public void insertProductInfo(Product p) {

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String getSql = "SELECT NEXTVAL('seq_product_id')";
            rset = stmt.executeQuery(getSql);

            int seq = 0;
            while (rset.next()) {
                System.out.println(rset.getString(1));
                seq = Integer.valueOf(rset.getString(1));

            }

            LocalDateTime systemDate = LocalDateTime.now();

            String sql = "INSERT INTO product(product_id, product_name, price, quantity,purchases,delete_flag,create_date,update_date) \n"
                    + "VALUES (" + seq + ", '" + p.getProductname() + "', " + p.getPrice() + ", " + p.getQuantity() + "," + p.getQuantity() + " , '" + 0 + "','" + systemDate + "' ,'" + systemDate + "')";

            System.out.println(sql);
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProductJFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public List<Product> getProductInfoList() {

        List<Product> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();

            String sql = null;
            sql = "select * from product order by product_id asc;";

            rset = stmt.executeQuery(sql);

            while (rset.next()) {
                Product p = new Product();

                p.setProductid(rset.getInt(1));
                p.setProductname(rset.getString(2));
                p.setPrice(rset.getInt(3));
                p.setQuantity(rset.getInt(4));

                list.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    public void updateProductInfo(Product p) {
        try {
            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();
            LocalDateTime systemDate = LocalDateTime.now();

            String sql = "UPDATE product SET product_name  = '" + p.getProductname() + "', price = " + p.getPrice()
                    + ", quantity = " + p.getQuantity() + ",purchases = " + p.getQuantity()
                    + ", delete_flag = '" + 0 + "' ,create_date = '" + systemDate + "' ,update_date = '" + systemDate + "'WHERE product_id = " + p.getProductId();

            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(ProductJFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void deleteProductInfo(Product p) {
        try {
            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();

            String sql = "DELETE FROM product WHERE product_id = " + p.getProductId();
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProductJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSeisekiData(JTextField jTextField2, JTextField jTextField3, JTextField jTextField4, JTextField jTextField5, JTextField jTextField6) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
