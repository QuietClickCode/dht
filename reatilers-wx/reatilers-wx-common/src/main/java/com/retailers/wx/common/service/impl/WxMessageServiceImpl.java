
package com.retailers.wx.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.wx.common.dao.WxMessageMapper;
import com.retailers.wx.common.entity.WxMessage;
import com.retailers.wx.common.service.WxMessageService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：用户发送消息内容Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-19 01:17:19
 */
@Service("wxmessageService")
public class WxMessageServiceImpl implements WxMessageService {
	@Autowired
	private WxMessageMapper wxMessageMapper;
	public boolean saveWxMessage(WxMessage wxMessage) {
		int status = wxMessageMapper.saveWxMessage(wxMessage);
		return status == 1 ? true : false;
	}
	public boolean updateWxMessage(WxMessage wxMessage) {
		int status = wxMessageMapper.updateWxMessage(wxMessage);
		return status == 1 ? true : false;
	}
	public WxMessage queryWxMessageByWmId(Long wmId) {
		return wxMessageMapper.queryWxMessageByWmId(wmId);
	}

	public Pagination<WxMessage> queryWxMessageList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<WxMessage> page = new Pagination<WxMessage>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<WxMessage> list = wxMessageMapper.queryWxMessageList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteWxMessageByWmId(Long wmId) {
		int status = wxMessageMapper.deleteWxMessageByWmId(wmId);
		return status == 1 ? true : false;
	}
}

