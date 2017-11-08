<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/init/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>

	<ol class="breadcrumb">
		 <li class="breadcrumb-item">首页</li>
         <li class="breadcrumb-item">系统管理</li>
         <li class="breadcrumb-item active">用户管理</li>
    </ol>
           
	<div class="container">
		<form role="form">
	         <div class="row-fluid">
		         <div class="form-group">
		         <label class="col-md-1 col_style_label" >登录名</label>
		            <div class="col-md-4 ">
		            	<input class="form-control" id="loginName" name="loginName" type="text" placeholder="请输入登录名..."/>
		            </div>
		            <label class="col-md-1"></label>
		            <label class="col-md-1 col_style_label">电话号码</label>
		            <div class="col-md-4 ">
		            	<input class="form-control" id="phoneNumber" name="phoneNumber" type="text" placeholder="请输入电话号码..."/>
		            </div>
		         </div>
	         </div>   
        </form>
        
        <div align="right" style="padding-top: 50px;">   
		   <div class="btn-group">  
		       <button class="btn btn-primary">  
			     <i class="glyphicon glyphicon-search"></i>查询用户
			   </button>
			   <button class="add btn btn-primary">  
			     <i class="glyphicon glyphicon-plus"></i>添加用户
			   </button>  
			   <button class="modify btn btn-primary">  
			     <i class="glyphicon glyphicon-cog"></i>编辑用户
			   </button>
			   <button class="cancel btn btn-primary">  
			     <i class="glyphicon glyphicon-remove"></i>注销用户
			   </button>  
			</div>
		</div>
        
		<legend style="padding-top: 15px;margin-bottom:0px;"></legend>    
		<table id="mytable"></table>
		
		<!-- 弹出框 -->
		<div class="modal fade" id="themodal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="title"></h4>
					</div>
					<div class="modal-body" id="mydiv">
						<form id="myform" action="userManager/saveUser" method="post" class="form-horizontal" >
	                        <div class="form-group">
	                            <label class="col-xs-2 col_style_label">登录名：</label>
	                            <div class="col-sm-7">
	                                <input type="text" class="form-control" name="loginName" id="loginName" placeholder="输入登录名...不可为空" />
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-xs-2 col_style_label">用户名：</label>
	                            <div class="col-sm-7">
	                                <input type="text" class="form-control" name="userName" id="userName" placeholder="输入用户名...不可为空" />
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-xs-2 col_style_label">登录密码：</label>
	                            <div class="col-sm-7">
	                                <input type="password" class="form-control" name="loginPassword" id="loginPassword" placeholder="登录密码,不少于6个字符...不可为空" />
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-xs-2 col_style_label">确认密码：</label>
	                            <div class="col-sm-7">
	                                <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="再次确认密码...不可为空" />
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-xs-2 col_style_label">电话号码：</label>
	                            <div class="col-sm-7">
	                                <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="手机号或固定电话（固话加区号）" />
	                            </div>
	                        </div>
	                        <div class="form-group">
		                        <label class="col-xs-2 col_style_label">邮箱地址：</label>
		                        <div class="col-sm-7">
		                            <input type="text" class="form-control" id="email" name="email" />
		                        </div>
		                    </div>
	                        <div class="form-group">
	                            <label class="col-xs-2 col_style_label">是否生效：</label>
	                            <div class="col-sm-6">
		                            <select class="form-control" id="validselect" name="isValid">
									     <option value="Y">Y</option>
									     <option value="N">N</option>
									</select>
								</div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-xs-2 col_style_label">性别：</label>
	                            <div class="col-sm-6">
		                                <label>
		                                    <input type="radio" name="sex" value="男" /> 男
		                                </label>
		                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                                <label>
		                                    <input type="radio" name="sex" value="女" /> 女
		                                </label>
	                            </div>
	                        </div>
	                        <input type="hidden" id="userId" name="userId">
	                        <div class="modal-footer">
	                        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>  
								<button id="savebtn" type="submit" class="btn btn-default" >保 存</button>
							</div>
	                    </form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	    var $table = $('#mytable');
	    $table.bootstrapTable({
	    	url: 'userManager/search',        
            method: 'post',                     
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性
            sortable: true,                     //是否启用排序
            pagination: true, 					//是否启用分页
            //sidePagination: "server",         //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            sortOrder: "asc",                   //排序方式
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端
            showColumns: true,                  //是否显示所有的列
            minimumCountColumns: 2,            //最少允许的列数
            singleSelect: true,					//是否单行选择
            clickToSelect: true,                //是否启用点击选中行
            height: 430,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "userId",                 //每一行的唯一标识，一般为主键列
            showToggle: false,                  //是否显示详细视图和列表视图的切换按钮
            detailView: false,                  //是否显示父子表
            showExport: true,                   //是否显示导出
            exportDataType: "basic",            //basic', 'all', 'selected'.
            columns: [{
                checkbox: true
            },{
                field: 'userId',
                title: 'ID',
                visible:false
            }, {
                field: 'loginName',
                title: '登录名',
            }, {
                field: 'userName',
                title: '用户名'
            }, {
                field: 'phoneNumber',
                title: '电话号码'
            }, {
                field: 'email',
                title: '邮箱'
            },{
                field: 'isValid',
                title: '是否有效'
            },{
            	field:'createDate',
            	title:'创建日期'
            }]
	    });
	   
	</script>
	
	<script type="text/javascript">
	
		 $(document).ready(function() { 
			 $("#validselect").select2(); 
			 
			 $('#myform').bootstrapValidator({
			        container: 'tooltip',
			        feedbackIcons: {
			            valid: 'glyphicon glyphicon-ok',
			            invalid: 'glyphicon glyphicon-remove',
			            validating: 'glyphicon glyphicon-refresh'
			        },
			        fields: {
			        	loginName: {
			                validators: {
			                    stringLength: {
			                        min: 2,
			                        message: '登录名不允许少于5个字符!'
			                    },
			                    notEmpty: {
			                        message: '登录名不允许为空!'
			                    }
			                }
			            },
			            userName: {
			                validators: {
			                    stringLength: {
			                        min: 2,
			                        message: '用户名不允许少于2个字符!'
			                    },
			                    notEmpty: {
			                        message: '用户名不允许为空!'
			                    }
			                }
			            },
			            loginPassword:{
			            	validators: {
			                    stringLength: {
			                        min: 6,
			                        message: '密码不允许少于6个字符!'
			                    },
			                    notEmpty: {message:'密码不允许为空!'},
			                    identical: {
			                        field: 'confirmPassword'
			                    },
			                    different: {
			                        field: 'loginName',
			                        message: '不可与登录名相同'
			                    }
			                }
			            },
			            confirmPassword: {
			                validators: {
			                	stringLength: {
			                        min: 6,
			                        message: '密码不允许少于6个字符!'
			                    },
			                    notEmpty: {},
			                    identical: {
			                        field: 'loginPassword'
			                    },
			                    different: {
			                        field: 'loginName',
			                        message: '不可与登录名相同'
			                    }
			                }
			            },
			            email: {
			                validators: {
			                    emailAddress: {
			                        message: '邮箱地址格式错误'
			                    }
			                }
			            },
			            phoneNumber: {
			                validators: {
			                    digits: {
			                        message: '电话号码格式错误'
			                    },
			                    phone:{country:'CN'}
			                }
			            }
			        }
			    });
			 
		 });
		 
		 $(".add").click(function() {
			 $("#mydiv input").each(function() {
				 $(this).val('');
			 });
			 $('#title').text('添加用户');
			 $('#savebtn').text('保存');
			 $("#themodal").modal("show");
		 });
		 
		 $(".cancel").click(function(){
			 var selectContent = $('#mytable').bootstrapTable('getSelections')[0];  
		        if(typeof(selectContent) == 'undefined') {  
		            toastr.warning('请选择一列数据!');  
		            return false;
		        }
		        
			 bootbox.confirm({ 
				  size: 'small',
				  message: "确定要注销用户?", 
				  buttons: {
				        confirm: {
				            label: '确定',
				            className: 'btn-success'
				        },
				        cancel: {
				            label: '取消',
				            className: 'btn-danger'
				        }
				    },
				    callback: function(result){ 
					  if(result){
						  var userId = selectContent.userId;
						  var isVaild = selectContent.isValid;
						  if(isVaild=='N'){
							  toastr.warning("已经是失效用户");
							  return false;
						  }
						  
						  $.ajax({
							  url: "userManager/cancalUser",
							  type: "post",
							  data: {userId:userId},
							  success: function (data, status) {
								  toastr.success(data);
								  $('#mytable').bootstrapTable('refresh');
							  },
							  error: function (data, status) {
								  toastr.error("操作失败!!!");
							  }
						  }); 
					  }
				  }
			 })
		 });
		 
		//编辑角色
			$(".modify").click(function(){
				var selectContent = $('#mytable').bootstrapTable('getSelections')[0];  
		        if(typeof(selectContent) == 'undefined') {  
		            toastr.warning('请选择一列数据!');  
		            return false;
		        } 
		        	
		        $('#sex').val(selectContent.sex);
		        $('#loginName').val(selectContent.loginName);
		        $('#userId').val(selectContent.userId);
		        $('#loginPassword').val(selectContent.loginPassword);
		        $('#email').val(selectContent.email);
		        $('#phoneNumber').val(selectContent.phoneNumber);
		        $('#userName').val(selectContent.userName);
		        $('#title').text('编辑用户');
		        $('#savebtn').text('更新');
		        $('#themodal').modal('show');     // 项目立项面板  
		        $("#loginName").attr("disabled","disabled");//再改成disabled  
		        
			});	
		
	</script>
	
</body>
</html>