package com.retailers.dht.common.entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;
/**
 * 描述：城市表对象
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-31 11:21:14
 */
public class City implements java.io.Serializable {

	/**cityid*/
	@NotEmpty
	private Long cityid;
	/**cityname*/
	@NotEmpty
	@Length(min = 1, max = 100)
	private String cityname;
	/**parentid*/
	@NotEmpty
	private Long parentid;
	//columns END

	public City(){
	}

	public City( Long cityid){
		this.cityid = cityid;
	}

	public void setCityid(Long value) {
		this.cityid = value;
	}

	public Long getCityid() {
		return this.cityid;
	}
	public void setCityname(String value) {
		this.cityname = value;
	}

	public String getCityname() {
		return this.cityname;
	}
	public void setParentid(Long value) {
		this.parentid = value;
	}

	public Long getParentid() {
		return this.parentid;
	}


}
