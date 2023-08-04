/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kksystem.obj;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author shini
 */
public class Revenue {

    private String orderDate;

    private int totalAmountSum;

    public int getTotalAmountSum() {
        return totalAmountSum;
    }

    public void setTotalAmountSum(int totalAmountSum) {
        this.totalAmountSum = totalAmountSum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

}
