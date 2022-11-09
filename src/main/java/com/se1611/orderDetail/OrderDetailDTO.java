/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1611.orderDetail;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class OrderDetailDTO implements Serializable {
    private int order_Detail_Id;
    private int order_Id;
    private int book_Id;
    private int quantity_Order_Detail;
    private float total_Order_Detail;
    private boolean status;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int order_Detail_Id, int order_Id, int book_Id, int quantity_Order_Detail, float total_Order_Detail, boolean status) {
        this.order_Detail_Id = order_Detail_Id;
        this.order_Id = order_Id;
        this.book_Id = book_Id;
        this.quantity_Order_Detail = quantity_Order_Detail;
        this.total_Order_Detail = total_Order_Detail;
        this.status = status;
    }

    public OrderDetailDTO(int order_Id, int book_Id, int quantity_Order_Detail, float total_Order_Detail, boolean status) {
        this.order_Id = order_Id;
        this.book_Id = book_Id;
        this.quantity_Order_Detail = quantity_Order_Detail;
        this.total_Order_Detail = total_Order_Detail;
        this.status = status;
    }

    public int getOrder_Detail_Id() {
        return order_Detail_Id;
    }

    public void setOrder_Detail_Id(int order_Detail_Id) {
        this.order_Detail_Id = order_Detail_Id;
    }

    public int getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(int order_Id) {
        this.order_Id = order_Id;
    }

    public int getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(int book_Id) {
        this.book_Id = book_Id;
    }

    public int getQuantity_Order_Detail() {
        return quantity_Order_Detail;
    }

    public void setQuantity_Order_Detail(int quantity_Order_Detail) {
        this.quantity_Order_Detail = quantity_Order_Detail;
    }

    public float getTotal_Order_Detail() {
        return total_Order_Detail;
    }

    public void setTotal_Order_Detail(float total_Order_Detail) {
        this.total_Order_Detail = total_Order_Detail;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   

  
}