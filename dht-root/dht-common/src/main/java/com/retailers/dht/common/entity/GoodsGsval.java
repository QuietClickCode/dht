package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品规格值表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-18 09:46:10
 */
public class GoodsGsval implements java.io.Serializable {

	/**商品类型值ID*/
	@NotEmpty
	private Long gsvId;
	/**商品规格ID*/
	@NotEmpty
	private Long gsId;
	/**商品规格值*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String gsvVal;
	/**isDelete*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGsval(){
	}

	public GoodsGsval( Long gsvId){
		this.gsvId = gsvId;
	}

	public void setGsvId(Long value) {
		this.gsvId = value;
	}

	public Long getGsvId() {
		return this.gsvId;
	}
	public void setGsId(Long value) {
		this.gsId = value;
	}

	public Long getGsId() {
		return this.gsId;
	}
	public void setGsvVal(String value) {
		this.gsvVal = value;
	}

	public String getGsvVal() {
		return this.gsvVal;
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
