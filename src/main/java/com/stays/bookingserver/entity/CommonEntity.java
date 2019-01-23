package com.stays.bookingserver.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class CommonEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = -5367350189003102000L;

	@Column(name = "status")
	@JsonProperty("status")
	private Integer status;

	@Column(name = "created_date")
	@JsonProperty("createdDate")
	private String createdDate;

	@Column(name = "created_by")
	@JsonProperty("createdBy")
	private Long createdBy;

	@Column(name = "modified_date")
	@JsonProperty("modifiedDate")
	private String modifiedDate;

	@Column(name = "modified_by")
	@JsonProperty("modifiedBy")
	private Long modifiedBy;

}