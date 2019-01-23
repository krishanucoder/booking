/**
 * @author Krishanu
*/
package com.stays.bookingserver.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class CommonModel {

	private Integer status;
	private String createdDate;
	private Long createdBy;
	private String modifiedDate;
	private Long modifiedBy;
	private String userToken;
	private String languageId;

}