
package com.retailers.hnc.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.Channel;
import java.util.Map;
/**
 * 描述：来访渠道Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-26 17:11:32
 */
public interface ChannelService {

	/**
	 * 添加来访渠道
	 * @param channel
	 * @return
	 * @author wangjue
	 * @date 2017-12-26 17:11:32
	 */
	public boolean saveChannel(Channel channel);
	/**
	 * 编辑来访渠道
	 * @param channel
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateChannel(Channel channel);
	/**
	 * 根据id查询来访渠道
	 * @param cid
	 * @return channel
	 * @author wangjue
	 * @date 2017-12-26 17:11:32
	 */
	public Channel queryChannelByCid(Long cid);
	/**
	 * 查询来访渠道列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-12-26 17:11:32
	 */
	public Pagination<Channel> queryChannelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据cid删除来访渠道
	 * @param cid
	 * @return
	 * @author wangjue
	 * @date 2017-12-26 17:11:32
	 */
	public boolean deleteChannelByCid(Long cid);

}


