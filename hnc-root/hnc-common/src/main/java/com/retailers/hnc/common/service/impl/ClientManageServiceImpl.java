
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.dao.ClientManageMapper;
import com.retailers.hnc.common.service.ClientManageService;
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
	public boolean saveClientManage(ClientManage clientManage) {
		int status = clientManageMapper.saveClientManage(clientManage);
		return status == 1 ? true : false;
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

	public Pagination<ClientManage> queryClientManageList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<ClientManage> page = new Pagination<ClientManage>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ClientManage> list = clientManageMapper.queryClientManageList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteClientManageByTmId(Long tmId) {
		ClientManage clientManage = queryClientManageByTmId(tmId);
		clientManage.setIsDelete(1);
		return updateClientManage(clientManage);
	}
}

