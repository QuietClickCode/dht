
package com.retailers.razz.common.service.impl;

import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.razz.common.dao.ArticleTypeMapper;
import com.retailers.razz.common.entity.ArticleType;
import com.retailers.razz.common.service.ArticleTypeService;
import com.retailers.razz.common.vo.ArticleTypeVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：文章类别Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2018-01-30 14:34:45
 */
@Service("articletypeService")
public class ArticleTypeServiceImpl implements ArticleTypeService {
	@Autowired
	private ArticleTypeMapper articleTypeMapper;
	public boolean saveArticleType(ArticleType articleType) {
		int status = articleTypeMapper.saveArticleType(articleType);
		return status == 1 ? true : false;
	}
	public boolean updateArticleType(ArticleType articleType) {
		int status = articleTypeMapper.updateArticleType(articleType);
		return status == 1 ? true : false;
	}
	public ArticleType queryArticleTypeByTid(Long tid) {
		return articleTypeMapper.queryArticleTypeByTid(tid);
	}

	public Pagination<ArticleType> queryArticleTypeList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<ArticleType> page = new Pagination<ArticleType>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ArticleType> list = articleTypeMapper.queryArticleTypeList(page);
		page.setData(list);
		return page;
	}

	public boolean deleteArticleTypeByTid(Long tid) {
		int status = articleTypeMapper.deleteArticleTypeByTid(tid);
		return status == 1 ? true : false;
	}

	public List<ArticleTypeVo> queryArticleTypeTree() {
		List<ArticleTypeVo> articleTypeVos = articleTypeMapper.queryAllArticleType();
		for(ArticleTypeVo vos:articleTypeVos){
			if(vos.getParentId() == null){
				vos.setLevel(1L);
			}else{
				vos.setLevel(2L);
			}
		}
		return articleTypeVos;
	}
}

