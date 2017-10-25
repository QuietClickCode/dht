
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.ArticleClassification;
import java.util.Map;
/**
 * 描述：文章分类Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-25 17:17:40
 */
public interface ArticleClassificationService {

	/**
	 * 添加文章分类
	 * @param articleClassification
	 * @return
	 * @author wangjue
	 * @date 2017-10-25 17:17:40
	 */
	public boolean saveArticleClassification(ArticleClassification articleClassification);
	/**
	 * 编辑文章分类
	 * @param articleClassification
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateArticleClassification(ArticleClassification articleClassification);
	/**
	 * 根据id查询文章分类
	 * @param aid
	 * @return articleClassification
	 * @author wangjue
	 * @date 2017-10-25 17:17:40
	 */
	public ArticleClassification queryArticleClassificationByAid(Long aid);
	/**
	 * 查询文章分类列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-10-25 17:17:40
	 */
	public Pagination<ArticleClassification> queryArticleClassificationList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据aid删除文章分类
	 * @param aid
	 * @return
	 * @author wangjue
	 * @date 2017-10-25 17:17:40
	 */
	public boolean deleteArticleClassificationByAid(Long aid);

}


