package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：平台累计金额(按商品类型分配）对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 00:46:54
 */
public class AccumulativeAmount implements java.io.Serializable {

	/**aaId*/
	@NotEmpty
	private Long aaId;
	/**统计类型（0 累加，1 累减） */
	@NotEmpty
	private Integer aaType;
	/**商品类型顶层*/
	@NotEmpty
	private Long aaGoodsParentType;
	/**商品类型（当前商品类型）*/
	@NotEmpty
	private Long aaGoodsType;
	/**订单id*/
	@NotEmpty
	private Long aaOrderId;
	/**商品id*/
	@NotEmpty
	private Long aaGoodsId;
	/**消费金额*/
	@NotEmpty
	private Long aaConsumePrice;
	/**当前累计金额*/
	@NotEmpty
	private Long aaTotalConsumePrice;
	/**创建时间*/
	@NotEmpty
	private Date aaCreateTime;
	/**累计备注*/
	@NotEmpty
	@Length(min = 1, max = 500)
	private String aaRemark;
	/**version*/
	@NotEmpty
	private Integer version;
	//columns END

	public AccumulativeAmount(){
	}

	public AccumulativeAmount( Long aaId){
		this.aaId = aaId;
	}

	public void setAaId(Long value) {
		this.aaId = value;
	}

	public Long getAaId() {
		return this.aaId;
	}
	public void setAaType(Integer value) {
		this.aaType = value;
	}

	public Integer getAaType() {
		return this.aaType;
	}
	public void setAaGoodsParentType(Long value) {
		this.aaGoodsParentType = value;
	}

	public Long getAaGoodsParentType() {
		return this.aaGoodsParentType;
	}
	public void setAaGoodsType(Long value) {
		this.aaGoodsType = value;
	}

	public Long getAaGoodsType() {
		return this.aaGoodsType;
	}
	public void setAaOrderId(Long value) {
		this.aaOrderId = value;
	}

	public Long getAaOrderId() {
		return this.aaOrderId;
	}
	public void setAaGoodsId(Long value) {
		this.aaGoodsId = value;
	}

	public Long getAaGoodsId() {
		return this.aaGoodsId;
	}
	public void setAaConsumePrice(Long value) {
		this.aaConsumePrice = value;
	}

	public Long getAaConsumePrice() {
		return this.aaConsumePrice;
	}
	public void setAaTotalConsumePrice(Long value) {
		this.aaTotalConsumePrice = value;
	}

	public Long getAaTotalConsumePrice() {
		return this.aaTotalConsumePrice;
	}
	public void setAaCreateTime(Date value) {
		this.aaCreateTime = value;
	}

	public Date getAaCreateTime() {
		return this.aaCreateTime;
	}
	public void setAaRemark(String value) {
		this.aaRemark = value;
	}

	public String getAaRemark() {
		return this.aaRemark;
	}
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getVersion() {
		return this.version;
	}


}
