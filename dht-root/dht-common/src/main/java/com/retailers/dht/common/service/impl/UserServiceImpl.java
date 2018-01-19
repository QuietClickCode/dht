
package com.retailers.dht.common.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.retailers.aliyun.sms.constant.SmsSendRecordConstant;
import com.retailers.aliyun.sms.dao.SmsSendRecordMapper;
import com.retailers.aliyun.sms.entity.SmsSendRecord;
import com.retailers.dht.common.constant.SystemConstant;
import com.retailers.dht.common.constant.UserConstant;
import com.retailers.dht.common.dao.UserCardPackageMapper;
import com.retailers.dht.common.dao.WxAuthUserMapper;
import com.retailers.dht.common.entity.User;
import com.retailers.dht.common.dao.UserMapper;
import com.retailers.dht.common.entity.UserCardPackage;
import com.retailers.dht.common.entity.WxAuthUser;
import com.retailers.dht.common.service.UserService;
import com.retailers.dht.common.view.UserInfoVIew;
import com.retailers.dht.common.vo.UserVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.entity.Attachment;
import com.retailers.mybatis.common.service.AttachmentService;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.DateUtil;
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
	@Autowired
	private WxAuthUserMapper wxAuthUserMapper;
	@Autowired
	private UserCardPackageMapper userCardPackageMapper;

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

	public Pagination<UserVo> queryUserList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<UserVo> page = new Pagination<UserVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<UserVo> list = userMapper.queryUserList(page);
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

	@Transactional(rollbackFor = Exception.class)
	public UserInfoVIew userLogin(String account,String pwd,Boolean isBindWx,Long wxId)throws AppException{
		User user=userMapper.queryUserByAccount(account);
		if(ObjectUtils.isEmpty(user)){
			throw new AppException("用户名或密码错误");
		}
		//输和密码进行加密
		String inPwd="";
		if(user.getUoldPwd().intValue()== SystemConstant.USER_IS_OLD_YES){
			//判断密码是否相同
			inPwd=Md5Encrypt.md5(pwd,SystemConstant.DEFAUT_CHARSET);
		}else{
			inPwd=Md5Encrypt.md5(StringUtils.concat(pwd, DateUtil.dateToString(user.getUcreateTime(), DateUtil.DATE_LONG_SIMPLE_FORMAT)),SystemConstant.DEFAUT_CHARSET);
		}
		if(!inPwd.equalsIgnoreCase(user.getUpwd())){
			throw new AppException("用户名或密码错误");
		}
		if(user.getUstatus().intValue()!= UserConstant.USER_STATUS_NORMAL){
			throw new AppException("用户登陆状态异常");
		}

		//判断是否是绑定操作
		if(ObjectUtils.isNotEmpty(isBindWx)&&isBindWx){
			//根据微信id 取得当前登陆微信数据
			WxAuthUser wxAuthUser=wxAuthUserMapper.queryWxAuthUserByWauId(wxId);
			if(ObjectUtils.isNotEmpty(wxAuthUser)&&ObjectUtils.isEmpty(wxAuthUser.getWauUid())){
				//设备微信关联用户
				wxAuthUser.setWauUid(user.getUid());
				wxAuthUserMapper.updateWxAuthUser(wxAuthUser);
			}
		}
		//判断是否存在卡包信息
		UserCardPackage userCardPackage=userCardPackageMapper.queryUserCardPackageById(user.getUid());
		if(ObjectUtils.isEmpty(userCardPackage)){
			userCardPackage = new UserCardPackage();
			userCardPackage.setUtotalWallet(0l);
			userCardPackage.setUcurWallet(0l);
			userCardPackage.setUtotalIntegral(0l);
			userCardPackage.setUcurIntegral(0l);
			userCardPackage.setUtotalConsume(0l);
			userCardPackage.setUwalletConsumeTotal(0l);
			userCardPackage.setId(user.getUid());
			userCardPackageMapper.saveUserCardPackage(userCardPackage);
		}
		//判断是老用户登陆 重新设置老用户密码加密方式
		if(user.getUoldPwd().intValue()== SystemConstant.USER_IS_OLD_YES){
			inPwd=Md5Encrypt.md5(StringUtils.concat(pwd, DateUtil.dateToString(user.getUcreateTime(), DateUtil.DATE_LONG_SIMPLE_FORMAT)),SystemConstant.DEFAUT_CHARSET);
			user.setUpwd(inPwd);
			user.setUoldPwd(SystemConstant.USER_IS_OLD_NO);
			userMapper.updateUser(user);
		}
		//根据用户取得相应的登陆信息
		UserInfoVIew info=userMapper.queryLoginUserInfoView(user.getUid());
		return info;
	}

	public UserInfoVIew queryUserInfoByUid(Long uid) {
		UserInfoVIew info=userMapper.queryLoginUserInfoView(uid);
		return info;
	}

	@Transactional(rollbackFor = Exception.class)
	public UserInfoVIew wxLoginNoUser(WxAuthUser wxAuthUser) {
		//创建新有用户
		User user = new User();
		user.setIsDelete(com.retailers.auth.constant.SystemConstant.SYS_IS_DELETE_NO);
		user.setUcreateTime(new Date());
		user.setUstatus(UserConstant.USER_STATUS_NORMAL);
		user.setUsex(wxAuthUser.getWauSex());
		user.setUname(wxAuthUser.getWauNickname());
		user.setUoldPwd(0);
		user.setUisOld(0);
		user.setUrecommendId(wxAuthUser.getWauRefereeId());
		userMapper.saveUser(user);
		//创建用户卡包
		UserCardPackage userCardPackage=new UserCardPackage();
		userCardPackage.setId(user.getUid());
		userCardPackage.setUtotalWallet(0l);
		userCardPackage.setUcurWallet(0l);
		userCardPackage.setUtotalIntegral(0l);
		userCardPackage.setUcurIntegral(0l);
		userCardPackage.setId(user.getUid());
		userCardPackage.setUwalletConsumeTotal(0l);
		userCardPackage.setUtotalConsume(0l);
		userCardPackage.setuOtherPayTotal(0l);
		userCardPackage.setUcashCurPrice(0l);
		userCardPackage.setUcashTotalPrice(0l);
		userCardPackageMapper.saveUserCardPackage(userCardPackage);
		wxAuthUser.setWauUid(user.getUid());
		System.out.println(JSON.toJSON(wxAuthUser));
		//修改微信关联用户
		wxAuthUserMapper.relationUser(wxAuthUser.getWauId(),user.getUid());
		//根据用户取得相应的登陆信息
		UserInfoVIew info=userMapper.queryLoginUserInfoView(user.getUid());
		return info;
	}

	/**
	 *
	 * @param sysUid 系统用户id
	 * @param uid 用户id
	 * @param utype 用户类型
	 * @param ufirstCommission 首单提成
	 * @param urecommendCommission 消费提成
	 * @return
	 * @throws AppException
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean editorUserType(Long sysUid, Long uid, Long utype, Long ufirstCommission, Long urecommendCommission) throws AppException {
		User user=userMapper.queryUserByUid(uid);
		if(ObjectUtils.isEmpty(user)){
			throw new AppException("用户不存在");
		}
		if(utype.intValue()==UserConstant.USER_TYPE_PT){
			ufirstCommission=null;
			urecommendCommission=null;
		}
		long total =userMapper.editorUserType(uid,utype,ufirstCommission,urecommendCommission,user.getVersion());
		if(total==0){
			throw new AppException("设置用户类型失败");
		}
		return true;
	}

	/**
	 *
	 * @param uid 用户id
	 * @param module 主推方向（0 乡村，1 城镇）
	 * @return
	 */
	public UserInfoVIew editorUserUseModule(Long uid, Integer module) {
		User user=userMapper.queryUserByUid(uid);
		userMapper.editorUserUseModule(uid,module,user.getVersion());
		UserInfoVIew userInfoVIew=userMapper.queryLoginUserInfoView(uid);
		return null;
	}
}

