package com.retailers.dht.common.entity;
import java.util.Date;
/**
 * 描述：系统附件表(用于存放上传物品)对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-14 15:23:57
 */
public class Attachment implements java.io.Serializable {

	/**id*/
	private Long id;
	/**附件存放类型（0 本地，1 远程)*/
	private Long saveType;
	/**附件名称*/
	private String name;
	/**附件类型*/
	private String type;
	/**附件状态（0 未启用，1 己启用)未启用附件一天后进行删除处理*/
	private Long status;
	/**附件展示路径*/
	private String showUrl;
	/**附件存放的相对路径*/
	private String savePath;
	/**创建时间*/
	private Date createTime;
	//columns END
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

	public Long getSaveType() {
		return saveType;
	}

	public void setSaveType(Long saveType) {
		this.saveType = saveType;
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
	public void setShowUrl(String value) {
		this.showUrl = value;
	}

	public String getShowUrl() {
		return this.showUrl;
	}
	public void setSavePath(String value) {
		this.savePath = value;
	}

	public String getSavePath() {
		return this.savePath;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}


}
