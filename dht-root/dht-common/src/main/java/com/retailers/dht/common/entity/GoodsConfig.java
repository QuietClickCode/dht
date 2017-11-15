package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品配置表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 10:37:31
 */
public class GoodsConfig implements java.io.Serializable {

	/**商品设置ID*/
	@NotEmpty
	private Long gcId;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**商品销量*/
	@NotEmpty
	private Long gsalesvolume;
	/**运费*/
	@NotEmpty
	private Float gfreight;
	/**定金*/
	@NotEmpty
	private Float gdeposit;
	/**预计发货时间*/
	@NotEmpty
	private Date gedt;
	/**利润率*/
	@NotEmpty
	private Float gprofitability;
	/**起售数量*/
	@NotEmpty
	private Long gstartbuy;
	/**gendbuy*/
	@NotEmpty
	private Long gendbuy;
	/**商品配置副本id*/
	@NotEmpty
	private Long gcCopyid;
	/**是否生效 0生效 1未生效*/
	@NotEmpty
	private Long isPutway;
	/**是否允许用户设置发货时间 0不允许 1允许*/
	@NotEmpty
	private Long isAllowsetdeliverytime;
	/**是否显示销量 0不显示 1显示*/
	@NotEmpty
	private Long isShowsalesvolume;
	/**会员是否打折 0不打折 1打折*/
	@NotEmpty
	private Long isMenberdiscount;
	/**能否货到付款 0不能 1能*/
	@NotEmpty
	private Long isCod;
	/**是否是服务类商品 0不是 1是*/
	@NotEmpty
	private Long isServicegoods;
	/**是否倍数购买 0不是 1是*/
	@NotEmpty
	private Long isMultiplebuy;
	/**是否预售 0不是 1是*/
	@NotEmpty
	private Long isAdvancesale;
	/**是否只允许会员购买 0不是 1是*/
	@NotEmpty
	private Long isOnlymenberbuy;
	/**是否删除 0不是 1是*/
	@NotEmpty
	private Long isDelete;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsConfig(){
	}

	public GoodsConfig( Long gcId){
		this.gcId = gcId;
	}

	public void setGcId(Long value) {
		this.gcId = value;
	}

	public Long getGcId() {
		return this.gcId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGsalesvolume(Long value) {
		this.gsalesvolume = value;
	}

	public Long getGsalesvolume() {
		return this.gsalesvolume;
	}
	public void setGfreight(Float value) {
		this.gfreight = value;
	}

	public Float getGfreight() {
		return this.gfreight;
	}
	public void setGdeposit(Float value) {
		this.gdeposit = value;
	}

	public Float getGdeposit() {
		return this.gdeposit;
	}
	public void setGedt(Date value) {
		this.gedt = value;
	}

	public Date getGedt() {
		return this.gedt;
	}
	public void setGprofitability(Float value) {
		this.gprofitability = value;
	}

	public Float getGprofitability() {
		return this.gprofitability;
	}
	public void setGstartbuy(Long value) {
		this.gstartbuy = value;
	}

	public Long getGstartbuy() {
		return this.gstartbuy;
	}
	public void setGendbuy(Long value) {
		this.gendbuy = value;
	}

	public Long getGendbuy() {
		return this.gendbuy;
	}
	public void setGcCopyid(Long value) {
		this.gcCopyid = value;
	}

	public Long getGcCopyid() {
		return this.gcCopyid;
	}
	public void setIsPutway(Long value) {
		this.isPutway = value;
	}

	public Long getIsPutway() {
		return this.isPutway;
	}
	public void setIsAllowsetdeliverytime(Long value) {
		this.isAllowsetdeliverytime = value;
	}

	public Long getIsAllowsetdeliverytime() {
		return this.isAllowsetdeliverytime;
	}
	public void setIsShowsalesvolume(Long value) {
		this.isShowsalesvolume = value;
	}

	public Long getIsShowsalesvolume() {
		return this.isShowsalesvolume;
	}
	public void setIsMenberdiscount(Long value) {
		this.isMenberdiscount = value;
	}

	public Long getIsMenberdiscount() {
		return this.isMenberdiscount;
	}
	public void setIsCod(Long value) {
		this.isCod = value;
	}

	public Long getIsCod() {
		return this.isCod;
	}
	public void setIsServicegoods(Long value) {
		this.isServicegoods = value;
	}

	public Long getIsServicegoods() {
		return this.isServicegoods;
	}
	public void setIsMultiplebuy(Long value) {
		this.isMultiplebuy = value;
	}

	public Long getIsMultiplebuy() {
		return this.isMultiplebuy;
	}
	public void setIsAdvancesale(Long value) {
		this.isAdvancesale = value;
	}

	public Long getIsAdvancesale() {
		return this.isAdvancesale;
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

	public Long getIsOnlymenberbuy() {
		return isOnlymenberbuy;
	}

	public void setIsOnlymenberbuy(Long isOnlymenberbuy) {
		this.isOnlymenberbuy = isOnlymenberbuy;
	}
}
