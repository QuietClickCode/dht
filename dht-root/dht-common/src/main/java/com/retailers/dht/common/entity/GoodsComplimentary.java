package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：赠品表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-27 14:58:13
 */
public class GoodsComplimentary implements java.io.Serializable {

	/**赠品Id*/
	@NotEmpty
	private Long gcId;
	/**赠品名称*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String gcName;
	/**赠送条件*/
	@NotEmpty
	private Long gcCondition;
	/**赠送类型 0满件 1满元*/
	@NotEmpty
	private Long gcType;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**能否叠加使用 0不能 1可以*/
	@NotEmpty
	private Long isMultiuse;
	/**是否有效 0无效 1有效*/
	@NotEmpty
	private Long isValid;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsComplimentary(){
	}

	public GoodsComplimentary( Long gcId){
		this.gcId = gcId;
	}

	public void setGcId(Long value) {
		this.gcId = value;
	}

	public Long getGcId() {
		return this.gcId;
	}
	public void setGcName(String value) {
		this.gcName = value;
	}

	public String getGcName() {
		return this.gcName;
	}
	public void setGcCondition(Long value) {
		this.gcCondition = value;
	}

	public Long getGcCondition() {
		return this.gcCondition;
	}
	public void setGcType(Long value) {
		this.gcType = value;
	}

	public Long getGcType() {
		return this.gcType;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setIsMultiuse(Long value) {
		this.isMultiuse = value;
	}

	public Long getIsMultiuse() {
		return this.isMultiuse;
	}
	public void setIsValid(Long value) {
		this.isValid = value;
	}

	public Long getIsValid() {
		return this.isValid;
	}
	public void setIsDelete(Long value) {
		this.isDelete = value;
	}

	public Long getIsDelete() {
		return this.isDelete;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
