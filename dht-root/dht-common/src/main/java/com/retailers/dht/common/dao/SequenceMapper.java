package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.Sequence;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：自增序列DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-21 15:02:05
 */
public interface SequenceMapper {

	/**
	 * 添加自增序列
	 * @param sequence
	 * @return
	 * @author zhongp
	 * @date 2017-11-21 15:02:05
	 */
	public int saveSequence(Sequence sequence);
	/**
	 * 编辑自增序列
	 * @param sequence
	 * @return
	 * @author zhongp
	 * @date 2017-11-21 15:02:05
	 */
	public int updateSequence(Sequence sequence);
	/**
	 * 根据Id删除自增序列
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-21 15:02:05
	 */
	public int deleteSequenceById(Integer id);
	/**
	 * 根据Id查询自增序列
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-21 15:02:05
	 */
	public Sequence querySequenceById(Integer id);
	/**
	 * 查询自增序列列表
	 * @param pagination 分页对象
	 * @return  自增序列列表
	 * @author zhongp
	 * @date 2017-11-21 15:02:05
	 */
	public List<Sequence> querySequenceList(Pagination<Sequence> pagination);

}
