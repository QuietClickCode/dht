
package com.retailers.wx.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.wx.common.entity.WxMessage;

import java.util.Map;
/**
 * 描述：用户发送消息内容Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-19 01:17:19
 */
public interface WxMessageService {

	/**
	 * 添加用户发送消息内容
	 * @param wxMessage
	 * @return
	 * @author zhongp
	 * @date 2017-12-19 01:17:19
	 */
	public boolean saveWxMessage(WxMessage wxMessage);
	/**
	 * 编辑用户发送消息内容
	 * @param wxMessage
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateWxMessage(WxMessage wxMessage);
	/**
	 * 根据id查询用户发送消息内容
	 * @param wmId
	 * @return wxMessage
	 * @author zhongp
	 * @date 2017-12-19 01:17:19
	 */
	public WxMessage queryWxMessageByWmId(Long wmId);
	/**
	 * 查询用户发送消息内容列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-12-19 01:17:19
	 */
	public Pagination<WxMessage> queryWxMessageList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据wmId删除用户发送消息内容
	 * @param wmId
	 * @return
	 * @author zhongp
	 * @date 2017-12-19 01:17:19
	 */
	public boolean deleteWxMessageByWmId(Long wmId);

}


