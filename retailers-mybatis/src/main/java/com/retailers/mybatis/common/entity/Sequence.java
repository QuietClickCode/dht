package com.retailers.mybatis.common.entity;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 描述：自增序列对象
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-21 15:02:05
 */
public class Sequence implements java.io.Serializable {

	/**id*/
	@NotEmpty
	private Integer id;
	/**插入时间*/
	@NotEmpty
	private Long time;
	//columns END

	public Sequence(){
	}

	public Sequence(Integer id){
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}
	public void setTime(Long value) {
		this.time = value;
	}

	public Long getTime() {
		return this.time;
	}


}
