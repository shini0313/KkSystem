/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kksystem.service;

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

}
