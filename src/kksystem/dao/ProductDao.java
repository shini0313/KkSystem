/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kksystem.dao;

import kksystem.obj.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import kksystem.controller.ProductJFrame;
import kksystem.obj.Order;
import kksystem.obj.Revenue;

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
                    + "VALUES (" + seq + ", '" + p.getProductName() + "', " + p.getPrice() + ", " + p.getQuantity() + "," + p.getQuantity() + " , '" + 0 + "','" + systemDate + "' ,'" + systemDate + "')";

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

                p.setProductId(rset.getInt(1));
                p.setProductName(rset.getString(2));
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

            String sql = "UPDATE product SET product_name  = '" + p.getProductName() + "', price = " + p.getPrice()
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

    public Product getProductInfo(long productId) {

        Product p = new Product();

        try {
            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();

            String sql = "SELECT * FROM product WHERE product_id = " + productId;

            rset = stmt.executeQuery(sql);

            while (rset.next()) {

                p.setProductId(rset.getInt(1));
                p.setProductName(rset.getString(2));
                p.setPrice(rset.getInt(3));
                p.setQuantity(rset.getInt(4));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;

    }

    public void insertOrderInfo(List<Order> orderList) {

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String getSql = "SELECT NEXTVAL('seq_order_id')";
            rset = stmt.executeQuery(getSql);
            int seq = 0;
            if (rset.next()) {
                seq = rset.getInt(1);
            }

            String orderNo = "O" + String.valueOf(seq);

            LocalDateTime systemDate = LocalDateTime.now();

            for (Order o : orderList) {

                int total_amount = (int) (o.getQuantity() * o.getPrice());
                String sql = "INSERT INTO orders(order_id,product_id,product_name, price, quantity,total_amount,create_date,update_date) \n"
                        + "VALUES (" + seq + "," + o.getProductId() + ", '" + o.getProductName() + "', " + o.getPrice() + ", " + o.getQuantity() + "," + total_amount + ",'" + systemDate + "' ,'" + systemDate + "')";

                System.out.println(sql);
                stmt.executeUpdate(sql);
            }

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProductJFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public List<Order> getOrderInfoList() {

        List<Order> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();

            String sql = "select * from orders order by id asc";

            rset = stmt.executeQuery(sql);

            while (rset.next()) {
                Order o = new Order();
                o.setOrderId(rset.getInt(2));
                o.setProductId(rset.getInt(3));
                o.setProductName(rset.getString(4));
                o.setPrice(rset.getInt(5));
                o.setQuantity(rset.getInt(6));
                o.setTotalAmount(rset.getInt(7));
                o.setCreate_date(rset.getTimestamp(8));
                o.setUpdate_date(rset.getTimestamp(9));
                o.setStatus(rset.getString(10));
                int statusValue = rset.getInt(10);
                if (statusValue == 1) {
                    o.setStatus("購入");
                } else if (statusValue == 0) {
                    o.setStatus("キャンセル");
                }

                list.add(o);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    public List<Revenue> getOrderInfoListDay(String startTime, String endTime) {
        List<Revenue> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String sql = "SELECT DATE_TRUNC('day', create_date) AS order_date, SUM(total_amount) AS total_amount_sum " + "FROM orders "
                    + "WHERE create_date BETWEEN '" + startTime + "' AND '" + endTime + "' " + "AND status = '1' "
                    + "GROUP BY DATE_TRUNC('day', create_date)";

            System.out.println(sql);
            rset = stmt.executeQuery(sql);

            while (rset.next()) {
                Revenue r = new Revenue();
                r.setOrderDate(rset.getString(1));
                r.setTotalAmountSum(rset.getInt(2));

                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
   public List<Revenue> getOrderInfoListToday() {
    List<Revenue> list = new ArrayList<>();

    try {
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();

        String sql = "SELECT DATE_TRUNC('day', create_date) AS order_date, SUM(total_amount) AS total_amount_sum " +
                     "FROM orders " +
                     "WHERE create_date >= CURRENT_DATE " +
                     "AND create_date < CURRENT_DATE + INTERVAL '1 day' " +
                     "AND status = '1' " +
                     "GROUP BY DATE_TRUNC('day', create_date)";

        System.out.println(sql);
        rset = stmt.executeQuery(sql);

        while (rset.next()) {
            Revenue r = new Revenue();
            r.setOrderDate(rset.getString(1));
            r.setTotalAmountSum(rset.getInt(2));

            list.add(r);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProductJFrame.class.getName()).log(Level.SEVERE, null, ex);
    }

    return list;
}

    public List<Order> getRevenueInfoList(String startTime, String endTime) {
        List<Order> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM orders "
                    + "WHERE create_date BETWEEN '" + startTime + "' AND '" + endTime + "' "
                    + "ORDER BY id ASC";

            System.out.println(sql);
            rset = stmt.executeQuery(sql);

            while (rset.next()) {
                Order r = new Order();
                r.setOrderId(rset.getInt(2));
                r.setProductId(rset.getInt(3));
                r.setProductName(rset.getString(4));
                r.setPrice(rset.getInt(5));
                r.setQuantity(rset.getInt(6));
                r.setTotalAmount(rset.getInt(7));
                r.setCreate_date(rset.getTimestamp(8));
                r.setUpdate_date(rset.getTimestamp(9));
                r.setStatus(rset.getString(10));

                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
     public List<Order> getRevenueInfoListToday() {
        List<Order> list = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalDateTime startTime = today.atStartOfDay();
        LocalDateTime endTime = today.plusDays(1).atStartOfDay(); 
        
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM orders " +
                         "WHERE create_date >= '" + startTime + "' AND create_date < '" + endTime + "' " +
                         "ORDER BY id ASC";

            System.out.println(sql);
            rset = stmt.executeQuery(sql);

            while (rset.next()) {
                Order r = new Order();
                r.setOrderId(rset.getInt(2));
                r.setProductId(rset.getInt(3));
                r.setProductName(rset.getString(4));
                r.setPrice(rset.getInt(5));
                r.setQuantity(rset.getInt(6));
                r.setTotalAmount(rset.getInt(7));
                r.setCreate_date(rset.getTimestamp(8));
                r.setUpdate_date(rset.getTimestamp(9));
                r.setStatus(rset.getString(10));

                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }


    public void updateOrderInfo(Order o) {
        try {
            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();

            String sql = "UPDATE orders SET status = '0' WHERE order_id = '" + o.getOrderId() + "'";

            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(ProductJFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
