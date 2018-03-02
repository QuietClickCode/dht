package com.retailers.sbj.common.dao;

import com.retailers.sbj.common.entity.Channel;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;

/**
 * 描述：来访渠道DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-26 17:11:32
 */
public interface ChannelMapper {

	/**
	 * 添加来访渠道
	 * @param channel
	 * @return
	 * @author wangjue
	 * @date 2017-12-26 17:11:32
	 */
	public int saveChannel(Channel channel);
	/**
	 * 编辑来访渠道
	 * @param channel
	 * @return
	 * @author wangjue
	 * @date 2017-12-26 17:11:32
	 */
	public int updateChannel(Channel channel);
	/**
	 * 根据Cid删除来访渠道
	 * @param cid
	 * @return
	 * @author wangjue
	 * @date 2017-12-26 17:11:32
	 */
	public int deleteChannelByCid(Long cid);
	/**
	 * 根据Cid查询来访渠道
	 * @param cid
	 * @return
	 * @author wangjue
	 * @date 2017-12-26 17:11:32
	 */
	public Channel queryChannelByCid(Long cid);
	/**
	 * 查询来访渠道列表
	 * @param pagination 分页对象
	 * @return  来访渠道列表
	 * @author wangjue
	 * @date 2017-12-26 17:11:32
	 */
	public List<Channel> queryChannelList(Pagination<Channel> pagination);

}
