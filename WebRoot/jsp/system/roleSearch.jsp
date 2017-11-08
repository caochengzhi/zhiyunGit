<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/init/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
</head>
<body>
	<ol class="breadcrumb">
		 <li class="breadcrumb-item">首页</li>
         <li class="breadcrumb-item">系统管理</li>
         <li class="breadcrumb-item active">角色管理</li>
    </ol>
           
	<div id="toolbar">  
	    <div class="btn-group">  
	        <button  class="add btn btn-primary">  
	            <i class="glyphicon glyphicon-plus"></i>添加角色
	        </button>  
	        <button class="modify btn btn-primary">  
	            <i class="glyphicon glyphicon-cog"></i>编辑角色
	        </button>  
	    </div>  
	</div>
	
	<div class="container">
		<table id="mytable"></table>
	</div>
	
	<!-- 弹出框 -->
	<div class="modal fade" id="themodal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="title"></h4>
				</div>
				<div class="modal-body" id="mydiv">
					<form id="myform" action="roleManager/saveRole" method="post" class="form-horizontal" >
                        <div class="form-group">
                            <label class="col-xs-2 col_style_label">角色名称：</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="roleName" id="roleName" placeholder="请输入角色名称..." />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-2 col_style_label">角色描述：</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="describe" id="describe" placeholder="请输入角色相关描述..." />
                            </div>
                        </div>
                        <input type="hidden" id="roleId" name="roleId">
                        <div class="modal-footer">
                        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>  
							<button id="savebtn" type="submit" class="btn btn-default" >保 存</button>
						</div>
                    </form>
				</div>
			</div>
			
		</div>
	</div>
	
	
	<script type="text/javascript">
	    var $table = $('#mytable');
	    $table.bootstrapTable({
	    	url: 'roleManager/search',        //请求后台的URL（*）
            method: 'post',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性
            sortable: false,                    //是否启用排序
            buttonsAlign: "right",				//按钮对齐方式
            pagination: true,
            toolbar: "#toolbar",				//指定工具栏
            toolbarAlign: "left",				//工具栏对齐方式
            sortOrder: "asc",                   //排序方式
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端
            showColumns: true,                  //是否显示所有的列
            minimumCountColumns: 2,             //最少允许的列数
            singleSelect: true,
            clickToSelect: true,                //是否启用点击选中行
            height: 550,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "roleId",                 //每一行的唯一标识，一般为主键列
            exportDataType: "basic",            //basic', 'all', 'selected'.
            columns: [{
                checkbox: true
            },{
                field: 'roleId',
                title: 'ID',
                visible:false
            }, {
                field: 'roleName',
                title: '角色名称',
            }, {
                field: 'description',
                title: '角色描述'
            }]
	    });
	</script>
	
	<!-- 表单验证 -->
	<script type="text/javascript">
		
		//新增角色
		$(".add").click(function() {
            $("#mydiv input").each(function() {
	            $(this).val('');
	        });
            $('#title').text('添加角色');
            $('#savebtn').text('保存');
			$("#themodal").modal("show");
		});
		
		//编辑角色
		$(".modify").click(function(){
			var selectContent = $('#mytable').bootstrapTable('getSelections')[0];  
	        if(typeof(selectContent) == 'undefined') {  
	            toastr.warning('请选择一列数据!');  
	        }else{  
	            $('#roleName').val(selectContent.roleName);
	            $('#describe').val(selectContent.description);
	            $('#roleId').val(selectContent.roleId);
	            $('#title').text('编辑角色');
	            $('#savebtn').text('更新');
	            $('#themodal').modal('show');     // 项目立项面板  
	        } 
		});	
		
		$(document).ready(function() {
		    $('#myform').bootstrapValidator({
		        container: 'tooltip',
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		            roleName: {
		                validators: {
		                    stringLength: {
		                        min: 2,
		                        message: '角色名不允许少于2个字符!'
		                    },
		                    notEmpty: {
		                        message: '角色名不允许为空!'
		                    }
		                }
		            },
		            describe: {
		                validators: {
		                    stringLength: {
		                        min: 2,
		                        message: '角色描述不允许少于2个字符!'
		                    },
		                    notEmpty: {
		                        message: '角色描述不允许为空!'
		                    }
		                }
		            }
		        }
		    });
		});
		
	</script>
	
</body>
</html>