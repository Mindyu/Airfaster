/**
 * 
 */	

var personNum = 1;
var usernames = "";

function clc() {
	document.getElementById("title").innerHTML = "哈哈";
}

function selectOption(x, y) {
	document.getElementById(x).value = y;
}

function setAction(x, y) {
	console.log(1);
	var lists = document.getElementById(y).getElementsByTagName("li");
	console.log(lists[0].getAttribute("value"));
	for(i = 0; i < lists.length; ++i) {
		lists[i].onclick = function(){
			selectOption(x, this.innerText);
        }
	}
}

function showSTime() {
	getCand();
}

function hideSTime() {
	var F = document.activeElement.id;
	if(F != "sTime" && F != "rTime" && F != "myDrp" && F != "searchDiv")
	$(".drp-popup").hide();
}


//**************************************************************
function showSearch() {
	$("#searchID").addClass("active");
	$("#loginID").removeClass("active");
	$("#bookID").removeClass("active");
	$("#bookViewID").removeClass("active");
	$("#choseID").removeClass("active");
	$("#otherID").removeClass("active");
	$(".search_page").show();
	$(".login_page").hide();
	$(".book_page").hide();
	$(".bookview_page").hide();
	$(".chose_page").hide();
	$(".other_page").hide();
	$(".login_comp_page").hide();
	
	$("#bookID").hide();
	$("#choseID").hide();
	
}

function showLogin() {
	if(usernames != "") { showLoginComp(); return; }
	$("#searchID").removeClass("active");
	$("#loginID").addClass("active");
	$("#bookID").removeClass("active");
	$("#bookViewID").removeClass("active");
	$("#choseID").removeClass("active");
	$("#otherID").removeClass("active");
	$(".search_page").hide();
	$(".regist_page").hide();
	$(".login_comp_page").hide();
	$(".login_page").show();
	$(".book_page").hide();
	$(".bookview_page").hide();
	$(".chose_page").hide();
	$(".other_page").hide();
	
	$("#bookID").hide();
	$("#choseID").hide();
}
function showLoginComp() {
	$("#searchID").removeClass("active");
	$("#loginID").addClass("active");
	$("#bookID").removeClass("active");
	$("#bookViewID").removeClass("active");
	$("#choseID").removeClass("active");
	$("#otherID").removeClass("active");
	$(".search_page").hide();
	$(".regist_page").hide();
	$(".login_page").hide();
	$(".book_page").hide();
	$(".bookview_page").hide();
	$(".chose_page").hide();
	$(".other_page").hide();
	$(".login_comp_page").show();
	
	$("#bookID").hide();
	$("#choseID").hide();
}
function showBook() {
	$("#searchID").removeClass("active");
	$("#loginID").removeClass("active");
	$("#bookID").addClass("active");
	$("#bookViewID").removeClass("active");
	$("#choseID").removeClass("active");
	$("#otherID").removeClass("active");
	$(".search_page").hide();
	$(".regist_page").hide();
	$(".login_page").hide();
	$(".book_page").show();
	$(".bookview_page").hide();
	$(".chose_page").hide();
	$(".other_page").hide();
	$(".login_comp_page").hide();
	
	$("#bookID").show();
	$("#choseID").hide();
}
function showBookView() {
	$("#searchID").removeClass("active");
	$("#loginID").removeClass("active");
	$("#bookID").removeClass("active");
	$("#bookViewID").addClass("active");
	$("#choseID").removeClass("active");
	$("#otherID").removeClass("active");
	$(".search_page").hide();
	$(".regist_page").hide();
	$(".login_page").hide();
	$(".book_page").hide();
	$(".bookview_page").show();
	$(".chose_page").hide();
	$(".other_page").hide();
	$(".login_comp_page").hide();
	
	$("#bookID").hide();
	$("#choseID").hide();
}
function showChose() {
	$("#searchID").removeClass("active");
	$("#loginID").removeClass("active");
	$("#bookID").removeClass("active");
	$("#bookViewID").removeClass("active");
	$("#choseID").addClass("active");
	$("#otherID").removeClass("active");
	$(".search_page").hide();
	$(".regist_page").hide();
	$(".login_page").hide();
	$(".book_page").hide();
	$(".bookview_page").hide();
	$(".chose_page").show();
	$(".other_page").hide();
	$(".login_comp_page").hide();
	
	$("#bookID").hide();
	$("#choseID").show();
}

