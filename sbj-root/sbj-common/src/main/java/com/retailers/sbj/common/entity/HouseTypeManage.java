package com.retailers.sbj.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 描述：管理员对象
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2018-01-15 18:13:01
 */
public class HouseTypeManage implements java.io.Serializable {

	/**户型ID*/
	@NotEmpty
	private Long htId;
	/**户型名称*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String htTypeName;
	/**房屋类型*/
	@NotEmpty
	@Length(min = 1, max = 200)
	private String htType;
	/**户型面积*/
	@NotEmpty
	private Double htArea;
	/**是否主推(0代表非主推类型，1代表主推类型）*/
	@NotEmpty
	private Integer htRecommend;
	/**户型效果图*/
	@NotEmpty
	private Long htImage;
	/**htShowImg*/
	@NotEmpty
	private Long htShowImg;
	/**户型描述*/
	@NotEmpty
	@Length(min = 1, max = 20000)
	private String htInfo;
	/**是否显示（0代表不显示，1代表显示）默认为1*/
	@NotEmpty
	private Integer isShow;
	/**是否删除（0代表不删除，1代表删除）默认为0*/
	@NotEmpty
	private Integer isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public HouseTypeManage(){
	}

	public HouseTypeManage( Long htId){
		this.htId = htId;
	}

	public void setHtId(Long value) {
		this.htId = value;
	}

	public Long getHtId() {
		return this.htId;
	}
	public void setHtTypeName(String value) {
		this.htTypeName = value;
	}

	public String getHtTypeName() {
		return this.htTypeName;
	}
	public void setHtType(String value) {
		this.htType = value;
	}

	public String getHtType() {
		return this.htType;
	}
	public void setHtArea(Double value) {
		this.htArea = value;
	}

	public Double getHtArea() {
		return this.htArea;
	}
	public void setHtRecommend(Integer value) {
		this.htRecommend = value;
	}

	public Integer getHtRecommend() {
		return this.htRecommend;
	}
	public void setHtImage(Long value) {
		this.htImage = value;
	}

	public Long getHtImage() {
		return this.htImage;
	}
	public void setHtShowImg(Long value) {
		this.htShowImg = value;
	}

	public Long getHtShowImg() {
		return this.htShowImg;
	}
	public void setHtInfo(String value) {
		this.htInfo = value;
	}

	public String getHtInfo() {
		return this.htInfo;
	}
	public void setIsShow(Integer value) {
		this.isShow = value;
	}

	public Integer getIsShow() {
		return this.isShow;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
