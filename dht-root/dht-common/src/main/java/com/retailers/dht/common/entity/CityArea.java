package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：城市地区表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-20 15:47:24
 */
public class CityArea implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Long id;
	/**地域代码*/
	@NotEmpty
	@Length(min = 1, max = 100)
	private String code;
	/**城市名称*/
	@NotEmpty
	@Length(min = 1, max = 100)
	private String name;
	/**上级地区代码*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String parentCode;
	/**上级地域*/
	@NotEmpty
	private Long parentId;
	//columns END

	public CityArea(){
	}

	public CityArea( Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setCode(String value) {
		this.code = value;
	}

	public String getCode() {
		return this.code;
	}
	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}
	public void setParentCode(String value) {
		this.parentCode = value;
	}

	public String getParentCode() {
		return this.parentCode;
	}
	public void setParentId(Long value) {
		this.parentId = value;
	}

	public Long getParentId() {
		return this.parentId;
	}


}
