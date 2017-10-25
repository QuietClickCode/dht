
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.ArticleClassification;
import com.retailers.dht.common.dao.ArticleClassificationMapper;
import com.retailers.dht.common.service.ArticleClassificationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：文章分类Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-25 17:17:40
 */
@Service("articleclassificationService")
public class ArticleClassificationServiceImpl implements ArticleClassificationService {
	@Autowired
	private ArticleClassificationMapper articleClassificationMapper;
	public boolean saveArticleClassification(ArticleClassification articleClassification) {
		int status = articleClassificationMapper.saveArticleClassification(articleClassification);
		return status == 1 ? true : false;
	}
	public boolean updateArticleClassification(ArticleClassification articleClassification) {
		int status = articleClassificationMapper.updateArticleClassification(articleClassification);
		return status == 1 ? true : false;
	}
	public ArticleClassification queryArticleClassificationByAid(Long aid) {
		return articleClassificationMapper.queryArticleClassificationByAid(aid);
	}

	public Pagination<ArticleClassification> queryArticleClassificationList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<ArticleClassification> page = new Pagination<ArticleClassification>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ArticleClassification> list = articleClassificationMapper.queryArticleClassificationList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteArticleClassificationByAid(Long aid) {
		int status = articleClassificationMapper.deleteArticleClassificationByAid(aid);
		return status == 1 ? true : false;
	}
}

