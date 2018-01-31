
package com.retailers.razz.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.razz.common.entity.ArticleType;
import com.retailers.razz.common.vo.ArticleTypeVo;

import java.util.List;
import java.util.Map;
/**
 * 描述：文章类别Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2018-01-30 14:34:45
 */
public interface ArticleTypeService {

	/**
	 * 添加文章类别
	 * @param articleType
	 * @return
	 * @author wangjue
	 * @date 2018-01-30 14:34:45
	 */
	public boolean saveArticleType(ArticleType articleType);
	/**
	 * 编辑文章类别
	 * @param articleType
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateArticleType(ArticleType articleType);
	/**
	 * 根据id查询文章类别
	 * @param tid
	 * @return articleType
	 * @author wangjue
	 * @date 2018-01-30 14:34:45
	 */
	public ArticleType queryArticleTypeByTid(Long tid);
	/**
	 * 查询文章类别列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2018-01-30 14:34:45
	 */
	public Pagination<ArticleType> queryArticleTypeList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据tid删除文章类别
	 * @param tid
	 * @return
	 * @author wangjue
	 * @date 2018-01-30 14:34:45
	 */
	public boolean deleteArticleTypeByTid(Long tid);


	/**
	 * 生成文章类型的树型结构
	 * @return
	 * @author wangjue
	 * @date 2018-01-30 14:34:45
	 */
	public List<ArticleTypeVo> queryArticleTypeTree();
}


