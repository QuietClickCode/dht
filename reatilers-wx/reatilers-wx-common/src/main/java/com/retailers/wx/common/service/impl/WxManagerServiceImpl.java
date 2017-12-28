
package com.retailers.wx.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.constant.SysParameterConfigConstant;
import com.retailers.mybatis.common.service.AttachmentService;
import com.retailers.mybatis.common.service.SysParameterConfigService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import com.retailers.wx.common.dao.WxManagerMapper;
import com.retailers.wx.common.enm.WXAccountEnum;
import com.retailers.wx.common.entity.WxAccessToken;
import com.retailers.wx.common.entity.WxManager;
import com.retailers.wx.common.service.WxManagerService;
import com.retailers.wx.common.utils.WxHttpClientUtils;
import com.retailers.wx.common.utils.WxReqUtils;
import com.retailers.wx.common.vo.WxManagerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 描述：公众号管理Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-30 20:28:34
 */
@Service("wxmanagerService")
public class WxManagerServiceImpl implements WxManagerService {
	@Autowired
	private WxManagerMapper wxManagerMapper;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private SysParameterConfigService sysParameterConfigService;

	public boolean saveWxManager(WxManager wxManager) {
		int status = wxManagerMapper.saveWxManager(wxManager);
		return status == 1 ? true : false;
	}
	public boolean updateWxManager(WxManager wxManager) {
		int status = wxManagerMapper.updateWxManager(wxManager);
		return status == 1 ? true : false;
	}
	public WxManager queryWxManagerByWxId(Long wxId) {
		return wxManagerMapper.queryWxManagerByWxId(wxId);
	}

	public Pagination<WxManager> queryWxManagerList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<WxManager> page = new Pagination<WxManager>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<WxManager> list = wxManagerMapper.queryWxManagerList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteWxManagerByWxId(Long wxId) {
		int status = wxManagerMapper.deleteWxManagerByWxId(wxId);
		return status == 1 ? true : false;
	}

    /**
     * 取得当前使用的微信公众号
	 * @return
     */
	public WxManagerVo queryCurUsedWx(WXAccountEnum wxAccountEnum) {
		return wxManagerMapper.queryCurUsedWx(wxAccountEnum.getType());
	}
	@Transactional(rollbackFor = Exception.class)
	public boolean editorWxManager(WxManager wxManager) throws AppException {
		WxManager old = wxManagerMapper.queryCurUsedWx(WXAccountEnum.WX_GZH.getType());
		//判断传入微信appid 与 AppSecret 是否有效
		String rtn = WxReqUtils.getToken(wxManager.getAppId(),wxManager.getAppSecret());
		System.out.println(rtn);
		if(ObjectUtils.isNotEmpty(rtn)){
			JSONObject obj = JSONObject.parseObject(rtn);
			if(obj.containsKey("errcode")){
				throw new AppException(obj.getString("errmsg"));
			}
		}else{
			throw new AppException("传入的appid 或 appsecret 不正确");
		}
		//判断当前是否传二维码
		if(ObjectUtils.isEmpty(wxManager.getWxQrCode())){
			if(ObjectUtils.isNotEmpty(old)&&ObjectUtils.isNotEmpty(old.getWxQrCode())){
				attachmentService.editorAttachment(old.getWxQrCode(), AttachmentConstant.ATTACHMENT_STATUS_NO);
			}
		}else{
			if(ObjectUtils.isEmpty(old)||ObjectUtils.isEmpty(old.getWxQrCode())){
				attachmentService.editorAttachment(wxManager.getWxQrCode());
			}else if(old.getWxQrCode().intValue()!=wxManager.getWxQrCode().intValue()){
				attachmentService.editorAttachment(wxManager.getWxQrCode());
				attachmentService.editorAttachment(old.getWxQrCode(), AttachmentConstant.ATTACHMENT_STATUS_NO);
			}
		}
		boolean isAdd=false;
		wxManager.setWxType(WXAccountEnum.WX_GZH.getType());
		wxManager.setWxDomainName(SysParameterConfigConstant.getValue(SysParameterConfigConstant.MASTER_SERVER_PC_URL));
		wxManager.setWxDomainUrl(StringUtils.concat(SysParameterConfigConstant.getValue(SysParameterConfigConstant.MASTER_SERVER_PC_URL),"/","wechat/sendMsg"));
		if(ObjectUtils.isEmpty(old)){
			wxManager.setIsDelete(0);
			wxManager.setIsValid(0);
			//生成随机数
			wxManager.setWxToken(StringUtils.randomNumberCode(8));
			wxManagerMapper.saveWxManager(wxManager);
		}else{
			wxManagerMapper.deleteWxManagerByWxId(old.getWxId());
			wxManager.setIsDelete(old.getIsDelete());
			wxManager.setIsValid(old.getIsValid());
			wxManager.setVersion(old.getVersion()+1);
			wxManagerMapper.saveWxManager(wxManager);
		}
		sysParameterConfigService.reloadSysParameterConfig();
		return true;
	}
}

