package com.penalty.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Penalty {

	@Id
	@GeneratedValue
	private Long id;
	
	private String vin;
	private String owner;
	private Double penaltyAmount;
	private boolean penaltyActive;
	private Date violationDate;
	private String violationType;


	public Penalty() {
		super();
	}


	public Penalty(String vin, String owner, Double penaltyAmount,Date violationDate,
			String violationType) {
		super();
		this.vin = vin;
		this.owner = owner;
		this.penaltyAmount = penaltyAmount;		
		this.violationDate = violationDate;
		this.violationType = violationType;
		this.penaltyActive = true;
	}


	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Double getPenaltyAmount() {
		return penaltyAmount;
	}
	public void setPenaltyAmount(Double penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}
	public boolean getPenaltyActive() {
		return penaltyActive;
	}
	public void setPenaltyActive(boolean penaltyActive) {
		this.penaltyActive = penaltyActive;
	}
	public Date getViolationDate() {
		return violationDate;
	}
	public void setViolationDate(Date violationDate) {
		this.violationDate = violationDate;
	}
	public String getViolationType() {
		return violationType;
	}
	public void setViolationType(String violationType) {
		this.violationType = violationType;
	}


	@Override
	public String toString() {
		return "Penalty [id=" + id + ", vin=" + vin + ", owner=" + owner + ", penaltyAmount=" + penaltyAmount
				+ ", penaltyActive=" + penaltyActive + ", violationDate=" + violationDate + ", violationType="
				+ violationType + "]";
	}



}
