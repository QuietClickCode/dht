package com.retailers.wx.common.dao;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.wx.common.entity.WxMessage;

import java.util.List;
/**
 * 描述：用户发送消息内容DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-19 01:17:19
 */
public interface WxMessageMapper {

	/**
	 * 添加用户发送消息内容
	 * @param wxMessage
	 * @return
	 * @author zhongp
	 * @date 2017-12-19 01:17:19
	 */
	public int saveWxMessage(WxMessage wxMessage);
	/**
	 * 编辑用户发送消息内容
	 * @param wxMessage
	 * @return
	 * @author zhongp
	 * @date 2017-12-19 01:17:19
	 */
	public int updateWxMessage(WxMessage wxMessage);
	/**
	 * 根据WmId删除用户发送消息内容
	 * @param wmId
	 * @return
	 * @author zhongp
	 * @date 2017-12-19 01:17:19
	 */
	public int deleteWxMessageByWmId(Long wmId);
	/**
	 * 根据WmId查询用户发送消息内容
	 * @param wmId
	 * @return
	 * @author zhongp
	 * @date 2017-12-19 01:17:19
	 */
	public WxMessage queryWxMessageByWmId(Long wmId);
	/**
	 * 查询用户发送消息内容列表
	 * @param pagination 分页对象
	 * @return  用户发送消息内容列表
	 * @author zhongp
	 * @date 2017-12-19 01:17:19
	 */
	public List<WxMessage> queryWxMessageList(Pagination<WxMessage> pagination);

}
