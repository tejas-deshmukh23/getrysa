package com.lsp.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lsp.web.genericentity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;


@Entity
//@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Table(name = "t_callback")
public class Callback extends BaseEntity {
	
    @ManyToOne(targetEntity = Product.class,cascade={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name="product_id")
    @JsonIgnore
    private Product product;

    @Column(name = "clickid", length = 100)
    private String clickid;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "click_time", columnDefinition="datetime NULL")
    private Date clickTime;

    @Column(name = "callback", columnDefinition="INT NOT NULL default 0")
    private Integer callback;

    @Column(name = "callback_content", length=1000)
    private String callbackContent;
    
    @Column(name = "callback_location", length=500)
    private String callbackLocation;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getClickid() {
        return clickid;
    }

    public void setClickid(String clickid) {
        this.clickid = clickid;
    }

    public Date getClickTime() {
        return clickTime;
    }

    public void setClickTime(Date clickTime) {
        this.clickTime = clickTime;
    }

    public Integer getCallback() {
        return callback;
    }

    public void setCallback(Integer callback) {
        this.callback = callback;
    }


    public String getCallbackContent() {
        return callbackContent;
    }

    public void setCallbackContent(String callbackContent) {
        this.callbackContent = callbackContent;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getCallbackLocation() {
		return callbackLocation;
	}

	public void setCallbackLocation(String callbackLocation) {
		this.callbackLocation = callbackLocation;
	}

}

