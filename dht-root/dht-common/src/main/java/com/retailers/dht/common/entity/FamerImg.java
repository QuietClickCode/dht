package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：农户背景图片关联表表对象
 * @author yiliangcheng
 * @version 1.0
 * @since 1.8
 * @date 2018-02-09 09:42:03
 */
public class FamerImg implements java.io.Serializable {

	/**背景图片关联ID*/
	@NotEmpty
	private Long fimgId;
	/**农户ID*/
	@NotEmpty
	private Long fid;
	/**图片ID*/
	@NotEmpty
	private Long imgId;
	/**是否删除*/
	@NotEmpty
	private Long isDelete;
	/**版本*/
	@NotEmpty
	private Long version;
	//columns END

	public FamerImg(){
	}

	public FamerImg( Long fimgId){
		this.fimgId = fimgId;
	}

	public void setFimgId(Long value) {
		this.fimgId = value;
	}

	public Long getFimgId() {
		return this.fimgId;
	}
	public void setFid(Long value) {
		this.fid = value;
	}

	public Long getFid() {
		return this.fid;
	}
	public void setImgId(Long value) {
		this.imgId = value;
	}

	public Long getImgId() {
		return this.imgId;
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
