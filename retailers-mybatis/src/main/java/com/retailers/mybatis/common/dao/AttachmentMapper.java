package com.retailers.mybatis.common.dao;
import com.retailers.mybatis.common.entity.Attachment;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：系统附件表(用于存放上传物品)DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-13 14:03:02
 */
public interface AttachmentMapper {

	/**
	 * 添加系统附件表(用于存放上传物品)
	 * @param attachment
	 * @return
	 * @author zhongp
	 * @date 2017-09-13 14:03:02
	 */
	public int saveAttachment(Attachment attachment);
	/**
	 * 编辑系统附件表(用于存放上传物品)
	 * @param attachment
	 * @return
	 * @author zhongp
	 * @date 2017-09-13 14:03:02
	 */
	public int updateAttachment(Attachment attachment);
	/**
	 * 根据Id删除系统附件表(用于存放上传物品)
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-09-13 14:03:02
	 */
	public int deleteAttachmentById(Long id);
	/**
	 * 根据Id查询系统附件表(用于存放上传物品)
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-09-13 14:03:02
	 */
	public Attachment queryAttachmentById(Long id);
	/**
	 * 查询系统附件表(用于存放上传物品)列表
	 * @param pagination 分页对象
	 * @return  系统附件表(用于存放上传物品)列表
	 * @author zhongp
	 * @date 2017-09-13 14:03:02
	 */
	public List<Attachment> queryAttachmentList(Pagination<Attachment> pagination);

}
