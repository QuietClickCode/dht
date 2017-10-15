package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：商品与标签关系表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 13:43:52
 */
public class GoodsGoodslabel implements java.io.Serializable {

	/**商品_标签关系ID*/
	@NotEmpty
	private Long glrId;
	/**商品ID*/
	@NotEmpty
	private Long gid;
	/**商品标签ID*/
	@NotEmpty
	private Long glId;
	/**version*/
	@NotEmpty
	private Long version;
	//columns END

	public GoodsGoodslabel(){
	}

	public GoodsGoodslabel( Long glrId){
		this.glrId = glrId;
	}

	public void setGlrId(Long value) {
		this.glrId = value;
	}

	public Long getGlrId() {
		return this.glrId;
	}
	public void setGid(Long value) {
		this.gid = value;
	}

	public Long getGid() {
		return this.gid;
	}
	public void setGlId(Long value) {
		this.glId = value;
	}

	public Long getGlId() {
		return this.glId;
	}
	public void setVersion(Long value) {
		this.version = value;
	}

	public Long getVersion() {
		return this.version;
	}


}
