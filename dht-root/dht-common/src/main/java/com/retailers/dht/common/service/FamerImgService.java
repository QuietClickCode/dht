
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.FamerImgVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.FamerImg;
import java.util.Map;
/**
 * 描述：农户背景图片关联表表Service
 * @author yiliangcheng
 * @version 1.0
 * @since 1.8
 * @date 2018-02-09 09:42:03
 */
public interface FamerImgService {

	/**
	 * 添加农户背景图片关联表表
	 * @param famerImg
	 * @return
	 * @author yiliangcheng
	 * @date 2018-02-09 09:42:03
	 */
	public boolean saveFamerImg(FamerImg famerImg);
	/**
	 * 编辑农户背景图片关联表表
	 * @param famerImg
	 * @return
	 * @author yiliangcheng
	 * @date
	 */
	public boolean updateFamerImg(FamerImg famerImg);
	/**
	 * 根据id查询农户背景图片关联表表
	 * @param fimgId
	 * @return famerImg
	 * @author yiliangcheng
	 * @date 2018-02-09 09:42:03
	 */
	public FamerImg queryFamerImgByFimgId(Long fimgId);
	/**
	 * 查询农户背景图片关联表表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author yiliangcheng
	 * @date 2018-02-09 09:42:03
	 */
	public Pagination<FamerImg> queryFamerImgList(Map<String, Object> params, int pageNo, int pageSize);
	public Pagination<FamerImgVo> queryFamerImgVoList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据fimgId删除农户背景图片关联表表
	 * @param fimgId
	 * @return
	 * @author yiliangcheng
	 * @date 2018-02-09 09:42:03
	 */
	public boolean deleteFamerImgByFimgId(Long fimgId);

}


