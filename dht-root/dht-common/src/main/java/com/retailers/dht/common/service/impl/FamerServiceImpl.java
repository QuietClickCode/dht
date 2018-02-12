
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.Famer;
import com.retailers.dht.common.dao.FamerMapper;
import com.retailers.dht.common.service.FamerService;
import com.retailers.dht.common.vo.FamerVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.dao.AttachmentMapper;
import com.retailers.mybatis.common.entity.Attachment;
import com.retailers.mybatis.common.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：农夫信息表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-02-05 15:09:57
 */
@Service("famerService")
public class FamerServiceImpl implements FamerService {
	@Autowired
	private FamerMapper famerMapper;
	@Autowired
	private AttachmentMapper attachmentMapper;
	@Autowired
	private AttachmentService attachmentService;

	public boolean saveFamer(Famer famer) {
		int status = famerMapper.saveFamer(famer);
		// 图片以使用状态
		Attachment attachment = attachmentMapper.queryAttachmentById(famer.getFimg());
		if (attachment != null) {
			attachment.setStatus(1L);
			attachmentMapper.updateAttachment(attachment);
		}
		return status == 1 ? true : false;
	}
	public boolean updateFamer(Famer famer, Long oldImg) {

		int status = famerMapper.updateFamer(famer);
		// 图片以使用状态
		Attachment attachment = attachmentMapper.queryAttachmentById(famer.getFimg());
		if (attachment != null) {
			attachment.setStatus(1L);
			attachmentMapper.updateAttachment(attachment);
		}

		if (!oldImg.equals(famer.getFimg())) {
			attachmentService.editorAttachment(oldImg,0L);
		}
		return status == 1 ? true : false;
	}
	public Famer queryFamerByFid(Long fid) {
		return famerMapper.queryFamerByFid(fid);
	}

	public Pagination<FamerVo> queryFamerList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<FamerVo> page = new Pagination<FamerVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FamerVo> list = famerMapper.queryFamerList(page);
		for(FamerVo famerVo:list){
			famerVo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+famerVo.getImgUrl());
		}
		page.setData(list);
		return page;
	}
	public boolean deleteFamerByFid(Long fid) {
		int status = famerMapper.deleteFamerByFid(fid);
		return status == 1 ? true : false;
	}
}

