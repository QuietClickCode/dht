package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：地区与运费关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-31 10:49:29
 */
public class GoodsCgfrel implements java.io.Serializable {

	/**cgfId*/
	@NotEmpty
	private Long cgfId;
	/**运费ID*/
	@NotEmpty
	private Long gfId;
	/**城市ID*/
	@NotEmpty
	private Long cid;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsCgfrel(){
	}

	public GoodsCgfrel( Long cgfId){
		this.cgfId = cgfId;
	}

	public void setCgfId(Long value) {
		this.cgfId = value;
	}

	public Long getCgfId() {
		return this.cgfId;
	}
	public void setGfId(Long value) {
		this.gfId = value;
	}

	public Long getGfId() {
		return this.gfId;
	}
	public void setCid(Long value) {
		this.cid = value;
	}

	public Long getCid() {
		return this.cid;
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
