package com.retailers.auth.interceptor;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.annotation.Resourse;
import com.retailers.auth.bean.FunctionBean;
import com.retailers.auth.bean.MenuBean;
import com.retailers.auth.bean.ResourseBean;
import com.retailers.auth.constant.MenuConstant;
import com.retailers.auth.service.MenuService;
import com.retailers.tools.utils.ObjectUtils;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;


@Aspect
@Component
public class URLResourceInterceptor implements BeanPostProcessor,
		ApplicationListener<ContextRefreshedEvent> {
	Logger logger = LoggerFactory.getLogger(URLResourceInterceptor.class);
	// /**
	// * 资源注册开关
	// */
	// private String scan = CommonParameter.getMenuResourceRegSwitch();
	// /**
	// * 单服务器启动
	// */
	// private String singleServer = CommonParameter.getSingleServer();
	//
	//资源路径
	Map<String,Object> resourseMap = new HashMap<String, Object>();
	//父级资源路径
	Set<String> parseResMap = new HashSet<String>();

	// 菜单
	private Set<MenuBean> menus = new HashSet<MenuBean>();
	// 节点
	private Set<ResourseBean> resourseBeans = new HashSet<ResourseBean>();
	// 功能按钮
	private Set<FunctionBean> functions = new HashSet<FunctionBean>();
	@Autowired
	private MenuService menuService;

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		resolveController(bean);
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public void onApplicationEvent(ContextRefreshedEvent event) {
		//开始注册菜单
		registResource();
	}

	private void resolveController(Object bean) throws BeansException {
		// 以下代码块是注册应用系统的菜单、功能按钮
		Class<?> beanClass = bean.getClass();
		// 应用访问路径
		String path = "";
		// 获取@RequestMapping注解在Class上的value(path)值
		if (beanClass.isAnnotationPresent(RequestMapping.class)) {
			logger.info(beanClass + ", is annatation RequestMapping ->"
					+ beanClass.isAnnotationPresent(RequestMapping.class));
			RequestMapping requestMapping = (RequestMapping) beanClass
					.getAnnotation(RequestMapping.class);
			if (!ObjectUtils.isEmpty(requestMapping)) {
				String[] values = requestMapping.value();
				if (!ObjectUtils.isEmpty(values) && values.length > 0) {
					path = values[0];
					if (!ObjectUtils.isEmpty(path)) {
						if (!path.substring(0, 1).equals("/"))
							path = "/" + path;
					}
				}
			}
		}
		/**
		 * 注册节点 注解在实体类上
		 */
		if (beanClass.isAnnotationPresent(Resourse.class)) {
			Resourse resourse = (Resourse) beanClass
					.getAnnotation(Resourse.class);
			if (!ObjectUtils.isEmpty(resourse)) {
				addResource(resourse);
			}
		}
		/**
		 * 注册节点 注解在变量上
		 */
		Field[] fields = beanClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Resourse.class)) {
				Resourse resourse = (Resourse) field
						.getAnnotation(Resourse.class);
				addResource(resourse);
			}
		}
		// 获取UserAnnotation中上的注解
		// 获取@Function、@Menu注解在方法上的信息
		Method[] methods = beanClass.getDeclaredMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(RequestMapping.class)) {
				// 判断@Menu是否注解
				if (method.isAnnotationPresent(Menu.class)) {
					// 菜单
					Menu menu = (Menu) method.getAnnotation(Menu.class);
					// 资源名称
					String resource = menu.resourse();
					if (ObjectUtils.isEmpty(resource)) {
						resource = beanClass.getName() + "." + method.getName();
					}
					// 菜单名称
					String label = menu.label();
					// 显示顺序
					Integer order = menu.sort();
					// 菜单图标名称
					String icon = menu.icon();
					// 菜单访问路径
					String methodPath = this.resolveURI(path, method);
					// 菜单项的描述
					String description = menu.description();
					// 变更新的sourse变更前的sourse
					String changeRes = menu.changeRes();
					// 是否有效
					boolean isValid = menu.isValid();
					// 父级资源
					String parentRes = menu.parentRes();
					MenuBean menuBean = new MenuBean();
					menuBean.setResourse(resource);
					menuBean.setIcon(icon);
					menuBean.setLabel(label);
					menuBean.setParentRes(parentRes);
					menuBean.setSort(order);
					menuBean.setDescription(description);
					// 判断菜单是否有效
					int isValid_ = MenuConstant.MENU_IS_VALID_YES;
					if (!isValid) {
						isValid_ = MenuConstant.MENU_IS_VALID_NO;
					}
					menuBean.setIsValid(isValid_);
					menuBean.setUrl(methodPath);
					menuBean.setChangeRes(changeRes);
					addResourse(resource, parentRes);
					menus.add(menuBean);
					// 判断是否注解了资源
				} else if (method.isAnnotationPresent(Resourse.class)) {
					// 菜单
					Resourse resourse = (Resourse) method
							.getAnnotation(Resourse.class);
					addResource(resourse);
				} else if (method.isAnnotationPresent(Function.class)) {
					// 菜单
					Function function = (Function) method
							.getAnnotation(Function.class);
					// 资源名称
					String resource = function.resourse();
					if (ObjectUtils.isEmpty(resource)) {
						resource = beanClass.getName() + "." + method.getName();
					}
					// 菜单名称
					String label = function.label();
					// 显示顺序
					Integer order = function.sort();
					// 菜单图标名称
					String icon = function.icon();
					// 菜单访问路径
					String methodPath = this.resolveURI(path, method);
					// 菜单项的描述
					String description = function.description();
					// 变更新的sourse变更前的sourse
					String changeRes = function.changeRes();
					// 是否有效
					boolean isValid = function.isValid();
					// 父级资源
					String parentRes = function.parentRes();

					FunctionBean functionBean = new FunctionBean();
					functionBean.setResourse(resource);
					functionBean.setIcon(icon);
					functionBean.setLabel(label);
					functionBean.setParentRes(parentRes);
					functionBean.setSort(order);
					functionBean.setDescription(description);
					// 判断菜单是否有效
					int isValid_ = MenuConstant.MENU_IS_VALID_YES;
					if (!isValid) {
						isValid_ = MenuConstant.MENU_IS_VALID_NO;
					}
					functionBean.setIsValid(isValid_);
					functionBean.setUrl(methodPath);
					functionBean.setChangeRes(changeRes);
					addResourse(resource, parentRes);
					functions.add(functionBean);
				}
			}
		}
	}

	/**
	 * 取得资源的跳转路径
	 * @param path 根路径
	 * @param method 方法上配置的路径
	 * @return
	 */
	private String resolveURI(String path, Method method) {
		RequestMapping requestMapping = (RequestMapping) method
				.getAnnotation(RequestMapping.class);

		// 获取@RequestMapping注解在Method上的value(path)值
		String[] values = requestMapping.value();
		String value = "";
		if (ObjectUtils.isNotEmpty(values)) {
			value = values[0];
			if (ObjectUtils.isNotEmpty(value)) {
				value = (value.startsWith("/") ? value : (value = "/" + value));
			}
		}
		if(ObjectUtils.isNotEmpty(path)){
			path=path.startsWith("/")?(path=path.substring(1)):path;
		}
		// Method上的path值=Class.path + Method.path
		String methodPath = ObjectUtils.isEmpty(path) ? value : path + value;
		return methodPath;

	}

	/**
	 * 注册资源（菜单、按钮、portlet、View Component资源）
	 */
	private void registResource()throws BeansException {
		//校验父级资源是否存在
		for(String str:parseResMap){
			if(!resourseMap.containsKey(str)){
				logger.error("上级资源["+str+"]不存在");
				throw new RuntimeException("上级资源["+str+"]不存在");
			}
		}
		//注册节点菜单
		for(ResourseBean resourseBean:resourseBeans){
			logger.info("注册菜单节点:" + resourseBean.getResourse() + ",资源显示名称:"
					+ resourseBean.getLabel() + "---->开始");
			menuService.registerMenuNode(resourseBean);
			logger.info("注册菜单节点:" + resourseBean.getResourse() + ",资源显示名称:"
					+ resourseBean.getLabel() + "---->注册成功!");
		}
		//注册节点菜单
		for(MenuBean menuBean:menus){
			logger.info("注册菜单:" + menuBean.getResourse() + ",资源显示名称:"
					+ menuBean.getLabel() + "，访问路径："+menuBean.getUrl()+"---->开始!");
			menuService.registerMenu(menuBean);
			logger.info("注册菜单:" + menuBean.getResourse() + ",资源显示名称:"
					+ menuBean.getLabel() + "，访问路径："+menuBean.getUrl()+"---->注册成功!");
		}
		//功能按钮注册
		for(FunctionBean functionBean:functions){
			logger.info("注册功能按钮:" + functionBean.getResourse() + ",资源显示名称:"
					+ functionBean.getLabel() + "，访问路径："+functionBean.getUrl()+"---->开始!");
			menuService.registerFunction(functionBean);
			logger.info("注册功能按钮:" + functionBean.getResourse() + ",资源显示名称:"
					+ functionBean.getLabel() + "，访问路径："+functionBean.getUrl()+"---->注册成功!");
		}
	}

	/**
	 * 添加菜单节点
	 *
	 * @param resourse
	 *            注解的菜单节点数据
	 */
	public void addResource(Resourse resourse) throws BeansException {
		// 资源名称
		String resource = resourse.resourse();
		if (ObjectUtils.isEmpty(resource)) {
			throw new RuntimeException("资源路径不能为空");
		}
		// 菜单名称
		String label = resourse.label();
		// 显示顺序
		Integer order = resourse.sort();
		// 菜单图标名称
		String icon = resourse.icon();
		// 菜单项的描述
		String description = resourse.description();
		// 变更新的sourse变更前的sourse
		String changeRes = resourse.changeRes();
		// 是否有效
		boolean isValid = resourse.isValid();
		// 父级资源
		String parentRes = resourse.parentRes();

		ResourseBean resourseBean = new ResourseBean();
		resourseBean.setResourse(resource);
		resourseBean.setIcon(icon);
		resourseBean.setLabel(label);
		resourseBean.setParentRes(parentRes);
		resourseBean.setSort(order);
		resourseBean.setDescription(description);
		// 判断菜单是否有效
		int isValid_ = MenuConstant.MENU_IS_VALID_YES;
		if (!isValid) {
			isValid_ = MenuConstant.MENU_IS_VALID_NO;
		}
		resourseBean.setIsValid(isValid_);
		resourseBean.setChangeRes(changeRes);
		addResourse(resource, parentRes);
		resourseBeans.add(resourseBean);
	}

	/**
	 * 执行菜单、按钮、portlet注册的任务
	 *
	 * @author zhongp
	 *
	 */
	class RegistResourceTask extends TimerTask {

		@Override
		public void run() {
			registResource();
		}
	}
	/**
	 * 添加资源路径
	 * @param resource 资源路径
	 * @param parseRes 父级资源路径
	 */
	public void addResourse(String resource,String parseRes)throws BeansException{
		//判断是否己存在资源
		if(resourseMap.containsKey(resource)){
			throw new RuntimeException("资源己存在:"+resource);
		}
		//添加注入资源
		resourseMap.put(resource, parseRes);
		if(ObjectUtils.isNotEmpty(parseRes)){
			//添加父级资源
			parseResMap.add(parseRes);
		}
	}
}
