package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 10:30:30
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
	/**所属地区*/
	@NotEmpty
	private Long garea;
	/**可售范围 以km为单位*/
	@NotEmpty
	private Long gsalescope;
	/**主推方向 0乡村 1城镇 2乡村和城镇*/
	@NotEmpty
	private Long gmaindirection;
	/**商品描述*/
	@NotEmpty
	@Length(min = 1, max = 20000)
	private String gdescription;
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
	/**copy的ID update前在copy中插入一条一样的数据 并且更新此字段*/
	@NotEmpty
	private Long gcopyid;
	/**商品原价*/
	@NotEmpty
	private Long gprice;
	/**审核人*/
	@NotEmpty
	private Long gcheckperson;
	/**审查信息*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String gcheckmessage;
	/**计件单位*/
	@NotEmpty
	@Length(min = 1, max = 20)
	private String gunitname;
	/**是否通过审核 0未审核 1已审核 2未通过审核*/
	@NotEmpty
	private Long isChecked;
	/**是否被删除 0没有删除 1已删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
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
	public void setGmaindirection(Long value) {
		this.gmaindirection = value;
	}

	public Long getGmaindirection() {
		return this.gmaindirection;
	}
	public void setGdescription(String value) {
		this.gdescription = value;
	}

	public String getGdescription() {
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
	public void setGcopyid(Long value) {
		this.gcopyid = value;
	}

	public Long getGcopyid() {
		return this.gcopyid;
	}
	public void setGprice(Long value) {
		this.gprice = value;
	}

	public Long getGprice() {
		return this.gprice;
	}
	public void setGcheckperson(Long value) {
		this.gcheckperson = value;
	}

	public Long getGcheckperson() {
		return this.gcheckperson;
	}
	public void setGcheckmessage(String value) {
		this.gcheckmessage = value;
	}

	public String getGcheckmessage() {
		return this.gcheckmessage;
	}
	public void setGunitname(String value) {
		this.gunitname = value;
	}

	public String getGunitname() {
		return this.gunitname;
	}
	public void setIsChecked(Long value) {
		this.isChecked = value;
	}

	public Long getIsChecked() {
		return this.isChecked;
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
