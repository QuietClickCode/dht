package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品规格表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-11 22:19:21
 */
public class GoodsSpecification implements java.io.Serializable {

	/**商品规格ID*/
	@NotEmpty
	private Long gsId;
	/**规格名称*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String gsName;
	//columns END

	public GoodsSpecification(){
	}

	public GoodsSpecification( Long gsId){
		this.gsId = gsId;
	}

	public void setGsId(Long value) {
		this.gsId = value;
	}

	public Long getGsId() {
		return this.gsId;
	}
	public void setGsName(String value) {
		this.gsName = value;
	}

	public String getGsName() {
		return this.gsName;
	}


}
