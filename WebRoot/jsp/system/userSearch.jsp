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
		<div class="row-fluid">
	        <div class="form-group">
		         <label class="col-md-1 col_style_label" >登录名</label>
		         <div class="col-md-4 ">
		           	<input class="form-control" id="_loginName" name="_loginName" type="text" placeholder="请输入登录名..."/>
	             </div>
	             <label class="col-md-1"></label>
   	             <label class="col-md-1 col_style_label">电话号码</label>
		         <div class="col-md-4">
		            <input class="form-control" id="_phoneNumber" name="_phoneNumber" type="text" placeholder="请输入电话号码..."/>
		         </div>
		     </div>
	    </div>   
        
        <div align="right" style="padding-top: 50px;">   
		   <div class="btn-group">  
		       <button class="search btn btn-primary">  
			     <i class="glyphicon glyphicon-search"></i> 查 询
			   </button>
			   <button class="add btn btn-primary">  
			     <i class="glyphicon glyphicon-plus"></i> 新 增
			   </button>  
			   <button class="modify btn btn-primary">  
			     <i class="glyphicon glyphicon-cog"></i> 编 辑
			   </button>
			   <button class="cancel btn btn-primary">  
			     <i class="glyphicon glyphicon-remove"></i> 注 销
			   </button>  
			</div>
		</div>
        
		<legend style="padding-top: 10px;margin-bottom:0px;border: 0;"></legend>    
		
		<table id="mytable"></table>
		
	</div>
	
	<script type="text/javascript">
	    var $table = $('#mytable');
	    $table.bootstrapTable({
            method: 'post',
            contentType: "application/x-www-form-urlencoded",//post方式提交必须要有此参数
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性
            sortable: true,                     //是否启用排序
            pagination: true, 					//是否启用分页
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            queryParams:queryParams,			//请求服务器时所传的参数
            pageNumber: 1,                      //初始化加载第一页，默认第一页
            pageSize: 25,                       //每页的记录行数（*）
            pageList: [25, 50, 100],            //可供选择的每页的行数（*）
            sortOrder: "asc",                   //排序方式
            showColumns: false,                  //是否显示所有的列
            minimumCountColumns: 2,             //最少允许的列数
            singleSelect: true,					//是否单行选择
            clickToSelect: true,                //是否启用点击选中行
            height: 450,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "userId",                 //每一行的唯一标识，一般为主键列
            showToggle: false,                  //是否显示详细视图和列表视图的切换按钮
            detailView: false,                  //是否显示父子表
            showExport: false,                   //是否显示导出
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
            	title:'创建日期',
            	sortable : true
            },{
            	field:'lastUpdateDate',
            	title:'更新日期',
            	sortable : true
            }]
	    });
	   
	  //请求服务数据时所传参数
	  function queryParams(params){
		  return{
			  pageNumber: params.offset,
			  pageSize: params.limit,
			  sortName: params.sort,
			  sortOrder: params.order,
			  loginName:$('#_loginName').val(),
			  phoneNumber:$('#_phoneNumber').val()
		  }
	  }
	  
	  $(".search").click(function(){
		  $table.bootstrapTable('refreshOptions',{pageNumber:1});
		  $table.bootstrapTable('refresh', {url: 'userManager/search'});
	  })
	  
	</script>
	
	<script type="text/javascript">
		 
		 $(".add").click(function() {
			 var frm = document.subfrm;
			 frm.action = "userManager/toModify?userId=";
			 frm.submit(); 
		 });
		 
		//编辑角色
		$(".modify").click(function(){
			var selectContent = $('#mytable').bootstrapTable('getSelections')[0];  
			if(typeof(selectContent) == 'undefined') {  
				toastr.warning('请选择一列数据!');  
				return false;
			}
			var frm = document.subfrm;
			frm.action = "userManager/toModify?userId="+selectContent.userId;
			frm.submit(); 
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
							  return;
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
		
	</script>
	
</body>
</html>