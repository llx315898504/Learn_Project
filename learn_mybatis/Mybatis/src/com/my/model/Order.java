package com.my.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order  implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private Integer orderId;

    private Integer id;

    private Double orderAmount;

    private Date createTime;

    private String receiveAddress;
    
    private List<OrderDetail> orderDetailList;
    
    private Person person;

    public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }
}