package com.retailers.aliyun.sms.dao;
import com.retailers.aliyun.sms.entity.AliyunSmsTemplate;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：阿里云短信模版管理DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-04 15:05:09
 */
public interface AliyunSmsTemplateMapper {

	/**
	 * 添加阿里云短信模版管理
	 * @param aliyunSmsTemplate
	 * @return
	 * @author zhongp
	 * @date 2017-12-04 15:05:09
	 */
	public int saveAliyunSmsTemplate(AliyunSmsTemplate aliyunSmsTemplate);
	/**
	 * 编辑阿里云短信模版管理
	 * @param aliyunSmsTemplate
	 * @return
	 * @author zhongp
	 * @date 2017-12-04 15:05:09
	 */
	public int updateAliyunSmsTemplate(AliyunSmsTemplate aliyunSmsTemplate);
	/**
	 * 根据Id删除阿里云短信模版管理
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-12-04 15:05:09
	 */
	public int deleteAliyunSmsTemplateById(Long id);
	/**
	 * 根据Id查询阿里云短信模版管理
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-12-04 15:05:09
	 */
	public AliyunSmsTemplate queryAliyunSmsTemplateById(Long id);
	/**
	 * 查询阿里云短信模版管理列表
	 * @param pagination 分页对象
	 * @return  阿里云短信模版管理列表
	 * @author zhongp
	 * @date 2017-12-04 15:05:09
	 */
	public List<AliyunSmsTemplate> queryAliyunSmsTemplateList(Pagination<AliyunSmsTemplate> pagination);

	/**
	 * 根据FunCode查询阿里云短信模版管理
	 * @param funCode
	 * @return
	 */
	public AliyunSmsTemplate queryAliyunSmsTemplateByFunCode(String funCode);

}
