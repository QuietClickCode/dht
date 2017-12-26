
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.Channel;
import com.retailers.hnc.common.dao.ChannelMapper;
import com.retailers.hnc.common.service.ChannelService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：来访渠道Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-26 17:11:32
 */
@Service("channelService")
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelMapper channelMapper;
	public boolean saveChannel(Channel channel) {
		int status = channelMapper.saveChannel(channel);
		return status == 1 ? true : false;
	}
	public boolean updateChannel(Channel channel) {
		Channel c = queryChannelByCid(channel.getCid());
		channel.setVersion(c.getVersion());
		int status = channelMapper.updateChannel(channel);
		return status == 1 ? true : false;
	}
	public Channel queryChannelByCid(Long cid) {
		return channelMapper.queryChannelByCid(cid);
	}

	public Pagination<Channel> queryChannelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<Channel> page = new Pagination<Channel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<Channel> list = channelMapper.queryChannelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteChannelByCid(Long cid) {
		Channel channel = queryChannelByCid(cid);
		channel.setIsDelete(1);
		return updateChannel(channel);
	}
}

