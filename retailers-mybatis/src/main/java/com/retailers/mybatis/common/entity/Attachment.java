package com.retailers.mybatis.common.entity;
import java.util.Date;
/**
 * 描述：系统附件表(用于存放上传物品)对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-13 14:03:02
 */
public class Attachment implements java.io.Serializable {

	/**id*/
	private Long id;
	/**附件唯一id（同一图片压缩后uuid 不变）*/
	private String uuId;
	/**附件名称*/
	private String name;
	/**附件类型*/
	private String type;
	/**附件状态（0 未启用，1 己启用)未启用附件一天后进行删除处理*/
	private Long status;
	/**存放路径（相对位置）*/
	private String path;
	/**创建时间*/
	private Date createTime;

	public Attachment(){
	}

	public Attachment( Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setUuId(String value) {
		this.uuId = value;
	}

	public String getUuId() {
		return this.uuId;
	}
	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}
	public void setType(String value) {
		this.type = value;
	}

	public String getType() {
		return this.type;
	}
	public void setStatus(Long value) {
		this.status = value;
	}

	public Long getStatus() {
		return this.status;
	}
	public void setPath(String value) {
		this.path = value;
	}

	public String getPath() {
		return this.path;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}


}
