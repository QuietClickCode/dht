
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.LogManagerReq;
import com.retailers.dht.common.dao.LogManagerReqMapper;
import com.retailers.dht.common.service.LogManagerReqService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：后台请求日志Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-18 10:02:09
 */
@Service("logmanagerreqService")
public class LogManagerReqServiceImpl implements LogManagerReqService {
	@Autowired
	private LogManagerReqMapper logManagerReqMapper;
	@Async
	public boolean saveLogManagerReq(LogManagerReq logManagerReq) {
		int status = logManagerReqMapper.saveLogManagerReq(logManagerReq);
		return status == 1 ? true : false;
	}
	public boolean updateLogManagerReq(LogManagerReq logManagerReq) {
		int status = logManagerReqMapper.updateLogManagerReq(logManagerReq);
		return status == 1 ? true : false;
	}
	public LogManagerReq queryLogManagerReqById(Long id) {
		return logManagerReqMapper.queryLogManagerReqById(id);
	}

	public Pagination<LogManagerReq> queryLogManagerReqList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<LogManagerReq> page = new Pagination<LogManagerReq>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<LogManagerReq> list = logManagerReqMapper.queryLogManagerReqList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteLogManagerReqById(Long id) {
		int status = logManagerReqMapper.deleteLogManagerReqById(id);
		return status == 1 ? true : false;
	}
}

