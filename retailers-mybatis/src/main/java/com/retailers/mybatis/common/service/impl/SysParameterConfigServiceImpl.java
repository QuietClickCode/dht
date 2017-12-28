
package com.retailers.mybatis.common.service.impl;

import com.retailers.mybatis.common.constant.DBSystemConstant;
import com.retailers.mybatis.common.constant.SysParameterConfigConstant;
import com.retailers.mybatis.common.dao.SysParameterConfigMapper;
import com.retailers.mybatis.common.entity.SysParameterConfig;
import com.retailers.mybatis.common.service.SysParameterConfigService;
import com.retailers.mybatis.common.service.ThreadService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Autowired
	private ThreadService threadService;

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

	/**
	 * 添加服务器
	 */
	private void addServerInfo() {
		logger.info("添加服务器信息开始");
		String serverInfo = DBSystemConstant.getCurServletInfo();
		if(ObjectUtils.isNotEmpty(serverInfo)){
			sysParameterConfigMapper.addServerInfo(serverInfo);
		}
		logger.info("添加服务器信息结束");
	}

	/**
	 * 服务器注册
	 */
	public void registerService() {
		addServerInfo();
	}
	/**
	 * 取得服务器列表
	 * @return
	 */
	private List<String> getServerLists() {
		return sysParameterConfigMapper.queryServerLists();
	}

	/**
	 *重新加载系统参数
	 */
	public void reloadSysParameterConfig() {
		List<String> lists = getServerLists();
		for(String url:lists){
			threadService.loadHttp(url,DBSystemConstant.RELOAD_PARAMS_TO_MEMORY);
		}
	}

	/**
	 *加载微信配置至内存
	 */
	public void reloadWxConfigToMemory() {
		List<String> lists = getServerLists();
		for(String url:lists){
			threadService.loadHttp(url,DBSystemConstant.RELOAD_WX_CONFIG_TO_MEMORY);
		}
	}
}

