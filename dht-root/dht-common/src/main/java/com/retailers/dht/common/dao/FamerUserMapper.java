package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.FamerUser;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：农夫用户关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-02-07 17:16:31
 */
public interface FamerUserMapper {

	/**
	 * 添加农夫用户关系表
	 * @param famerUser
	 * @return
	 * @author fanghui
	 * @date 2018-02-07 17:16:31
	 */
	public int saveFamerUser(FamerUser famerUser);
	/**
	 * 编辑农夫用户关系表
	 * @param famerUser
	 * @return
	 * @author fanghui
	 * @date 2018-02-07 17:16:31
	 */
	public int updateFamerUser(FamerUser famerUser);
	/**
	 * 根据FuId删除农夫用户关系表
	 * @param fuId
	 * @return
	 * @author fanghui
	 * @date 2018-02-07 17:16:31
	 */
	public int deleteFamerUserByFuId(Long fuId);
	/**
	 * 根据FuId查询农夫用户关系表
	 * @param fuId
	 * @return
	 * @author fanghui
	 * @date 2018-02-07 17:16:31
	 */
	public FamerUser queryFamerUserByFuId(Long fuId);
	/**
	 * 查询农夫用户关系表列表
	 * @param pagination 分页对象
	 * @return  农夫用户关系表列表
	 * @author fanghui
	 * @date 2018-02-07 17:16:31
	 */
	public List<FamerUser> queryFamerUserList(Pagination<FamerUser> pagination);

	public List<String> queryFamerUserImgUrlList(Pagination<String> pagination);
}