/*******************************************************************/
$(function(){
	var flag1 = false;
	var flag2 = false;
	var flag3 = false;
	$("#regist_username_input").blur(
			function(){
				var inputName = $("#regist_username_input").val();
				if($.trim(inputName) == ''
                    || $.trim(inputName).length < 2
                    || $.trim(inputName).length > 8){
					$("#usernamespan").html("<font color='red'>用户名不能为空，且用户名长度为2-8</font>");
					flag1 = false;
				}else{
					var inputPassword = $("#regist_passname_input").val();
                    var url = "ValidateUser";
                    var args = {
                        "name" : $.trim(inputName)
                    };
                    $.post(
                            url,
                            args,
                            function(data) {
                                if ($.trim(data) == "error") {
                                    $("#usernamespan")
                                            .html(
                                                    "<font color='red'>此用户名已被占用</font>");
                                    flag1 = false;
                                } else if ($.trim(data) == "success") {
                                    $("#usernamespan").html(
                                            "用户名可用").css({
                                        color : '#0DDC1C'
                                    });
                                    flag1 = true;
                                }
                            })
				}
			}
	)
	
	$("#regist_passname_input").blur(
			function(){
				var inputPassword = $("#regist_passname_input").val();
				if($.trim(inputPassword) == ''
                    || $.trim(inputPassword).length < 5
                    || $.trim(inputPassword).length > 15){
					$("#passwordspan").html("<font color='red'>密码不能为空且长度在5至15位之间</font>");
					flag2 = false;
				}else{
					$("#passwordspan").html("密码可用").css({
						color : '#0DDC1C'
					});
					flag2 = true;
				}
			}
		)
		
	$("#regist_rpassname_input").blur(
			function(){
				var inputPassword = $("#regist_passname_input").val();
				var inputrePassword = $("#regist_rpassname_input").val();
				if($.trim(inputPassword) != $.trim(inputrePassword)){
					$("#repasswordspan").html("<font color='red'>两次输入密码不一致，请检查</font>");
					flag3 = false;
				}else if($.trim(inputPassword) != "" && $.trim(inputrePassword) != ""){
					$("#repasswordspan").html("密码一致").css({
						color : '#0DDC1C'
					});
					flag3 = true;
				}
			}
	)
	
	$("#registerbutton").click(function() {
            if (flag1 && flag2 && flag3) {
                $("#registerform").submit();
            }else{
            	$("#buttonspan").html("<font color='red'>请检查仍存在的错误</font>");
            }
            return false;
        })
});
/*******************************************************************/

function beLogin() {
	$(".login_comp_page").hide();
	$(".login_page").hide();
	$(".regist_page").show();
}
function showPerson() {
	if(personNum>=4) return; 
	personNum++;
	var str = "";
	str += 
	"<div class = \"form-group passages passages_"+personNum+" col-xs-12\" id = \"passages_table_"+personNum+"\">\
		<div class = \"form-group passage col-xs-10\"> \
			<label class=\"sr-only\" for=\"passage_name_input"+personNum+"\">乘客姓名</label>\
			<input type=\"text\" class=\"form-control passage_show\" id=\"passage_name_input"+personNum+"\" name=\"passage_name_input\" placeholder=\"请输入乘客姓名\" style=\"float:left;\">\
		</div>\
		<div class = \"form-group passage col-xs-2\" style=\"float:right;\">\
			<lable id = \"delPassage"+personNum+"\" style=\"padding-top:15px; padding-left:0;float:right;\"> <a href = \"javascript:void(delPerson())\">删 除</a></lable>\
		</div>\
		<div class = \"form-group passage col-xs-12\"> \
			<label class=\"sr-only\" for=\"passage_id_input"+personNum+"\">乘客身份证</label>\
			<input type=\"text\" class=\"form-control passage_show\" id=\"passage_id_input"+personNum+"\" name=\"passage_id_input\" placeholder=\"请输入乘客身份证\" style=\"float:left;\">\
		</div>\
		<div class = \"form-group passage col-xs-12\"> \
			<label class=\"sr-only\" for=\"passage_phone_input"+personNum+"\">乘客电话</label>\
			<input type=\"text\" class=\"form-control passage_show\" id=\"passage_phone_input"+personNum+"\" name=\"passage_phone_input\" placeholder=\"请输入乘客联系电话\" style=\"float:left;\">\
		</div>\
	</div>";
	//$("#addPassage").before("<div><h4><label id = >乘客"+personNum.toString()+"</label></h4></div>");
	$("#addPassage").before(str);
}
function delPerson() {
	if(personNum<=1) return;
	$(".passages_"+personNum.toString()).remove();
	personNum--;
}

function bookbutton_clicked(i) {
	alert(i);
	//alert(usernames);
	if(usernames == "") showLogin();
	else showBook();
	
	
}