package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.Attachment;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
/**
 * 描述：系统附件表(用于存放上传物品)DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-14 15:23:57
 */
public interface AttachmentMapper {

	/**
	 * 添加系统附件表(用于存放上传物品)
	 * @param attachment
	 * @return
	 * @author zhongp
	 * @date 2017-09-14 15:23:57
	 */
	public int saveAttachment(Attachment attachment);
	/**
	 * 编辑系统附件表(用于存放上传物品)
	 * @param attachment
	 * @return
	 * @author zhongp
	 * @date 2017-09-14 15:23:57
	 */
	public int updateAttachment(Attachment attachment);
	/**
	 * 根据Id删除系统附件表(用于存放上传物品)
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-09-14 15:23:57
	 */
	public int deleteAttachmentById(Long id);
	/**
	 * 根据Id查询系统附件表(用于存放上传物品)
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-09-14 15:23:57
	 */
	public Attachment queryAttachmentById(Long id);
	/**
	 * 查询系统附件表(用于存放上传物品)列表
	 * @param pagination 分页对象
	 * @return  系统附件表(用于存放上传物品)列表
	 * @author zhongp
	 * @date 2017-09-14 15:23:57
	 */
	public List<Attachment> queryAttachmentList(Pagination<Attachment> pagination);

	/**
	 * 批量设置附件被使用
	 * @param attachmentIds
	 * @return
	 */
	public long editorAttachment(@Param("attachmentIds") List<Long> attachmentIds,@Param("status") Long status);

	/**
	 * 取得超使未被使用的附件(两小时)
	 * @param curDate
	 * @return
	 */
	public List<Attachment> queryUnUsedAttachemnt(@Param("curDate")Date curDate);
	/**
	 * 清除未使用附件
	 * @param attIds
	 * @return
	 */
	public long clearUnUsedAttachemnt(@Param("attIds")List<Long> attIds);

}
