package com.lsp.web.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.lsp.web.genericentity.BaseEntity;

@Entity
@Table(name = "apply")
public class Apply extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserInfo user;

    public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}


	private String productName;

    private String mobileNumber;

    private Integer stage;
    
//    private String status;

    // Getters and setters
}
