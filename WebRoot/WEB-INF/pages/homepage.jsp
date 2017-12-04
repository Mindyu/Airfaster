<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>AirFaster</title>
    <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
	<link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap-theme.min.css" rel="stylesheet">
	
	<link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
	<link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.bootcss.com/bootstrap/3.0.1/js/bootstrap.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.0.1/js/button.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.0.1/js/button.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script src = "js/myJs.js?t=2017gdfsdd"></script>
	<link rel="stylesheet"  type="text/css" href="css/myCss.css?t=2017xdgdgsdag" >
	
	<link rel="stylesheet" href="css/style.css" media="screen" type="text/css">
	
  </head>
 	
  <body onclick = hideSTime() >
  	
  	<lable id = "username_val" style="display:none;" >${user.username }</lable>
  	<div class = "fframe">
		<div class="col-md-12 column header" >
			<div class="container">
				<div >
				<br> <h1 class = "title" id = "title" onclick = "location.reload()"> <a href = "javascript:void(0)"> AirFaster  <small>飞机订票系统</small> </a></h1>
				</div>
			</div>
		</div>

		<div class="row clearfix leader">
			<div class="col-md-2 column inLeader">
				<ul class="nav nav-pills nav-stacked">
					<li id = "searchID" onclick=showSearch()>		<a href=javascript:vid(0)><h3>查询</h3></a> </li>
					<li id = "loginID" onclick=showLogin()>  		<a href=javascript:vid(0)><h3>登录</h3></a> </li>
					<li id = "bookViewID" onclick=showBookView()>	<a href=javascript:vid(0)><h3>订单</h3></a> </li>
					<li id = "bookID" class="disabled" style="display:none;"><a href=javascript:vid(0)><h3>订票</h3></a> </li>
					<li id = "choseID" class="disabled"style="display:none;"><a href=javascript:vid(0)><h3>选座</h3></a> </li>
					<li id = "otherID" class="disabled"style="display:none;"><a href=javascript:vid(0)><h3>其他</h3></a> </li>
				</ul>
			</div>
			<!-- 查询表单 -->
			<div class="col-md-10 column mySearch search_page " style="display:none">
				<form class="form-inline" role="form" id = "searchForm" action="${pageContext.request.contextPath}/servlet/SearchServlet" method="post">
					<div class="form-group dropdown col-xs-2 fg">
					    <label class="sr-only" for="startCity">出发城市</label>
					    <input type="text" class="form-control" id="startCity" placeholder="出发城市" data-toggle="dropdown"
					    		name = "startCity" value = "${formbean.startCity}">
					    <ul class="dropdown-menu" id = "startUL" role="menu" aria-labelledby=startCity>
							<!-- <li role="presentation"> <a role="menuitem" tabindex="-1" href=javascript:void(0)>北京</a> </li> -->
							${homepageServlet.cityOptions }
						</ul>
						
					</div>
					
					<div class="form-group dropdown col-xs-2 fg">
					    <label class="sr-only" for="arrivCity">到达城市</label>
					    <input type="text" class="form-control" id="arrivCity" placeholder="到达城市" data-toggle="dropdown"
					    name = "arrivCity" value = "${formbean.arrivCity}">
					    <ul class="dropdown-menu" id = "arrivUL" role="menu" aria-labelledby=sarrivCity>
							${homepageServlet.cityOptions }
						</ul>
					</div>
					
					<div class="form-group has-feedback dropdown col-xs-2 has-feedback fg" id = "searchDiv">
					    <label class="sr-only" for="sTime">出发日期</label>
						<span class="glyphicon glyphicon-calendar form-control-feedback cIcon" aria-hidden="true"></span>
					    <select class='custom-date form-control' name='select' style="display:none" id = "sTime" value = "${formbean.startTime}" >
					      <option value='' disabled selected style="display:none;">出发日期</option>  
					      <option value='7'>7 Days</option>
					      <option value='30'>30 Days</option>
					      <option value='90'>90 Days</option>
					      <option value='365'>365 Days</option>
					      <option value='custom'>Custom</option>
					    </select>
					    <input type="text" class="form-control" id="stTime" placeholder="出发日期" name = "startTime" value = "${formbean.startTime}" onFocus = showSTime()>
					</div>
					
					<div class="form-group dropdown col-xs-2 fg">
					    <label class="sr-only" for="rTime">返回日期</label>
					    <span class="glyphicon glyphicon-calendar form-control-feedback cIcon" aria-hidden="true"></span>
					    <input type="text" class="form-control" id="rTime" placeholder="返航日期" name = "arrivTime" value = "${formbean.arrivTime}" onFocus = showSTime()>
					</div>
					
					<div class="form-group dropdown col-xs-2 fg">
					    <label class="sr-only" for="airline">航空公司(可选)</label>
					    <input type="text" class="form-control" id="airline" placeholder="航空公司(可选)" data-toggle="dropdown"
					    		name = "airlineName" value = "${formbean.airlineName}">
					    <ul class="dropdown-menu" id = "airlineUL" role="menu" aria-labelledby=airline>
							${homepageServlet.airlineOptions }
						</ul>
					</div>		
					
					<button type="submit" class="btn btn-primary col-xs-1 mySearchButton">查询</button>
					
				</form>
			</div>
			
			<!-- 显示表格 -->
			<div class="col-md-10 column show_table search_page " style="display:none">
				<ul class = "col-xs-12 search_header">
					<li class = "col-xs-2" style = "padding-left:0px;padding-right:0px;">航班信息</li>
					<li class = "col-xs-2" style = "padding-left:5%;padding-right:0px;">起飞时间</li>
					<li class = "col-xs-1" style = "padding-left:0px;padding-right:0px;"></li>
					<li class = "col-xs-2" style = "padding-left:5%;padding-right:0;">到达时间</li>
					<li class = "col-xs-2" style = "padding-left:3.5%;padding-right:0px;">优惠</li>
					<li class = "col-xs-2" style = "padding-left:5px;padding-right:0px;">价格</li>
				</ul>
			</div>
			
			<!-- 船班信息================================================ -->
			<c:forEach items = "${bookInfoList}" var = "bookInfo">
 			<div class="col-md-10 column search_div search_page">
				<form class="form-inline" role="form" action="${pageContext.request.contextPath}/servlet/BookServlet" method="post">
				<input type = "text" class="form-control" id = "flight_id_${bookInfo.flight.id}" name = "flight_id"  value = "${bookInfo.flight.id}" style="width:0px; height:0px;padding:0;border:0;">
				<table class = "col-xs-12 search_table">
					<tbody>
						<tr class = "search_table_row">
							<td class = "flight_info col-xs-2">	
								<div> 
									<strong class = "flight_airline">${bookInfo.airline.name }</strong>
									<span class="flight_name"> ${bookInfo.flight.name } </span> 
								</div>
								<div><span class="flight_airplane">${bookInfo.airplane.name }(余票${bookInfo.airplane.leftSeat })</span></div>
							</td>
							<td class = "sTime_info col-xs-2">
								<div> <strong class = "sTimeShow"> ${bookInfo.sTime}</strong> </div>
								<div>${bookInfo.sAirport.name}</div>
							</td>
							<td class = "center col-xs-1 show_arrow">
								<div class = "arrow"></div>
							</td>
							<td class = "aTime_info col-xs-2">	
								<div> <strong class = "sTimeShow">${bookInfo.aTime}</strong> </div>
								<div>${bookInfo.aAirport.name}</div>
							</td>
							<td class = "discount_info col-xs-1">
								<div> <strong class= "discount_show">${bookInfo.discount}折</strong> </div>
							</td>
							<td class = "price_info col-xs-2 price_show">
								<span>
									<span class ="moneyshow"> <dfn class = "money_show">¥</dfn>${bookInfo.flight.cost} </span>
									<i class = "qi"> 起 </i> <br>
								</span>
							</td>
							<td class = "book_info col-xs-2">
								<button type="submit" class="btn btn-primary mySearchButton bookbutton">订票</button>
							</td>
						</tr>
					</tbody>
				</table>
				</form>
			</div>
			</c:forEach>			
			<!-- 登录页面 ================================================ -->
			<div class = "login_page col-xs-5 login_form" style="display:none">
				<form class="form-inline" role="form" action="${pageContext.request.contextPath}/servlet/LoginServlet" method="post">
					<div><h4><label>帐号登录</label></h4></div>
					
					<div class = "login_username" style = "padding-top: 15px;"> 
						<label class="sr-only" for="login_username_input" >用户名</label>
					    <input type="text" class="form-control" id="login_username_input" name = "login_username_input" placeholder="请输入用户名">
					</div>
					<div class = "login_passname" style = "padding-top: 15px;">
						<label class="sr-only" for="login_password_input" >密码</label>
					    <input type="text" class="form-control" id="login_password_input" name = "login_password_input"  placeholder="请输入密码">
					</div>
					<button type="submit" class="btn btn-primary col-xs-5 " style="padding-left:0; margin-top:15px;">登录</button>
					<lable class = "col-xs-5" style="padding-top:15px; float:right;" onclick=beLogin()> <h4> <a href = "javascript:void(0)">注册</a></h4></lable>
				</form>
			</div>
			
			<!-- 登录完成界面  -->
			<div class = "login_comp_page col-xs-5 login_form" style="display:none">
				<form class="form-inline" role="form" action = "javascript:void(0)">
					<div><h4><label>已登录：${user.username }</label></h4></div>
					<button type="submit" class="btn btn-primary col-xs-5 " style="padding-left:0; margin-top:15px;">注销</button>
				</form>
			</div>
			
			<!-- 注册界面 -->
			<div class = "regist_page col-xs-5 regist_form" style="display:none" >
				<form id = "registerform" class="form-inline" role="form" action="${pageContext.request.contextPath}/servlet/RegisterServlet" method="post">
					<div><h4><label>帐号注册</label></h4></div>
					
					<div class = "regist_username" style = "padding-top: 15px;"> 
						<label class="sr-only" for="regist_username_input" >用户名</label>
					    <input type="text" class="form-control" id="regist_username_input" name="regist_username_input" placeholder="请输入用户名">
						<span id = "usernamespan"></span>
					</div>
					<div class = "regist_showname" style = "padding-top: 15px;"> 
						<label class="sr-only" for="regist_showname_input" >昵称</label>
					    <input type="text" class="form-control" id="regist_showname_input" name="regist_showname_input" placeholder="请输入昵称">
						
					</div>
					<div class = "regist_passname" style = "padding-top: 15px;">
						<label class="sr-only" for="regist_passname_input" >密码</label>
					    <input type="text" class="form-control" id="regist_passname_input" name="regist_passname_input" placeholder="请输入密码">
						<span id = "passwordspan"></span>
						
					</div>
					<div class = "regist_rpassname" style = "padding-top: 15px;">
						<label class="sr-only" for="regist_rpassname_input" >重复密码</label>
					    <input type="text" class="form-control" id="regist_rpassname_input" placeholder="请再次输入密码">
						<span id = "repasswordspan"></span>
					</div>
					<button type="button" id="registerbutton" class="btn btn-primary col-xs-5 " style="padding-left:0; margin-top:15px;" >注册</button>
					<lable class = "col-xs-5" style="padding-top:15px; float:right;"> <h4> <a href = "javascript:void(showLogin())" >返回登录</a></h4></lable>
					<span id = "buttonspan"></span>
				</form>
			</div>
			
			<div class = "login_page regist_page login_comp_page col-xs-1 pad_left_zero pad_right_zero divide_line"></div>
			<div class = "login_page regist_page login_comp_page col-xs-5 log" style="display:none"> </div>
			
			<!-- 订票界面 ================================================ -->
			<div class = "book_page col-md-10 book_form" style="display:none; padding-right:0px;">
				<form class="form-inline" role="form" action="${pageContext.request.contextPath}/servlet/BookInfoServlet" method="post">
					<div class = "col-xs-6" >
						<div><h4><label id = >乘客</label></h4></div>
						<div class = "form-group passages passages_1 col-xs-12" id = "passages_table_1">
							<div class = "form-group passage col-xs-10"> 
								<label class="sr-only" for="passage_name_input1">乘客姓名</label>
								<input type="text" class="form-control passage_show" id="passage_name_input1" name="passage_name_input" placeholder="请输入乘客姓名" style="float:left;">
							</div>
							<div class = "form-group passage col-xs-2" style="float:right;">
								<lable id = "delPassage1" style="padding-top:15px; padding-left:0;float:right;"> <a href = "javascript:void(delPerson())">删 除</a></lable>
							</div>
							<div class = "form-group passage col-xs-12"> 
								<label class="sr-only" for="passage_id_input1">乘客身份证</label>
								<input type="text" class="form-control passage_show" id="passage_id_input1" name="passage_id_input" placeholder="请输入乘客身份证" style="float:left;">
							</div>
							<div class = "form-group passage col-xs-12"> 
								<label class="sr-only" for="passage_phone_input1">乘客电话</label>
								<input type="text" class="form-control passage_show" id="passage_phone_input1" name="passage_phone_input" placeholder="请输入乘客联系电话" style="float:left;">
							</div>
						</div>
					
						<lable class = "col-xs-3" id = "addPassage" style="padding-top:15px; padding-left:0;float:left;" onclick=showPerson()> <h4> <a href = "javascript:void(0)">添加乘客</a></h4></lable>
					</div>
					<!-- 将要订的航班信息 -->
					<div class = "col-xs-5" style="float:right; padding-right:0;">
						<div class = "col-xs-12 book_show_info" id = "book_show_info">
							<input type = "text" class="form-control" id = "flight_id_${book_flight.flight.id}" name = "book_flight_id"  value = "${book_flight.flight.id}" style="width:0px; height:0px;padding:0;border:0;">
							<input type = "text" class="form-control" id = "flight_id_${book_flight.flight.id}" name = "book_tot_cost"  value = "${book_flight.fcost}" style="width:0px; height:0px;padding:0;border:0;">
							<div>
								<div><center><h2>
									<strong class = "flight_airline">${book_flight.airline.name}</strong>
									<span class="flight_name"><small> ${book_flight.flight.name} </small></span>
								</h2></center></div>
								
								<div><center><h4>
									<span class="flight_airplane">${book_flight.airplane.name}(余票${book_flight.airplane.leftSeat})</span>
								</h4></center></div>
								<center><div class="centm">
									<div class="cent">
										<h1>${book_flight.sTime}</h1>
										<p>${book_flight.sAirport.name}</p>
									</div>
									<div class="cent arrow"></div>
									<div class="cent" >
										<h1>${book_flight.aTime}</h1>
										<p>${book_flight.aAirport.name}</p>
									</div>
								</div></center>
								
								<div style="text-align:right;"> 
									<h3><strong class= "discount_show">${book_flight.discount}折&emsp;&emsp;&emsp;</strong></h3>
									<h1><span class ="moneyshow"> <dfn class = "money_show">¥&nbsp;</dfn>${book_flight.fcost}&emsp;&emsp;</span><h1>
									<br>
								</div>
							</div>
							
						</div>
						<button type="submit" class="btn btn-primary book_button col-xs-6"><h4>提交订单</h4></button>
					</div>
				</form>
			</div>
			
			<!-- 订单查看界面 ================================================ -->
			<c:forEach items = "${orders}" var = "order">
			<div class = "bookview_page col-md-10 book_viwe_show" style="display:none">
				<form class="form-inline" role="form" id = "searchForm" action="javascript:void(0)" method="post">
				<table class = "col-xs-12 search_table">
					<tbody>
						<tr class = "search_table_row">
							<td class = "flight_info col-xs-2">	
								<div> 
									<strong class = "flight_airline">${order.airline.name }</strong>
									<span class="flight_name"> ${order.flight.name} </span> 
								</div>
								<div><span class="flight_airplane">${order.airplane.name}(中型)</span></div>
							</td>
							<td class = "sTime_info col-xs-2">
								<div> <strong class = "sTimeShow">${order.sTime}</strong> </div>
								<div>${order.sAirport.name}</div>
							</td>
							<td class = "center col-xs-1 show_arrow">
								<div class = "arrow"></div>
							</td>
							<td class = "aTime_info col-xs-2">	
								<div> <strong class = "sTimeShow">${order.aTime}</strong> </div>
								<div>${order.aAirport.name}</div>
							</td>
							<td class = "discount_info col-xs-1">
								<div> <strong class= "discount_show">${order.order.passageNum}人</strong> </div>
							</td>
							<td class = "price_info col-xs-2 price_show">
								<span>
									<span class ="moneyshow"> <dfn class = "money_show">¥</dfn>${order.order.cost} </span>	
								</span>
							</td>
							<td class = "book_info col-xs-2">
								<button type="submit" class="btn btn-primary mySearchButton bookbutton" onclick=showChose() >选座</button>
							</td>
						</tr>
					</tbody>
				</table>
				</form>
			</div>
			</c:forEach>
			<!-- 选座界面 ================================================ -->
			<div class = "chose_page col-md-10" style="display:none">
				<div class = "show_seat col-xs-12"> 
					
				</div>
			<div>
		</div>	
		<!--  弹出日历 -->
		<div style="text-align:center;clear:both;margin-top:360px">
			<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
			<script src="/follow.js" type="text/javascript"></script>
		</div>
	  	<script src="js/jquery.js?t=20171117"></script>
		<script src="js/index.js?t=20171117"></script>
		
  	</div>


<!-- ============================================================= -->
  </body>
  
  <script>
  	//showSearch();
  	
	setAction("startCity", "startUL");
	setAction("arrivCity", "arrivUL");
	setAction("airline", "airlineUL");
	${sessionScope.cur_page}
	usernames = $("#username_val").text();
	init();
  </script>
</html>

<!-- ============================================================================================ -->


