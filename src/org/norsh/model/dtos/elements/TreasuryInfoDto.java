package org.norsh.model.dtos.elements;

import java.math.BigDecimal;

public class TreasuryInfoDto {
	private String id;
	private String udbn;
	private BigDecimal supply;
	private BigDecimal circulating;
	private BigDecimal nsh;
	private BigDecimal value;
	private Long timestamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUdbn() {
		return udbn;
	}
	public void setUdbn(String udbn) {
		this.udbn = udbn;
	}
	public BigDecimal getSupply() {
		return supply;
	}
	public void setSupply(BigDecimal supply) {
		this.supply = supply;
	}
	public BigDecimal getCirculating() {
		return circulating;
	}
	public void setCirculating(BigDecimal circulating) {
		this.circulating = circulating;
	}
	public BigDecimal getNsh() {
		return nsh;
	}
	public void setNsh(BigDecimal nsh) {
		this.nsh = nsh;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
