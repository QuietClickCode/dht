package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.FamerImg;
import com.retailers.dht.common.vo.FamerImgVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：农户背景图片关联表表DAO
 * @author yiliangcheng
 * @version 1.0
 * @since 1.8
 * @date 2018-02-09 09:42:03
 */
public interface FamerImgMapper {

	/**
	 * 添加农户背景图片关联表表
	 * @param famerImg
	 * @return
	 * @author yiliangcheng
	 * @date 2018-02-09 09:42:03
	 */
	public int saveFamerImg(FamerImg famerImg);
	/**
	 * 编辑农户背景图片关联表表
	 * @param famerImg
	 * @return
	 * @author yiliangcheng
	 * @date 2018-02-09 09:42:03
	 */
	public int updateFamerImg(FamerImg famerImg);
	/**
	 * 根据FimgId删除农户背景图片关联表表
	 * @param fimgId
	 * @return
	 * @author yiliangcheng
	 * @date 2018-02-09 09:42:03
	 */
	public int deleteFamerImgByFimgId(Long fimgId);
	/**
	 * 根据FimgId查询农户背景图片关联表表
	 * @param fimgId
	 * @return
	 * @author yiliangcheng
	 * @date 2018-02-09 09:42:03
	 */
	public FamerImg queryFamerImgByFimgId(Long fimgId);
	/**
	 * 查询农户背景图片关联表表列表
	 * @param pagination 分页对象
	 * @return  农户背景图片关联表表列表
	 * @author yiliangcheng
	 * @date 2018-02-09 09:42:03
	 */
	public List<FamerImg> queryFamerImgList(Pagination<FamerImg> pagination);
	public List<FamerImgVo> queryFamerImgVoList(Pagination<FamerImgVo> pagination);

}
