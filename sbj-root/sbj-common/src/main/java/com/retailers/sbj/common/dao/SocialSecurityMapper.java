package com.retailers.sbj.common.dao;
import com.retailers.sbj.common.entity.SocialSecurity;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：模拟社保卡信息绑定数据表DAO
 * @author boylin
 * @version 1.0
 * @since 1.8
 * @date 2018-03-02 15:50:28
 */
public interface SocialSecurityMapper {

	/**
	 * 判断手机号是否为社保局预留号码
	 * @param socialSecurity
	 * @return
	 */
	public List<SocialSecurity> querySocialSecurity(SocialSecurity socialSecurity);

	/**
	 * 添加模拟社保卡信息绑定数据表
	 * @param socialSecurity
	 * @return
	 * @author boylin
	 * @date 2018-03-02 15:50:28
	 */
	public int saveSocialSecurity(SocialSecurity socialSecurity);
	/**
	 * 编辑模拟社保卡信息绑定数据表
	 * @param socialSecurity
	 * @return
	 * @author boylin
	 * @date 2018-03-02 15:50:28
	 */
	public int updateSocialSecurity(SocialSecurity socialSecurity);
	/**
	 * 根据Id删除模拟社保卡信息绑定数据表
	 * @param id
	 * @return
	 * @author boylin
	 * @date 2018-03-02 15:50:28
	 */
	public int deleteSocialSecurityById(Long id);
	/**
	 * 根据Id查询模拟社保卡信息绑定数据表
	 * @param id
	 * @return
	 * @author boylin
	 * @date 2018-03-02 15:50:28
	 */
	public SocialSecurity querySocialSecurityById(Long id);
	/**
	 * 查询模拟社保卡信息绑定数据表列表
	 * @param pagination 分页对象
	 * @return  模拟社保卡信息绑定数据表列表
	 * @author boylin
	 * @date 2018-03-02 15:50:28
	 */
	public List<SocialSecurity> querySocialSecurityList(Pagination<SocialSecurity> pagination);

}
