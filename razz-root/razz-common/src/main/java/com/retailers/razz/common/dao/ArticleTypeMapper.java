package com.retailers.razz.common.dao;
import com.retailers.razz.common.entity.ArticleType;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.razz.common.vo.ArticleTypeVo;

import java.util.List;
/**
 * 描述：文章类别DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2018-01-30 14:34:45
 */
public interface ArticleTypeMapper {

	/**
	 * 添加文章类别
	 * @param articleType
	 * @return
	 * @author wangjue
	 * @date 2018-01-30 14:34:45
	 */
	public int saveArticleType(ArticleType articleType);
	/**
	 * 编辑文章类别
	 * @param articleType
	 * @return
	 * @author wangjue
	 * @date 2018-01-30 14:34:45
	 */
	public int updateArticleType(ArticleType articleType);
	/**
	 * 根据Tid删除文章类别
	 * @param tid
	 * @return
	 * @author wangjue
	 * @date 2018-01-30 14:34:45
	 */
	public int deleteArticleTypeByTid(Long tid);
	/**
	 * 根据Tid查询文章类别
	 * @param tid
	 * @return
	 * @author wangjue
	 * @date 2018-01-30 14:34:45
	 */
	public ArticleType queryArticleTypeByTid(Long tid);
	/**
	 * 查询文章类别列表
	 * @param pagination 分页对象
	 * @return  文章类别列表
	 * @author wangjue
	 * @date 2018-01-30 14:34:45
	 */
	public List<ArticleType> queryArticleTypeList(Pagination<ArticleType> pagination);

	/**
	 * 查询文章类别列表
	 * @return  文章类别列表
	 * @author wangjue
	 * @date 2018-01-30 14:34:45
	 */
	public List<ArticleTypeVo> queryAllArticleType();

}
