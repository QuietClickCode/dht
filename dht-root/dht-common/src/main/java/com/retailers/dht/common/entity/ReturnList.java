package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：返现类型表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-13 10:29:40
 */
public class ReturnList implements java.io.Serializable {

	/**返现id*/
	@NotEmpty
	private Long rtId;
	/**返现名称*/
	@NotEmpty
	@Length(min = 1, max = 100)
	private String rtName;
	/**返现类型 0立即返现 1不立即返现*/
	@NotEmpty
	private Long rtType;
	/**是否删除 0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public ReturnList(){
	}

	public ReturnList( Long rtId){
		this.rtId = rtId;
	}

	public void setRtId(Long value) {
		this.rtId = value;
	}

	public Long getRtId() {
		return this.rtId;
	}
	public void setRtName(String value) {
		this.rtName = value;
	}

	public String getRtName() {
		return this.rtName;
	}
	public void setRtType(Long value) {
		this.rtType = value;
	}

	public Long getRtType() {
		return this.rtType;
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
