package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.ArticleClassification;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：文章分类DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-25 17:17:40
 */
public interface ArticleClassificationMapper {

	/**
	 * 添加文章分类
	 * @param articleClassification
	 * @return
	 * @author wangjue
	 * @date 2017-10-25 17:17:40
	 */
	public int saveArticleClassification(ArticleClassification articleClassification);
	/**
	 * 编辑文章分类
	 * @param articleClassification
	 * @return
	 * @author wangjue
	 * @date 2017-10-25 17:17:40
	 */
	public int updateArticleClassification(ArticleClassification articleClassification);
	/**
	 * 根据Aid删除文章分类
	 * @param aid
	 * @return
	 * @author wangjue
	 * @date 2017-10-25 17:17:40
	 */
	public int deleteArticleClassificationByAid(Long aid);
	/**
	 * 根据Aid查询文章分类
	 * @param aid
	 * @return
	 * @author wangjue
	 * @date 2017-10-25 17:17:40
	 */
	public ArticleClassification queryArticleClassificationByAid(Long aid);
	/**
	 * 查询文章分类列表
	 * @param pagination 分页对象
	 * @return  文章分类列表
	 * @author wangjue
	 * @date 2017-10-25 17:17:40
	 */
	public List<ArticleClassification> queryArticleClassificationList(Pagination<ArticleClassification> pagination);

}
