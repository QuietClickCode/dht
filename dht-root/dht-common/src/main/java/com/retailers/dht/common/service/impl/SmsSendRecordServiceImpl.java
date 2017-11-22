
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.SmsSendRecord;
import com.retailers.dht.common.dao.SmsSendRecordMapper;
import com.retailers.dht.common.service.SmsSendRecordService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：短信发送列表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-23 00:38:54
 */
@Service("smssendrecordService")
public class SmsSendRecordServiceImpl implements SmsSendRecordService {
	@Autowired
	private SmsSendRecordMapper smsSendRecordMapper;
	public boolean saveSmsSendRecord(SmsSendRecord smsSendRecord) {
		int status = smsSendRecordMapper.saveSmsSendRecord(smsSendRecord);
		return status == 1 ? true : false;
	}
	public boolean updateSmsSendRecord(SmsSendRecord smsSendRecord) {
		int status = smsSendRecordMapper.updateSmsSendRecord(smsSendRecord);
		return status == 1 ? true : false;
	}
	public SmsSendRecord querySmsSendRecordById(Long id) {
		return smsSendRecordMapper.querySmsSendRecordById(id);
	}

	public Pagination<SmsSendRecord> querySmsSendRecordList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<SmsSendRecord> page = new Pagination<SmsSendRecord>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<SmsSendRecord> list = smsSendRecordMapper.querySmsSendRecordList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteSmsSendRecordById(Long id) {
		int status = smsSendRecordMapper.deleteSmsSendRecordById(id);
		return status == 1 ? true : false;
	}
}

