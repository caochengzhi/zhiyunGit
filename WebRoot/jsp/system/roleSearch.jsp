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
	        <button  class="add btn btn-primary" onclick="onPermission('add')">
	            <i class="glyphicon glyphicon-plus"></i>添加角色
	        </button>  
	    </div>  
	</div>
	
	<div class="container">
		<table id="mytable"></table>
	</div>
	
	<!-- 弹出窗口 -->
	<div class="modal fade" id="themodal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="title">查看权限</h4>
				</div>
				<div class="modal-body" id="mydiv">
					<div class="pre-scrollable">
						<ul id="tree" class="ztree"></ul>
					</div>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>  
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
            height: 600,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "roleId",                 //每一行的唯一标识，一般为主键列
            exportDataType: "basic",            //basic', 'all', 'selected'.
            columns: [{
                checkbox: true
            }/* ,{
                field: 'Number',  
                align:'center',
                formatter: function (value, row, index) {  
                	return index+1;  
                }
            } */,{
                field: 'roleId',
                title: 'ID',
                visible:false
            }, {
                field: 'roleName',
                title: '角色名称'
            }, {
                field: 'roleCode',
                title: '角色编码',
            }, {
                field: 'roleType',
                title: '角色类型',
            }, {
                field: 'description',
                title: '备注信息'
            }, {
                field: 'operator',
                title: '操作',
                formatter: actionFormatter
            }]
	    });
	    
	    //value: 所在collumn的当前显示值，
	    //row:整个行的数据 ，对象化，可通过.获取
	     //表格-操作 - 格式化 
	    function actionFormatter(value, row, index) {
	        return ['<a class="btn" href="javascript:showPermission()" title="查看角色"><i class="glyphicon glyphicon-search"></i></a>' 
	        ,'<a class="modify btn" href="javascript:onPermission('+"'modify'"+')" title="编辑角色"><i class="glyphicon glyphicon-pencil"></i></a>'
	        ,'<div class="btn-group">'
	        ,'	<a title="数据操作" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
	        ,'  	<i class="glyphicon glyphicon-circle-arrow-right"></i>'
	        ,'	</a>'
	        ,'	<ul class="dropdown-menu">'
	        ,'  	<li><a href="javascript:setUsers('+index+')">分配用户</a></li>'
	        ,'  	<li><a href="javascript:setPermission('+index+')">权限菜单</a></li>'
	        ,'	</ul>'
	        ,'</div>'].join('');
	    }
	    
	</script>
	
	<script type="text/javascript">
		function onPermission(type){
			var frm = document.subfrm;
			if(type == "add"){
				frm.action = "roleManager/toModify/"+type+"?roleId=";
				frm.submit(); 
			}else{
				var selectContent = getTableRow('mytable');
				if(selectContent != null){
					var roleId = selectContent.roleId;
					frm.action = "roleManager/toModify/"+type+"?roleId="+roleId;
					frm.submit(); 
				}
			}
		}
		
		 function setPermission(index){
		    	checkTable('mytable',index);
		    	onPermission('update');
		 }
		 
		 function setUsers(index){
		    	checkTable('mytable',index);
		    	alert('设置用户');
		 }
		
		 function showPermission(){
			 $("#themodal").modal("show");
			 	var setting = {
						check: {
							enable: true
						},
						data: {
							simpleData: {
								enable: true
							}
						},
						async: {
							enable: true,
							url:"roleManager/getRoleAclTree",
							otherParam:{"roleId":"${roleId}"},
								type: "post",
								dataType:"text"
							}
						};
			 
			 	$.fn.zTree.init($("#tree"), setting);
		 }
	</script>
</body>
</html>