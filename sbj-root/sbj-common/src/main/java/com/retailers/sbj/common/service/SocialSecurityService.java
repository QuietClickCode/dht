
package com.retailers.sbj.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.sbj.common.entity.SocialSecurity;
import java.util.Map;
/**
 * 描述：模拟社保卡信息绑定数据表Service
 * @author boylin
 * @version 1.0
 * @since 1.8
 * @date 2018-03-02 15:50:28
 */
public interface SocialSecurityService {

	/**
	 * 判断手机号是否为社保局预留号码
	 * @param socialSecurity
	 * @return
	 */
	public Boolean isReserve(SocialSecurity socialSecurity);

	/**
	 * 添加模拟社保卡信息绑定数据表
	 * @param socialSecurity
	 * @return
	 * @author boylin
	 * @date 2018-03-02 15:50:28
	 */
	public boolean saveSocialSecurity(SocialSecurity socialSecurity);
	/**
	 * 编辑模拟社保卡信息绑定数据表
	 * @param socialSecurity
	 * @return
	 * @author boylin
	 * @date
	 */
	public boolean updateSocialSecurity(SocialSecurity socialSecurity);
	/**
	 * 根据id查询模拟社保卡信息绑定数据表
	 * @param id
	 * @return socialSecurity
	 * @author boylin
	 * @date 2018-03-02 15:50:28
	 */
	public SocialSecurity querySocialSecurityById(Long id);
	/**
	 * 查询模拟社保卡信息绑定数据表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author boylin
	 * @date 2018-03-02 15:50:28
	 */
	public Pagination<SocialSecurity> querySocialSecurityList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除模拟社保卡信息绑定数据表
	 * @param id
	 * @return
	 * @author boylin
	 * @date 2018-03-02 15:50:28
	 */
	public boolean deleteSocialSecurityById(Long id);

}


