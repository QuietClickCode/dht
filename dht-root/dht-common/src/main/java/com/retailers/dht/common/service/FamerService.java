
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.FamerVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.Famer;
import java.util.Map;
/**
 * 描述：农夫信息表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-02-05 15:09:57
 */
public interface FamerService {

	/**
	 * 添加农夫信息表
	 * @param famer
	 * @return
	 * @author fanghui
	 * @date 2018-02-05 15:09:57
	 */
	public boolean saveFamer(Famer famer);
	/**
	 * 编辑农夫信息表
	 * @param famer
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateFamer(Famer famer, Long oldImg);
	/**
	 * 根据id查询农夫信息表
	 * @param fid
	 * @return famer
	 * @author fanghui
	 * @date 2018-02-05 15:09:57
	 */
	public Famer queryFamerByFid(Long fid);
	/**
	 * 查询农夫信息表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2018-02-05 15:09:57
	 */
	public Pagination<FamerVo> queryFamerList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据fid删除农夫信息表
	 * @param fid
	 * @return
	 * @author fanghui
	 * @date 2018-02-05 15:09:57
	 */
	public boolean deleteFamerByFid(Long fid);

}


