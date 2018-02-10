package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.Famer;
import com.retailers.dht.common.vo.FamerVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：农夫信息表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-02-05 15:09:57
 */
public interface FamerMapper {

	/**
	 * 添加农夫信息表
	 * @param famer
	 * @return
	 * @author fanghui
	 * @date 2018-02-05 15:09:57
	 */
	public int saveFamer(Famer famer);
	/**
	 * 编辑农夫信息表
	 * @param famer
	 * @return
	 * @author fanghui
	 * @date 2018-02-05 15:09:57
	 */
	public int updateFamer(Famer famer);
	/**
	 * 根据Fid删除农夫信息表
	 * @param fid
	 * @return
	 * @author fanghui
	 * @date 2018-02-05 15:09:57
	 */
	public int deleteFamerByFid(Long fid);
	/**
	 * 根据Fid查询农夫信息表
	 * @param fid
	 * @return
	 * @author fanghui
	 * @date 2018-02-05 15:09:57
	 */
	public Famer queryFamerByFid(Long fid);
	/**
	 * 查询农夫信息表列表
	 * @param pagination 分页对象
	 * @return  农夫信息表列表
	 * @author fanghui
	 * @date 2018-02-05 15:09:57
	 */
	public List<FamerVo> queryFamerList(Pagination<FamerVo> pagination);

}
