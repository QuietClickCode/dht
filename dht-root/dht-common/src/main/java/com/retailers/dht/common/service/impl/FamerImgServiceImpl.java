
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.FamerImg;
import com.retailers.dht.common.dao.FamerImgMapper;
import com.retailers.dht.common.service.FamerImgService;
import com.retailers.dht.common.vo.FamerImgVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.dao.AttachmentMapper;
import com.retailers.mybatis.common.entity.Attachment;
import com.retailers.mybatis.common.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：农户背景图片关联表表Service
 * @author yiliangcheng
 * @version 1.0
 * @since 1.8
 * @date 2018-02-09 09:42:03
 */
@Service("famerimgService")
public class FamerImgServiceImpl implements FamerImgService {
	@Autowired
	private FamerImgMapper famerImgMapper;
	@Autowired
	private AttachmentService attachmentService;

	public boolean saveFamerImg(FamerImg famerImg) {
		famerImg.setIsDelete(0L);
		int status = famerImgMapper.saveFamerImg(famerImg);
		// 图片以使用状态
		attachmentService.editorAttachment(famerImg.getImgId());
		return status == 1 ? true : false;
	}

	public boolean updateFamerImg(FamerImg famerImg) {
		int status = famerImgMapper.updateFamerImg(famerImg);
		return status == 1 ? true : false;
	}

	public FamerImg queryFamerImgByFimgId(Long fimgId) {
		return famerImgMapper.queryFamerImgByFimgId(fimgId);
	}

	public Pagination<FamerImg> queryFamerImgList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<FamerImg> page = new Pagination<FamerImg>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FamerImg> list = famerImgMapper.queryFamerImgList(page);
		page.setData(list);
		return page;
	}

	public Pagination<FamerImgVo> queryFamerImgVoList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<FamerImgVo> page = new Pagination<FamerImgVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FamerImgVo> list = famerImgMapper.queryFamerImgVoList(page);
		for (FamerImgVo vo : list) {
			vo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL + vo.getImgUrl());
		}
		page.setData(list);
		return page;
	}

	public boolean deleteFamerImgByFimgId(Long fimgId) {
		FamerImg famerImg = famerImgMapper.queryFamerImgByFimgId(fimgId);
		attachmentService.editorAttachment(famerImg.getImgId(),0L);
		famerImg.setIsDelete(1l);
		int status = famerImgMapper.updateFamerImg(famerImg);
		return status == 1 ? true : false;
	}
}

