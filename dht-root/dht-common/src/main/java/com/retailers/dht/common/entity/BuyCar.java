package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：购物车表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-20 16:03:17
 */
public class BuyCar implements java.io.Serializable {

	/**购物车Id*/
	@NotEmpty
	private Long bcId;
	/**用户ID*/
	@NotEmpty
	private Long uid;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**商品数量*/
	@NotEmpty
	private Long gcount;
	/**已选规格值id*/
	@NotEmpty
	@Length(min = 1, max = 225)
	private String bcGsvalids;
	/**已选规格值名称*/
	@NotEmpty
	@Length(min = 1, max = 225)
	private String bcGsval;
	/**创建时间*/
	@NotEmpty
	private Date bcTimmer;
	/**单价*/
	@NotEmpty
	private Float bcPrice;
	/**路径*/
	@NotEmpty
	@Length(min = 1, max = 225)
	private String bcUrl;
	/**购物车备注*/
	@NotEmpty
	@Length(min = 1, max = 1000)
	private String bcDescription;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public BuyCar(){
	}

	public BuyCar( Long bcId){
		this.bcId = bcId;
	}

	public void setBcId(Long value) {
		this.bcId = value;
	}

	public Long getBcId() {
		return this.bcId;
	}
	public void setUid(Long value) {
		this.uid = value;
	}

	public Long getUid() {
		return this.uid;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGcount(Long value) {
		this.gcount = value;
	}

	public Long getGcount() {
		return this.gcount;
	}
	public void setBcGsvalids(String value) {
		this.bcGsvalids = value;
	}

	public String getBcGsvalids() {
		return this.bcGsvalids;
	}
	public void setBcGsval(String value) {
		this.bcGsval = value;
	}

	public String getBcGsval() {
		return this.bcGsval;
	}
	public void setBcTimmer(Date value) {
		this.bcTimmer = value;
	}

	public Date getBcTimmer() {
		return this.bcTimmer;
	}
	public void setBcPrice(Float value) {
		this.bcPrice = value;
	}

	public Float getBcPrice() {
		return this.bcPrice;
	}
	public void setBcUrl(String value) {
		this.bcUrl = value;
	}

	public String getBcUrl() {
		return this.bcUrl;
	}
	public void setBcDescription(String value) {
		this.bcDescription = value;
	}

	public String getBcDescription() {
		return this.bcDescription;
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