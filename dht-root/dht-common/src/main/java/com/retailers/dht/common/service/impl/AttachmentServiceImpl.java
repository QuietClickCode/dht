
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.dao.AttachmentMapper;
import com.retailers.dht.common.entity.Attachment;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.Md5Encrypt;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

/**
 * 描述：系统附件表(用于存放上传物品)Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-14 15:23:57
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

	/**
	 * 编辑附件属性
	 * @param attachmentId 商品id
	 * @return
	 */
	public boolean editorAttachment(List<Long> attachmentId) {
		long total = attachmentMapper.editorAttachment(attachmentId);
		if(total>0){
			return true;
		}
		return false;
	}

	/**
	 * 编辑附件属性
	 * @param attachmentId 商品id
	 * @return
	 */
	public boolean editorAttachment(Long attachmentId) {
		List<Long> list = new ArrayList<Long>();
		list.add(attachmentId);
		return editorAttachment(list);
	}

	/**
	 * 清除未被使用的附件
	 */
	@Transactional(rollbackFor = Exception.class)
	public void clearUnUsedAttachemnt() {
		Date curDate =new Date();
		curDate = DateUtil.addHour(curDate,-1);
		List<Attachment> list = attachmentMapper.queryUnUsedAttachemnt(curDate);
		if(ObjectUtils.isNotEmpty(list)&&!list.isEmpty()){
			String removeFile = "";
			List<Long> attIds=new ArrayList<Long>();
			for(Attachment attachment:list){
				if(attachment.getSaveType().intValue()== AttachmentConstant.ATTACHMENT_SAVE_TYPE_REMOTE){
					removeFile+=attachment.getSavePath()+";";
					attIds.add(attachment.getId());
				}
			}
			if(ObjectUtils.isNotEmpty(removeFile)){
//				String url="http://image.kuaiyis.com/filesRemove";
				Map<String,String> params=new HashMap<String,String>();
				long curT = System.currentTimeMillis();
				params.put("uploadSign", Md5Encrypt.md5("99695f8e24bd27ee2f70dba1b19785c6"+curT));
				params.put("time",curT+"");
				params.put("removeFile",removeFile);
				try {
					String str= HttpClientUtil.post(AttachmentConstant.CLEAR_ATTACHMENT_URL,params);
					System.out.println(str);
					attachmentMapper.clearUnUsedAttachemnt(attIds);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}

