
package com.retailers.dht.common.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.constant.SysParameterConfigConstant;
import com.retailers.dht.common.entity.SysParameterConfig;
import com.retailers.dht.common.dao.SysParameterConfigMapper;
import com.retailers.dht.common.service.SysParameterConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：系统参数配置表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-08 11:21:04
 */
@Service("sysparameterconfigService")
public class SysParameterConfigServiceImpl implements SysParameterConfigService {
	Logger logger = LoggerFactory.getLogger(SysParameterConfigServiceImpl.class);

	@Autowired
	private SysParameterConfigMapper sysParameterConfigMapper;
	public boolean saveSysParameterConfig(SysParameterConfig sysParameterConfig) {
		int status = sysParameterConfigMapper.saveSysParameterConfig(sysParameterConfig);
		return status == 1 ? true : false;
	}
	public boolean updateSysParameterConfig(SysParameterConfig sysParameterConfig) {
		int status = sysParameterConfigMapper.updateSysParameterConfig(sysParameterConfig);
		return status == 1 ? true : false;
	}
	public SysParameterConfig querySysParameterConfigByParameterKey(String parameterKey) {
		return sysParameterConfigMapper.querySysParameterConfigByParameterKey(parameterKey);
	}

	public Pagination<SysParameterConfig> querySysParameterConfigList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<SysParameterConfig> page = new Pagination<SysParameterConfig>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<SysParameterConfig> list = sysParameterConfigMapper.querySysParameterConfigList(page);
		page.setData(list);
		return page;
	}

	/**
	 * 初始化系统参数
	 */
	@Async
	public void initSysParamter() {
		long cur=System.currentTimeMillis();
		logger.info("初始化系统参数开始[{}]",cur);
		List<SysParameterConfig> list = sysParameterConfigMapper.querySysParameterConfigAll();
		SysParameterConfigConstant.params = new HashMap<String, Object>();
		for(SysParameterConfig config:list){
			SysParameterConfigConstant.params.put(config.getParameterKey().toUpperCase(), config.getParameterValue());
		}
		logger.info("初始化系统参数结束[{}]，执行时间：{}",System.currentTimeMillis(),System.currentTimeMillis()-cur);
	}
}

