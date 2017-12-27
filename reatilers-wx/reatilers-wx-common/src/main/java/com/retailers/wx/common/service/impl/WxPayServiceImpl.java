
package com.retailers.wx.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.service.AttachmentService;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.wx.common.dao.WxManagerMapper;
import com.retailers.wx.common.dao.WxPayMapper;
import com.retailers.wx.common.enm.WXAccountEnum;
import com.retailers.wx.common.entity.WxManager;
import com.retailers.wx.common.entity.WxPay;
import com.retailers.wx.common.service.WxPayService;
import com.retailers.wx.common.utils.WxReqUtils;
import com.retailers.wx.common.vo.WxPayVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：微信支付设置Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-28 00:26:12
 */
@Service("wxpayService")
public class WxPayServiceImpl implements WxPayService {
	Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);
	@Autowired
	private WxPayMapper wxPayMapper;
	@Autowired
	private WxManagerMapper wxManagerMapper;
	@Autowired
	private AttachmentService attachmentService;

	public boolean saveWxPay(WxPay wxPay) {
		int status = wxPayMapper.saveWxPay(wxPay);
		return status == 1 ? true : false;
	}
	public boolean updateWxPay(WxPay wxPay) {
		int status = wxPayMapper.updateWxPay(wxPay);
		return status == 1 ? true : false;
	}
	public WxPay queryWxPayByWxId(Long wxId) {
		return wxPayMapper.queryWxPayByWxId(wxId);
	}

	public Pagination<WxPay> queryWxPayList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<WxPay> page = new Pagination<WxPay>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<WxPay> list = wxPayMapper.queryWxPayList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteWxPayByWxId(Long wxId) {
		int status = wxPayMapper.deleteWxPayByWxId(wxId);
		return status == 1 ? true : false;
	}

	public WxPayVo queryCurUsedPay() {
		return wxPayMapper.queryCurUsedPay();
	}

	public boolean editorWxPay(WxPay wxPay) throws AppException {
		WxManager wxManager = wxManagerMapper.queryCurUsedWx(WXAccountEnum.WX_GZH.getType());
		if(ObjectUtils.isEmpty(wxManager)){
			throw new AppException("未绑定微信公众号");
		}

		WxPay old=wxPayMapper.queryCurUsedPay();
		if(ObjectUtils.isNotEmpty(old)&&!old.getWxAppId().equals(wxManager.getAppId())){
			logger.info("设置微信支付异常");
			throw new AppException("绑定异常");
		}

		//判断当前是否传二维码
		if(ObjectUtils.isEmpty(wxPay.getWxCertificateCode())){
			if(ObjectUtils.isNotEmpty(old)&&ObjectUtils.isNotEmpty(old.getWxCertificateCode())){
				attachmentService.editorAttachment(old.getWxCertificateCode(), AttachmentConstant.ATTACHMENT_STATUS_NO);
			}
		}else{
			if(ObjectUtils.isEmpty(old)||ObjectUtils.isEmpty(old.getWxCertificateCode())){
				attachmentService.editorAttachment(wxPay.getWxCertificateCode());
			}else if(old.getWxCertificateCode().intValue()!=wxPay.getWxCertificateCode().intValue()){
				attachmentService.editorAttachment(wxPay.getWxCertificateCode());
				attachmentService.editorAttachment(old.getWxCertificateCode(), AttachmentConstant.ATTACHMENT_STATUS_NO);
			}
		}
		wxPay.setWxAppId(wxManager.getAppId());
		if(ObjectUtils.isEmpty(old)){
			wxPay.setIsDelete(0);
			wxPayMapper.saveWxPay(wxPay);
		}else{
			wxPayMapper.deleteWxPayByWxId(old.getWxId());
			wxPay.setVersion(old.getVersion());
			wxPay.setIsDelete(old.getIsDelete());
			wxPay.setVersion(old.getVersion()+1);
			wxPayMapper.saveWxPay(wxPay);
		}
		return true;
	}
}

