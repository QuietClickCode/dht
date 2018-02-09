package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：农户商品关联表表对象
 * @author yiliangcheng
 * @version 1.0
 * @since 1.8
 * @date 2018-02-07 16:35:32
 */
public class FamerGoods implements java.io.Serializable {

	/**农户与商品关系id*/
	@NotEmpty
	private Long fgId;
	/**农户id*/
	@NotEmpty
	private Long fid;
	/**商品id*/
	@NotEmpty
	private Long gid;
	/**是否删除 0不删除 1删除*/
	@NotEmpty
	private Long isDelete;
	/**版本号*/
	@NotEmpty
	private Long version;
	//columns END

	public FamerGoods(){
	}

	public FamerGoods( Long fgId){
		this.fgId = fgId;
	}

	public void setFgId(Long value) {
		this.fgId = value;
	}

	public Long getFgId() {
		return this.fgId;
	}
	public void setFid(Long value) {
		this.fid = value;
	}

	public Long getFid() {
		return this.fid;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
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
