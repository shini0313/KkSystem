/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kksystem.service;

import java.util.List;
import kksystem.dao.ProductDao;

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

        ProductDao seisekiDao = new ProductDao();

        List<Product> list = seisekiDao.getProductInfoList();

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
    
     public void insertOrderInfo(Product o) {

        ProductDao productDao = new ProductDao();

        productDao.insertOrderInfo(o);

    }
}
