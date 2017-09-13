
package com.retailers.mybatis.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.mybatis.common.entity.Attachment;
import com.retailers.mybatis.common.dao.AttachmentMapper;
import com.retailers.mybatis.common.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：系统附件表(用于存放上传物品)Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-13 14:03:02
 */
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	private AttachmentMapper attachmentMapper;
	public boolean saveAttachment(Attachment attachment) {
		int status = attachmentMapper.saveAttachment(attachment);
		return status == 1 ? true : false;
	}
	public boolean updateAttachment(Attachment attachment) {
		int status = attachmentMapper.updateAttachment(attachment);
		return status == 1 ? true : false;
	}
	public Attachment queryAttachmentById(Long id) {
		return attachmentMapper.queryAttachmentById(id);
	}

	public Pagination<Attachment> queryAttachmentList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<Attachment> page = new Pagination<Attachment>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<Attachment> list = attachmentMapper.queryAttachmentList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteAttachmentById(Long id) {
		int status = attachmentMapper.deleteAttachmentById(id);
		return status == 1 ? true : false;
	}
}

