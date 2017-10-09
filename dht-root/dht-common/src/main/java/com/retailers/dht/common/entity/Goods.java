package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 17:29:36
 */
public class Goods implements java.io.Serializable {

	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**商品名称*/
	@NotEmpty
	@Length(min = 1, max = 64)
	private String gname;
	/**所属厂家*/
	@NotEmpty
	private Long gvender;
	/**所属店铺*/
	@NotEmpty
	private Long gshop;
	/**所属分类*/
	@NotEmpty
	private Long gclassification;
	/**所属品牌*/
	@NotEmpty
	private Long gbrand;
	/**上架时间*/
	@NotEmpty
	private Date gputawaytime;
	/**下架时间*/
	@NotEmpty
	private Date gunshelvetime;
	/**所属地区*/
	@NotEmpty
	private Long garea;
	/**可售范围 以km为单位*/
	@NotEmpty
	private Long gsalescope;
	/**利润率*/
	@NotEmpty
	private Long gprofitability;
	/**销量*/
	@NotEmpty
	private Long gsalesvolume;
	/**运费*/
	@NotEmpty
	private Long gfreight;
	/**主推方向 0乡村 1城镇 2乡村和城镇*/
	@NotEmpty
	private Long gmaindirection;
	/**计件单位*/
	@NotEmpty
	@Length(min = 1, max = 20)
	private String gunitname;
	/**起售数量*/
	@NotEmpty
	private Long gstartbuy;
	/**限购量*/
	@NotEmpty
	private Long gendbuy;
	/**商品描述*/
	@NotEmpty
	private byte[] gdescription;
	/**生产人姓名*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String gproductioninperson;
	/**产地信息*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String gproductioninaddress;
	/**采摘人姓名*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String gpickperson;
	/**采摘人地址*/
	@NotEmpty
	@Length(min = 1, max = 255)
	private String gpickaddress;
	/**定金*/
	@NotEmpty
	private Long gdeposit;
	/**copy的ID update前在copy中插入一条一样的数据 并且更新此字段*/
	@NotEmpty
	private Long gcopyid;
	/**是否上架 0未上架 1已上架 2已下架*/
	@NotEmpty
	private Long isPutway;
	/**是否通过审核 0未审核 1已审核 2未通过审核*/
	@NotEmpty
	private Long isChecked;
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
	/**是否倍数购买*/
	@NotEmpty
	private Long isMultiplebuy;
	/**是否是服务类商品 0不是 1是*/
	@NotEmpty
	private Long isServicegoods;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public Goods(){
	}

	public Goods( Long gid){
		this.gid = gid;
	}

	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGname(String value) {
		this.gname = value;
	}

	public String getGname() {
		return this.gname;
	}
	public void setGvender(Long value) {
		this.gvender = value;
	}

	public Long getGvender() {
		return this.gvender;
	}
	public void setGshop(Long value) {
		this.gshop = value;
	}

	public Long getGshop() {
		return this.gshop;
	}
	public void setGclassification(Long value) {
		this.gclassification = value;
	}

	public Long getGclassification() {
		return this.gclassification;
	}
	public void setGbrand(Long value) {
		this.gbrand = value;
	}

	public Long getGbrand() {
		return this.gbrand;
	}
	public void setGputawaytime(Date value) {
		this.gputawaytime = value;
	}

	public Date getGputawaytime() {
		return this.gputawaytime;
	}
	public void setGunshelvetime(Date value) {
		this.gunshelvetime = value;
	}

	public Date getGunshelvetime() {
		return this.gunshelvetime;
	}
	public void setGarea(Long value) {
		this.garea = value;
	}

	public Long getGarea() {
		return this.garea;
	}
	public void setGsalescope(Long value) {
		this.gsalescope = value;
	}

	public Long getGsalescope() {
		return this.gsalescope;
	}
	public void setGprofitability(Long value) {
		this.gprofitability = value;
	}

	public Long getGprofitability() {
		return this.gprofitability;
	}
	public void setGsalesvolume(Long value) {
		this.gsalesvolume = value;
	}

	public Long getGsalesvolume() {
		return this.gsalesvolume;
	}
	public void setGfreight(Long value) {
		this.gfreight = value;
	}

	public Long getGfreight() {
		return this.gfreight;
	}
	public void setGmaindirection(Long value) {
		this.gmaindirection = value;
	}

	public Long getGmaindirection() {
		return this.gmaindirection;
	}
	public void setGunitname(String value) {
		this.gunitname = value;
	}

	public String getGunitname() {
		return this.gunitname;
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
	public void setGdescription(byte[] value) {
		this.gdescription = value;
	}

	public byte[] getGdescription() {
		return this.gdescription;
	}
	public void setGproductioninperson(String value) {
		this.gproductioninperson = value;
	}

	public String getGproductioninperson() {
		return this.gproductioninperson;
	}
	public void setGproductioninaddress(String value) {
		this.gproductioninaddress = value;
	}

	public String getGproductioninaddress() {
		return this.gproductioninaddress;
	}
	public void setGpickperson(String value) {
		this.gpickperson = value;
	}

	public String getGpickperson() {
		return this.gpickperson;
	}
	public void setGpickaddress(String value) {
		this.gpickaddress = value;
	}

	public String getGpickaddress() {
		return this.gpickaddress;
	}
	public void setGdeposit(Long value) {
		this.gdeposit = value;
	}

	public Long getGdeposit() {
		return this.gdeposit;
	}
	public void setGcopyid(Long value) {
		this.gcopyid = value;
	}

	public Long getGcopyid() {
		return this.gcopyid;
	}
	public void setIsPutway(Long value) {
		this.isPutway = value;
	}

	public Long getIsPutway() {
		return this.isPutway;
	}
	public void setIsChecked(Long value) {
		this.isChecked = value;
	}

	public Long getIsChecked() {
		return this.isChecked;
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
	public void setIsMultiplebuy(Long value) {
		this.isMultiplebuy = value;
	}

	public Long getIsMultiplebuy() {
		return this.isMultiplebuy;
	}
	public void setIsServicegoods(Long value) {
		this.isServicegoods = value;
	}

	public Long getIsServicegoods() {
		return this.isServicegoods;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
