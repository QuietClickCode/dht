<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>自定义菜单</title>
	<%@include file="/common/common_bs_head_css.jsp"%>
	<link rel="stylesheet" href="/wx/wx_css/wx_create_menu.css" type="text/css">

</head>
<body style="overflow: hidden">
	<%--<div id="header"></div>--%>
	<div id="body">
		<!-- 自定义菜单使用说明区域 -->
		<div class="main_hd">
			<h4>&nbsp;</h4>
			<a href="" ></a>
		</div>
		<!-- 菜单发布信息 -->
		<%--<div class="main_msg">
			<i class="icon_msg"></i>
			<p class="main_msg_title">菜单编辑中/个性化菜单使用中</p>
			<p class="desc">菜单未发布，请确认菜单编辑完成后点击“保存并发布”同步到手机。当前生效的个性化菜单内容请调用API查看。若停用菜单，请<a href="">点击这里</a></p>
		</div>--%>

		<div class="menu_setting_area">
			<div class="menu_preview_area">
				<div class="mobile_menu_previre">
					<div class="mobile_hd">
						<%--博学生--%>
					</div>
					<div class="mobile_bd">
						<ul class="menu_list" id="wxMenuItem">
							<li class="menu_item" onclick="addTab(0)" id="menu_item_0">
								<i  class='add_menu_icon'></i>
								<div class="sub_pre_menu_box" style="display: none;">
									<ul class="sub_first_menu">
										<li class="sub_menu" onclick="event.stopPropagation();addChildMenu(0)">
											<i class="menu_add"></i>
											<span class="menu_title" style="display: none;">菜单名称</span>
										</li>
									</ul>
								</div>
							</li>
							<li class="menu_item" style="display: none;" id="menu_item_1">
								<i  class='add_menu_icon'></i>
								<div class="sub_pre_menu_box" style="display: none;">
									<ul class="sub_first_menu">
										<li class="sub_menu" onclick="event.stopPropagation();addChildMenu(1)">
											<i class="menu_add"></i>
											<span class="menu_title" style="display: none;">菜单名称</span>
										</li>
									</ul>
								</div>
							</li>
							<li class="menu_item" style="display: none;" id="menu_item_2">
								<i  class='add_menu_icon'></i>

								<div class="sub_pre_menu_box" style="display: none;">
									<ul class="sub_first_menu">
										<li class="sub_menu" onclick="event.stopPropagation();addChildMenu(2)">
											<i class="menu_add"></i>
											<span class="menu_title" style="display: none;">菜单名称</span>
										</li>
									</ul>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>

			<div class="menu_form_area">
				<div class="editor_inner">
					<div class="menu_form_hd">
						<p class="global_info">机构入住</p>
						<div  class="global_extra">
							<a href="javascript:void(0)">删除子菜单</a>
						</div>
					</div>

					<div class="menu_from_body" style="display: block;">
						<div class="menu_info">
							<form class="form-horizontal">
							  <div class="form-group">
							    <label for="inputEmail3" style="font-weight: 400;text-align: justify;width: 100px;" class="col-sm-3 control-label">子菜单名称</label>
							    <div class="col-sm-6">
							      <input type="email" class="form-control menu_child_name" id="inputEmail3" >
							      <p class="help-block">Example block-level help text here.</p>
							    </div>
							  </div>
							</form>
						</div>
						<div class="menu_choose">
							<form class="form-horizontal">
							  <div class="form-group">
							    <label for="inputEmail3" style="font-weight: 400;text-align: justify;width: 100px;" class="col-sm-3 control-label">子菜单内容</label>
							    <div class="col-sm-7">
							    	<form >
							    		 <div class="form-group">
							    		 	<label class="radio-inline">
										  	<input type="radio" name="inlineRadioOptions" data-value="0" checked class="nav_tab"> 发送消息
											</label>
											<label class="radio-inline">
										  	<input type="radio" name="inlineRadioOptions" data-value="1" class="nav_tab"> 跳转网页
											</label>
											<label class="radio-inline">
										 	 <input type="radio" name="inlineRadioOptions" data-value="2" class="nav_tab"> 跳转小程序
											</label>
							    		 </div>
							    	</form>

							    </div>
							  </div>
							</form>
						</div>

						<div class="menu_content_container">
							<div class="menu_content" style="display: block;">
								<div class="tab_navs">
									<span class="tab_nav_left" style="display: none;">
										<span class="tab_nav_switch_left"></span>
									</span>
									<span class="tab_nav_right">
										<span class="tab_nav_switch_right"></span>
									</span>
									<div class="tab_navs_wrp">
										<ul class="tab_list">
											<li class="tab_nav tab_appmsg">
												<a href="javascript:void(0)" data-value="10">
												<i class="icon_msg_sender glyphicon glyphicon-list-alt"></i>
												<span class="msg_tab_title">图文消息</span>
												</a>
											</li>

											<li class="tab_nav tab_appmsg">
												<a href="javascript:void(0)"  data-value="10">
												<i class="icon_msg_sender glyphicon glyphicon-pencil"></i>
												<span class="msg_tab_title">文字</span>
												</a>
											</li>

											<li class="tab_nav tab_appmsg">
												<a href="javascript:void(0)" data-value="10">
												<i class="icon_msg_sender  glyphicon glyphicon-picture"></i>
												<span class="msg_tab_title">图片</span>
												</a>
											</li>

											<li class="tab_nav tab_appmsg">
												<a href="javascript:void(0)" onclick="chooseMsgSender()" data-value="10">
												<i class="icon_msg_sender glyphicon glyphicon-folder-open"></i>
												<span class="msg_tab_title">卡券</span>
												</a>
											</li>

											<li class="tab_nav tab_appmsg">
												<a href="javascript:void(0)"  data-value="10">
												<i class="icon_msg_sender glyphicon glyphicon-ice-lolly"></i>
												<span class="msg_tab_title">语音</span>
												</a>
											</li>

											<li class="tab_nav tab_appmsg">
												<a href="javascript:void(0)" data-value="10">
												<i class="icon_msg_sender glyphicon glyphicon-facetime-video"></i>
												<span class="msg_tab_title">视频</span>
												</a>
											</li>
										</ul>
									</div>
								</div>
								<div class="tab_panel">
									<div class="tab_content" style="display: block;">
										<div class="appMsgArea" id="appMsgArea">
											<div class="appMsg">
												<div class="appmsg_info">
													<em>04月05日</em>
												</div>
												<div class="appmsg_bd">
													<h4 class="appmsg_title">
														<a href="" target="_blank">最新：科林教育会员卡领卡及使用指南</a>
													</h4>
													<div class="appmsg_thumb"></div>
													<p class="previre_mask">
														博学生教育社区学习卡扫码或长按领取科林教育加入博学生教育社区，现特推出学习卡，学习卡可积分、可换礼品、可获得
													</p>
												</div>
											</div>
											<a href="" class="msg_sender_del">删除</a>
										</div>

										<div class="appMsg_info_area" style="display: none;">
											<div class="img_area">
												<div class="msgSendTab">
													<div class="media_cover">
														<span class="create_access">
															<a href="" class="add_gray_wrp">
																<i class="add_gray"></i>
																<strong>从素材库中选择</strong>
															</a>
														</span>
													</div>
													<div class="media_cover" style="margin-left: 5.5%">
														<span class="create_access">
															<a href="" class="add_gray_wrp">
																<i class="add_gray"></i>
																<strong>从素材库中选择</strong>
															</a>
														</span>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="tab_content" style="display: none;">
										<div class="textArea">
											<div class="emotion_editor">
												<textarea id="edit_area" class="form-control" rows="3" maxlength="600"></textarea>
											</div>
											<div class="editor_toolbar">
												<p class="editor_tip">还可以输入<em>600</em>字</p>
											</div>
										</div>
									</div>

									<div class="tab_content" style="display: none;">
										<div class="img_area">
											<div class="msgSendTab">
												<div class="media_cover">
													<span class="create_access">
														<a href="" class="add_gray_wrp">
															<i class="add_gray"></i>
															<strong>从素材库中选择</strong>
														</a>
													</span>
												</div>
												<div class="media_cover" style="margin-left: 5.5%">
													<span class="create_access">
														<a href="" class="add_gray_wrp">
															<i class="add_gray"></i>
															<strong>从素材库中选择</strong>
														</a>
													</span>
												</div>
											</div>
										</div>
									</div>

									<div class="tab_content" style="display: none;">
										<div class="cardmsgArea">
											<div class="msg_card">
												<div class="card_content">
													<img src="/wx/wx_img/0.png" class="card_logo">
													<div class="card_info">
														<p>学习卡</p>
													</div>
												</div>
												<p class="store">博学生</p>
											</div>
											<a href="" style="margin-left: 10px">删除</a>
										</div>
									</div>

									<div class="tab_content" style="display: none;">
										<div class="img_area">
											<div class="msgSendTab">
												<div class="media_cover">
													<span class="create_access">
														<a href="" class="add_gray_wrp">
															<i class="add_gray"></i>
															<strong>从素材库中选择</strong>
														</a>
													</span>
												</div>
												<div class="media_cover" style="margin-left: 5.5%">
													<span class="create_access">
														<a href="" class="add_gray_wrp">
															<i class="add_gray"></i>
															<strong>从素材库中选择</strong>
														</a>
													</span>
												</div>
											</div>
										</div>
									</div>

									<div class="tab_content" style="display: none;">
										<div class="img_area">
											<div class="msgSendTab">
												<div class="media_cover">
													<span class="create_access">
														<a href="" class="add_gray_wrp">
															<i class="add_gray"></i>
															<strong>从素材库中选择</strong>
														</a>
													</span>
												</div>
												<div class="media_cover" style="margin-left: 5.5%">
													<span class="create_access">
														<a href="" class="add_gray_wrp">
															<i class="add_gray"></i>
															<strong>从素材库中选择</strong>
														</a>
													</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="menu_content" style="display: none;">
								<form class="urlForm form-horizontal">
									<p>订阅者点击该子菜单会跳到以下链接</p>
									<div class="form-group">
								    	<label class="col-sm-2 control-label" style="text-align: justify;font-weight: 400;">页面地址</label>
								    	<div class="col-sm-7">
								      	<input type="password" class="form-control" >
								    	</div>
								  	</div>

									<div class="form-group">
									    <label for="inputPassword3" class="col-sm-2 control-label"></label>
									    <div class="col-sm-10">
									      <a href="">从公众号图文消息中选择</a>
									    </div>
								  	</div>
								</form>
							</div>

							<div class="menu_content" style="display: none;">
								<form class="urlForm form-horizontal">
									<p>订阅者点击该子菜单会跳到以下小程序</p>
									<div class="form-group">
								    	<label class="col-sm-2 control-label" style="text-align: justify;font-weight: 400;">小程序</label>
								    	<div class="col-sm-7">
								      		<a href="javascript:void(0)" class="btn btn-default choose_webapp">选择小程序</a>
								    	</div>
								  	</div>

									<div class="form-group">
								    	<label class="col-sm-2 control-label" style="text-align: justify;font-weight: 400;">备用网页</label>
								    	<div class="col-sm-7">
								      	<input type="password" class="form-control" >
								      	<p class="help-block">旧版微信客户端无法支持小程序，用户点击菜单时将会打开备用网页。</p>
								    	</div>
								  	</div>
								</form>
							</div>
						</div>
					</div>

					<div class="viewSender" style="display: none;">
						<div class="sender_tips">已为“我的”添加了5个子菜单，无法设置其他内容。</div>
						<form class="form-horizontal">
						  <div class="form-group">
						    <label for="inputEmail3" style="text-align: justify;font-weight: 400;" class="col-sm-2 control-label">菜单名称</label>
						    <div class="col-sm-6">
						      <input type="text" class="form-control setting_menu_name">
						      <p class="help-block">字数不超过4个汉字或8个字母</p>
						    </div>
						  </div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="send_card ui_toggle" style="display: none;">
			<div class="dialog">
				<div class="dialog_hd">
					<h4>选择卡券</h4>
					<a href="javascript:void(0)" class="closed"></a>
				</div>
				<div class="dialog_bd">
					<div>
						<div class="first_step">
							<div class="card_list">
								<div class="card_container">
									<div class="table_wrp">
										<table class="table">
									      <thead class="thead">
									        <tr>
									        <th></th>
									          <th>卡券类型</th>
									          <th>全部卡券</th>
									          <th>卡券有效期</th>
									          <th>库存</th>
									          <th>微信价</th>
									          <th>卡券状态</th>
									        </tr>
									      </thead>
									      <tbody>
									        <tr>
									        	<td>
									        		<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
									        	</td>
									          <th>1</th>
									          <td>Mark</td>
									          <td>Otto</td>
									          <td>@mdo</td>
									          <td>Otto</td>
									          <td>@mdo</td>
									        </tr>
									        <tr>
									        	<td>
									        		<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
									        	</td>
									          <th>2</th>
									          <td>Jacob</td>
									          <td>Thornton</td>
									          <td>@fat</td>
									          <td>Thornton</td>
									          <td>@fat</td>
									        </tr>
									        <tr>
									        	<td>
									        		<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
									        	</td>
									          <th>3</th>
									          <td>Larry</td>
									          <td>the Bird</td>
									          <td>@twitter</td>
									           <td>the Bird</td>
									          <td>@twitter</td>
									        </tr>
									      </tbody>
								    	</table>
									</div>
									<div class="mini_tips">只能投放普通券</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="dialog_ft">
					<button class="btn btn-default btn_input" type="button">确定</button>
					<button class="btn btn-default btn_input" type="button">取消</button>
				</div>
			</div>
		</div>

		<div class="ui_draggable" style="display:none;"></div>

		<div class="dialog_wrp ui_toggle" style="display: none;">
			<div class="dialog">
				<div class="dialog_hd">
					<h4>选择小程序</h4>
					<a href="javascript:void(0)" class="closed">关闭</a>
				</div>
				<div class="dialog_wrp_bd">
					<div class="menu_link_webapp">
						<div class="link_webapp_desc">请选择已绑定的小程序</div>
						<div class="link_webapp_wrp">
							<div class="link_webapp_box">
								<ul class="wechat_list">
									<li class="webapp_item">
										<div class="webapp_item_innner">
											<div class="webapp_info">
												<img src="/wx/wx_img/1.png" class="weapplink_avatar">
												<strong class="webapplink_name">博学生社区亲子中心</strong>
											</div>
										</div>
									</li>
									<li class="webapp_item">
										<div class="webapp_item_innner">
											<div class="webapp_info">
												<img src="/wx/wx_img/1.png" class="weapplink_avatar">
												<strong class="webapplink_name">博学生社区亲子中心</strong>
											</div>
										</div>
									</li>
									<li class="webapp_item">
										<div class="webapp_item_innner">
											<div class="webapp_info">
												<img src="/wx/wx_img/1.png" class="weapplink_avatar">
												<strong class="webapplink_name">博学生社区亲子中心</strong>
											</div>
										</div>
									</li>
									<li class="webapp_item">
										<div class="webapp_item_innner">
											<div class="webapp_info">
												<img src="/wx/wx_img/1.png" class="weapplink_avatar">
												<strong class="webapplink_name">博学生社区亲子中心</strong>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="dialog_ft">
					<button class="btn btn-default webapp_closed" type="button">确定</button>
					<button class="btn btn-default webapp_closed" type="button">取消</button>
				</div>
			</div>
		</div>

		<div class="del_menu_dialog ui_toggle" style="display: none;">
			<div class="dialog">
				<div class="dialog_hd">
					<h4>温馨提示</h4>
					<a href="javascript:void(0)" class="closed">关闭</a>
				</div>
				<div class="dialog_del_menu_bd">
					<div class="page_msg">
						<div class="inner_group">
							<div class="msg_icon">
								<i class="icon_msg"></i>
							</div>
							<div class="msg_content">
								<h4>删除确认</h4>
								<p>删除后“子菜单名称”菜单下设置的内容将被删除</p>
							</div>
						</div>
					</div>
				</div>
				<div class="dialog_ft">
					<button class="btn btn-default del_menu_closed" type="button">确定</button>
					<button class="btn btn-default del_menu_closed" type="button">取消</button>
				</div>
			</div>
		</div>
	</div>


	<div id="footer"></div>
	<%@include file="/common/common_bs_head_js.jsp"%>
	<script type="text/javascript" src="/wx/wx_js/create_menu.js"></script>
	<script type="text/javascript">
		var wxMenu='${menus}';
		//将微信菜单 转换成json 对像
		var obj = JSON.parse(wxMenu);
		if(obj&&obj.button){
			var buttons=obj.button;
			var count=0;
			for(var row of buttons){
                addTab(count,row.name);
                count++;
			}
		}

        function addTab(index,title){
		    if(!title){
		        title="添加菜单";
			}
            i += 1;
            let item = $(".menu_item").eq(index);
            item.find(".add_menu_icon").hide();
            let $span = $("<span class='menu_item_name'>"+title+"</span>");
            item.append($span);
            item.attr("onclick","updateMenuName("+index+")");
            let nextItem = item.next();
            nextItem.attr("onclick","addTab("+i+")");
            let len = $(".menu_item:visible").length + 1;
            let w = $(".menu_list").width();
            let w1 = $(".menu_list").innerWidth();
            let ow = $(".menu_list").outerWidth();
            console.log(w)
            console.log(w1)
            console.log(ow)
            let $w = w / len;

            if($(".menu_item:visible").length < 3){
                $(".sub_pre_menu_box").css("width",$w+"px");
                nextItem.css("width",$w+"px");
                nextItem.show();
                $(".menu_item").css("width",$w+"px");
                $(".menu_list").append($li);
            }
        }
	</script>
</body>
</html>
