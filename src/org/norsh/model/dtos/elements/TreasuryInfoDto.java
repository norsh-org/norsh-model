package org.norsh.model.dtos.elements;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreasuryInfoDto {
	private String id;
	private String udbn;
	private BigDecimal supply;
	private BigDecimal circulating;
	private BigDecimal nsh;
	private BigDecimal value;
	private Long timestamp;
}
