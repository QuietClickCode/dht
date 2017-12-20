package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.User;
import com.retailers.dht.common.view.UserInfoVIew;
import com.retailers.dht.common.vo.UserVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 描述：平台会员DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-23 00:47:29
 */
public interface UserMapper {

	/**
	 * 添加平台会员
	 * @param user
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public int saveUser(User user);
	/**
	 * 编辑平台会员
	 * @param user
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public int updateUser(User user);
	/**
	 * 根据Uid删除平台会员
	 * @param uid
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public int deleteUserByUid(Long uid);
	/**
	 * 根据Uid查询平台会员
	 * @param uid
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public User queryUserByUid(Long uid);

	/**
	 * 登陆帐号
	 * @param account
	 * @return
	 */
	public User queryUserByAccount(String account);
	/**
	 * 查询平台会员列表
	 * @param pagination 分页对象
	 * @return  平台会员列表
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public List<UserVo> queryUserList(Pagination<UserVo> pagination);

	/**
	 * 校验手机号是否存在
	 * @param phone
	 * @return
	 */
	public User checkPhone(@Param("phone")String phone);

	/**
	 * 根据用户id 取得缓存数据
	 * @param uId
	 * @return
	 */
	public UserInfoVIew queryLoginUserInfoView(@Param("uid")Long uId);

	/**
	 * 设置用户类型
	 * @param uid 用户id
	 * @param type 用户类型
	 * @param ufirstCommission 首次交易提成
	 * @param urecommendCommission 消费提成
	 * @return
	 */
	public int editorUserType(@Param("uid")Long uid,@Param("utype")Long type,@Param("ufirstCommission")Long ufirstCommission,@Param("urecommendCommission")Long urecommendCommission,@Param("version") Integer version);

	/**
	 * 修改会员类型
	 * @param uid 会员id
	 * @param rechage 充值卡id
	 * @param version 版本号
	 * @return
	 */
	public int editorCustomerType(@Param("uid")Long uid,@Param("rechage")Long rechage,@Param("version") Integer version);

}
