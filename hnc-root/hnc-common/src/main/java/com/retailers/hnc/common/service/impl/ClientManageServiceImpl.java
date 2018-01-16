
package com.retailers.hnc.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.dao.ClientManageMapper;
import com.retailers.hnc.common.entity.OpeningEmpClient;
import com.retailers.hnc.common.entity.WxAuthUser;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.service.OpeningEmpClientService;
import com.retailers.hnc.common.service.WxAuthUserService;
import com.retailers.hnc.common.vo.ClientManageVo;
import com.retailers.hnc.common.vo.OpeningVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：客户管理Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-26 13:47:05
 */
@Service("clientmanageService")
public class ClientManageServiceImpl implements ClientManageService {
	@Autowired
	private ClientManageMapper clientManageMapper;
	@Autowired
	private WxAuthUserService wxAuthUserService;
	@Autowired
	private OpeningEmpClientService openingEmpClientService;
	public ClientManage saveClientManage(ClientManage clientManage) {
		int status = clientManageMapper.saveClientManage(clientManage);
		return status == 1 ? clientManage : null;
	}
	public boolean updateClientManage(ClientManage clientManage) {
		ClientManage manage = queryClientManageByTmId(clientManage.getTmId());
		clientManage.setVersion(manage.getVersion());
		int status = clientManageMapper.updateClientManage(clientManage);
		return status == 1 ? true : false;
	}
	public ClientManage queryClientManageByTmId(Long tmId) {
		return clientManageMapper.queryClientManageByTmId(tmId);
	}

	public ClientManageVo queryClientManageVoByTmId(Long tmId) {
		return clientManageMapper.queryClientManageVoByTmId(tmId);
	}

	public Pagination<ClientManageVo> queryClientManageList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<ClientManageVo> page = new Pagination<ClientManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ClientManageVo> list = clientManageMapper.queryClientManageList(page);
		page.setData(list);
		return page;
	}
	public Pagination<ClientManageVo> queryClientManageListWeb(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<ClientManageVo> page = new Pagination<ClientManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ClientManageVo> list = clientManageMapper.queryClientManageListWeb(page);
		for(ClientManageVo clientManageVo:list){
			String hids = clientManageVo.getHids();
			String fids = clientManageVo.getFids();
			openingEmpClientService.addFloorsAndHourses(hids,fids,clientManageVo);
		}
		page.setData(list);
		return page;
	}
	public boolean deleteClientManageByTmId(Long tmId) {
		ClientManage clientManage = queryClientManageByTmId(tmId);
		clientManage.setIsDelete(1);
		return updateClientManage(clientManage);
	}

	public Integer queryCurClientCount() {
		return clientManageMapper.queryCurClientCount();
	}

	public Integer queryClientCount() {
		return clientManageMapper.queryClientCount();
	}
	public Long queryClientManageIdByOpenid(String openid){
		Map params = new HashMap();
		params.put("isDelete",0L);
		params.put("wauOpenid",openid);
		List<WxAuthUser> wxAuthUserList = wxAuthUserService.queryWxAuthUserList(params,1,1).getData();
		if(ObjectUtils.isNotEmpty(wxAuthUserList)){
			Long wauUid = wxAuthUserList.get(0).getWauUid();
			return wauUid;
		}
		return null;
	}
	public List<ClientManageVo> queryClientManagerCount(){
		return clientManageMapper.queryClientManagerCount();
	}
	public OpeningVo queryEarlyCanComeIn(Long cid){
		return clientManageMapper.queryEarlyCanComeIn(cid);
	}

	public Pagination<ClientManageVo> queryClientManageVoList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<ClientManageVo> page = new Pagination<ClientManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ClientManageVo> list = clientManageMapper.queryClientManageVoList(page);
		page.setData(list);
		return page;
	}
}

