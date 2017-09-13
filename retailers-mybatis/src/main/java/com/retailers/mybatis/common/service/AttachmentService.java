
package com.retailers.mybatis.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.mybatis.common.entity.Attachment;
import java.util.Map;
/**
 * 描述：系统附件表(用于存放上传物品)Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-13 14:03:02
 */
public interface AttachmentService {

	/**
	 * 添加系统附件表(用于存放上传物品)
	 * @param attachment
	 * @return
	 * @author zhongp
	 * @date 2017-09-13 14:03:02
	 */
	public boolean saveAttachment(Attachment attachment);
	/**
	 * 编辑系统附件表(用于存放上传物品)
	 * @param attachment
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateAttachment(Attachment attachment);
	/**
	 * 根据id查询系统附件表(用于存放上传物品)
	 * @param id
	 * @return attachment
	 * @author zhongp
	 * @date 2017-09-13 14:03:02
	 */
	public Attachment queryAttachmentById(Long id);
	/**
	 * 查询系统附件表(用于存放上传物品)列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-09-13 14:03:02
	 */
	public Pagination<Attachment> queryAttachmentList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除系统附件表(用于存放上传物品)
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-09-13 14:03:02
	 */
	public boolean deleteAttachmentById(Long id);

}


