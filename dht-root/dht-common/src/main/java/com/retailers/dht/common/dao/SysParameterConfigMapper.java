package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.SysParameterConfig;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：系统参数配置表DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-08 11:21:04
 */
public interface SysParameterConfigMapper {

	/**
	 * 添加系统参数配置表
	 * @param sysParameterConfig
	 * @return
	 * @author zhongp
	 * @date 2017-10-08 11:21:04
	 */
	public int saveSysParameterConfig(SysParameterConfig sysParameterConfig);
	/**
	 * 编辑系统参数配置表
	 * @param sysParameterConfig
	 * @return
	 * @author zhongp
	 * @date 2017-10-08 11:21:04
	 */
	public int updateSysParameterConfig(SysParameterConfig sysParameterConfig);
	/**
	 * 根据ParameterKey删除系统参数配置表
	 * @param parameterKey
	 * @return
	 * @author zhongp
	 * @date 2017-10-08 11:21:04
	 */
	public int deleteSysParameterConfigByParameterKey(String parameterKey);
	/**
	 * 根据ParameterKey查询系统参数配置表
	 * @param parameterKey
	 * @return
	 * @author zhongp
	 * @date 2017-10-08 11:21:04
	 */
	public SysParameterConfig querySysParameterConfigByParameterKey(String parameterKey);
	/**
	 * 查询系统参数配置表列表
	 * @param pagination 分页对象
	 * @return  系统参数配置表列表
	 * @author zhongp
	 * @date 2017-10-08 11:21:04
	 */
	public List<SysParameterConfig> querySysParameterConfigList(Pagination<SysParameterConfig> pagination);

	/**
	 * 取得所有的系统参数（初始化到内存中）
	 * @return
	 */
	public List<SysParameterConfig> querySysParameterConfigAll();

}
