
package com.retailers.dht.common.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.retailers.aliyun.sms.constant.SmsSendRecordConstant;
import com.retailers.aliyun.sms.dao.SmsSendRecordMapper;
import com.retailers.aliyun.sms.entity.SmsSendRecord;
import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.entity.Attachment;
import com.retailers.dht.common.entity.User;
import com.retailers.dht.common.dao.UserMapper;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.UserService;
import com.retailers.dht.common.view.UserInfoVIew;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.Md5Encrypt;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：平台会员Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-23 00:47:29
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private SmsSendRecordMapper smsSendRecordMapper;

	public boolean saveUser(User user) {
		int status = userMapper.saveUser(user);
		return status == 1 ? true : false;
	}
	public boolean updateUser(User user) {
		int status = userMapper.updateUser(user);
		return status == 1 ? true : false;
	}
	public User queryUserByUid(Long uid) {
		return userMapper.queryUserByUid(uid);
	}

	public Pagination<User> queryUserList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<User> page = new Pagination<User>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<User> list = userMapper.queryUserList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteUserByUid(Long uid) {
		int status = userMapper.deleteUserByUid(uid);
		return status == 1 ? true : false;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean updateUserName(Long uid, String name) throws AppException {
		User user=userMapper.queryUserByUid(uid);
		user.setUname(name);
		userMapper.updateUser(user);
		return true;
	}

	public boolean updateUserSex(Long uid, int sex) throws AppException {
		User user=userMapper.queryUserByUid(uid);
		user.setUsex(sex);
		userMapper.updateUser(user);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean updateUserHead(Long uid, Long attachmentId) throws AppException {
		User user=userMapper.queryUserByUid(uid);
		Long old=user.getUimgid();
		user.setUimgid(attachmentId);
		userMapper.updateUser(user);
		attachmentService.editorAttachment(attachmentId);
		if(ObjectUtils.isNotEmpty(old)){
			attachmentService.editorAttachment(old, AttachmentConstant.ATTACHMENT_STATUS_NO);
		}
		return true;
	}

	/**
	 * 绑定手机
	 * @param uid 用户id
	 * @param phone 手机号
	 * @param code 验证码
	 * @return
	 * @throws AppException
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean bindPhone(Long uid, String phone, String code) throws AppException {
		Date curDate =new Date();
		SmsSendRecord smsSendRecord=smsSendRecordMapper.queryCurSmsSendRecordByPhone(phone, SmsSendRecordConstant.SMS_SEND_TYPE_BIND_PHONE,code,curDate);
		if(ObjectUtils.isEmpty(smsSendRecord)){
			throw new AppException("验证码己失效");
		}
		User user=userMapper.queryUserByUid(uid);
		user.setUphone(phone);
		userMapper.updateUser(user);
		smsSendRecord.setStatus(SmsSendRecordConstant.SMS_SEND_STAUTS_USE);
		smsSendRecord.setUseDate(curDate);
		smsSendRecordMapper.updateSmsSendRecord(smsSendRecord);
		return true;
	}

	/**
	 * 校验手机号是否己存在
	 * @param uid 用户id
	 * @param phone 手机号
	 * @return
	 * @throws AppException
	 */
	public boolean checkPhone(Long uid, String phone) throws AppException {
		User user=userMapper.checkPhone(phone);
		if(ObjectUtils.isEmpty(user)){
			return true;
		}
		if(user.getUid().intValue()==uid.intValue()){
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param uid 用户id
	 * @param pwd 密码
	 * @param type 密码类型(0 登录密码，1 支付密码)
	 * @return
	 * @throws AppException
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean addPwd(Long uid, String pwd, long type) throws AppException {
		User user=userMapper.queryUserByUid(uid);
		if(ObjectUtils.isEmpty(user)){
			throw new AppException("用户不存在");
		}
		String md5Pwd=Md5Encrypt.md5(StringUtils.concat(user.getUid()+"",pwd));
		//登录密码
		if(type==0){
			if(ObjectUtils.isNotEmpty(user.getUpwd())){
				throw new AppException("请输入原始登录密码");
			}
			user.setUpwd(md5Pwd);
		//支付密码
		}else if(type==1){
			if(ObjectUtils.isNotEmpty(user.getUpayPwd())){
				throw new AppException("请输入原始支付密码");
			}
			user.setUpayPwd(md5Pwd);
		}
		userMapper.updateUser(user);
		return true;
	}

	/**
	 *
	 * @param uid 用户id
	 * @param oldPwd 原始密码
	 * @param pwd 密码
	 * @param type 密码类型(0 登录密码，1 支付密码)
	 * @return
	 * @throws AppException
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean changePwd(Long uid, String oldPwd, String pwd, long type) throws AppException {
		User user=userMapper.queryUserByUid(uid);
		if(ObjectUtils.isEmpty(user)){
			throw new AppException("用户不存在");
		}
		String md5Pwd=Md5Encrypt.md5(StringUtils.concat(user.getUid()+"",pwd));
		String oldMd5=Md5Encrypt.md5(StringUtils.concat(user.getUid()+"",oldPwd));
		System.out.println(oldMd5);
		System.out.println(user.getUpayPwd());
		//登录密码
		if(type==0){
			if(ObjectUtils.isEmpty(user.getUpwd())){
				throw new AppException("原始密码错误");
			}
			if(!oldMd5.equals(user.getUpwd())){
				throw new AppException("原始密码错误");
			}
			user.setUpwd(md5Pwd);
			//支付密码
		}else if(type==1){
			if(ObjectUtils.isEmpty(user.getUpayPwd())){
				throw new AppException("原始支付密码错误");
			}
			if(md5Pwd.equals(user.getUpayPwd())){
				throw new AppException("原始支付密码错误");
			}
			user.setUpayPwd(md5Pwd);
		}
		userMapper.updateUser(user);
		return true;
	}

	/**
	 * 获取用户头像
	 */
	public Attachment queryUserHeader(Long attachmentId) {
		return attachmentService.queryAttachmentById(attachmentId);
	}

	public UserInfoVIew userLogin(String account, String pwd) throws AppException {
		return null;
	}
}

