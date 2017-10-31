package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsCgfrel;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：地区与运费关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-31 10:49:29
 */
public interface GoodsCgfrelMapper {

	/**
	 * 添加地区与运费关系表
	 * @param goodsCgfrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-31 10:49:29
	 */
	public int saveGoodsCgfrel(GoodsCgfrel goodsCgfrel);
	/**
	 * 编辑地区与运费关系表
	 * @param goodsCgfrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-31 10:49:29
	 */
	public int updateGoodsCgfrel(GoodsCgfrel goodsCgfrel);
	/**
	 * 根据CgfId删除地区与运费关系表
	 * @param cgfId
	 * @return
	 * @author fanghui
	 * @date 2017-10-31 10:49:29
	 */
	public int deleteGoodsCgfrelByCgfId(Long cgfId);
	/**
	 * 根据CgfId查询地区与运费关系表
	 * @param cgfId
	 * @return
	 * @author fanghui
	 * @date 2017-10-31 10:49:29
	 */
	public GoodsCgfrel queryGoodsCgfrelByCgfId(Long cgfId);
	/**
	 * 查询地区与运费关系表列表
	 * @param pagination 分页对象
	 * @return  地区与运费关系表列表
	 * @author fanghui
	 * @date 2017-10-31 10:49:29
	 */
	public List<GoodsCgfrel> queryGoodsCgfrelList(Pagination<GoodsCgfrel> pagination);

}
