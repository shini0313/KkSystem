/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kksystem.service;

import kksystem.obj.Order;
import kksystem.obj.Product;
import java.util.List;
import kksystem.dao.ProductDao;
import kksystem.obj.Revenue;

/**
 *
 * @author shini
 */
public class ProductService {

    public void insertProductInfo(Product kk) {

        ProductDao productDao = new ProductDao();

        productDao.insertProductInfo(kk);

    }

    public List<Product> getProductInfoList() {

        ProductDao productDao = new ProductDao();

        List<Product> list = productDao.getProductInfoList();

        return list;
    }

    public void updateProductInfo(Product kk) {

        ProductDao productDao = new ProductDao();

        productDao.updateProductInfo(kk);

    }

    public void deleteProductInfo(Product kk) {

        ProductDao productDao = new ProductDao();

        productDao.deleteProductInfo(kk);
    }

    public Product getProductInfo(long productId) {

        ProductDao productDao = new ProductDao();

        Product p = productDao.getProductInfo(productId);

        return p;

    }

    public void insertOrderInfo(List<Order> orderList) {

        ProductDao productDao = new ProductDao();

        productDao.insertOrderInfo(orderList);

    }

    public List<Order> getOrderInfoList() {

        ProductDao orderDao = new ProductDao();

        List<Order> list = orderDao.getOrderInfoList();

        return list;
    }

    public List<Revenue> getOrderInfoListDay(String startTime, String endTime) {

        ProductDao orderDao = new ProductDao();

        List<Revenue> list = orderDao.getOrderInfoListDay(startTime, endTime);

        return list;

    }
      public List<Revenue> getOrderInfoListToday() {

        ProductDao orderDao = new ProductDao();

        List<Revenue> list = orderDao.getOrderInfoListToday();

        return list;

    }

    public List<Order> getRevenueInfoList(String startTime, String endTime) {

        ProductDao orderDao = new ProductDao();

        List<Order> list = orderDao.getRevenueInfoList(startTime, endTime);

        return list;

    }
      public List<Order> getRevenueInfoListToday() {

        ProductDao orderDao = new ProductDao();

        List<Order> list = orderDao.getRevenueInfoListToday();

        return list;

    }
    

    public void updateOrderInfo(Order o) {

        ProductDao productDao = new ProductDao();

        productDao.updateOrderInfo(o);

    }

}
