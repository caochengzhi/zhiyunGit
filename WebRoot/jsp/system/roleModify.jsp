<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/init/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色编辑</title>
</head>
<body>
	<ol class="breadcrumb">
		 <li class="breadcrumb-item">首页</li>
         <li class="breadcrumb-item">系统管理</li>
         <li class="breadcrumb-item active">角色编辑</li>
    </ol>
    
    <div class="container">
    	<div class="modal-header">
			<h4 class="modal-title" id="title">角色编辑</h4>
		</div>
	    <div class="panel-body">
	        <ul class="nav nav-tabs">
	        	<c:if test="${type != 'update' }">
		        	<li class="active"><a href="#home" data-toggle="tab">角色设置</a></li>
		        </c:if>
		        <c:if test="${type != 'modify' }">
		        	<c:choose>
		        		<c:when test="${type == 'update' }">
		        			<li class="active">
		        		</c:when>
		        		<c:otherwise>
		        			<li>
		        		</c:otherwise>
		        	</c:choose>
			          	<a href="#profile" data-toggle="tab">权限菜单</a>
			          </li>
		        </c:if>
	        </ul>

	        <!-- Tab panes -->
	        <div class="tab-content">
	        	<c:if test="${type != 'update'}">
		            <div class="tab-pane fade in active" id="home">
		                <div class="modal-body" id="mydiv">
							<form id="myform" name="myform" action="roleManager/saveRole" method="post" class="form-horizontal" >
							      <div class="form-group">
							           <label class="col-xs-2 col_style_label">角色名称：</label>
							           <div class="col-sm-6">
							               <input type="text" class="form-control" name="roleName" id="roleName" value="${role.roleName }" placeholder="请输入角色名称，必填..." />
							           </div>
							      </div>
							      <div class="form-group">
							           <label class="col-xs-2 col_style_label">角色编码：</label>
							           <div class="col-sm-6">
							               <input type="text" class="form-control" name="roleCode" id="roleCode" value="${role.roleCode }" placeholder="请输入角色编码，必填..." />
							           </div>
							      </div>
							      <div class="form-group">
							           <label class="col-xs-2 col_style_label">角色类型：</label>
							           <div class="col-sm-6">
								           <select class="form-control" id="roleTypeselect" name="roleType">
										   </select>
							       	   </div>
							      </div>
							      <div class="form-group">
							           <label class="col-xs-2 col_style_label">备注信息：</label>
							           <div class="col-sm-6">
							               <textarea class="form-control" name="description" id="description"  placeholder="请输入角色相关描述..." >${role.description }</textarea>
							           </div>
							      </div>
							      <div class="modal-footer">
									   <button id="savebtn" type="submit" class="btn btn-success" ><i class="glyphicon glyphicon-ok"></i>保 存</button>
							           <button type="button" class="btn btn-default" onclick="javascript:history.back(-1);"><i class="fa fa-reply-all"></i>返 回</button>  
								  </div>
								  <input type="hidden" id="roleId" name="roleId" value="${role.roleId }">
								  <input type="hidden" id="resourceCodes" name="resourceCodes" value="${role.resourceCodes }">
								  <input type="hidden" id="operatorType" name="operatorType" value="${type}">
							  </form>
						</div>
		           </div>
            	</c:if>
            	
             	<c:if test="${type != 'modify'}">
             		<c:choose>
             			<c:when test="${type == 'update' }">
             				<div class="tab-pane fade in active" id="profile">
             			</c:when>
             			<c:otherwise>
             				<div class="tab-pane fade" id="profile">
             			</c:otherwise>
             		</c:choose>
	             <div class="tab-pane fade in active" id="profile">
	                <div class="pre-scrollable">
						<ul id="tree" class="ztree"></ul>
					</div>
					<c:if test="${type == 'update' }">
						<div class="modal-footer">
							<form id="myform" name="myform" action="roleManager/saveRole" method="post" class="form-horizontal" >
								<button id="savebtn" type="submit" class="btn btn-success" ><i class="glyphicon glyphicon-ok"></i>保 存</button>
								<button type="button" class="btn btn-default" onclick="javascript:history.back(-1);"><i class="fa fa-reply-all"></i>返 回</button>  
								<input type="hidden" id="roleId" name="roleId" value="${role.roleId }">
								<input type="hidden" id="resourceCodes" name="resourceCodes" value="${role.resourceCodes }">
								<input type="hidden" id="operatorType" name="operatorType" value="${type}">
							</form>
						</div>
					</c:if>
					
					<script type="text/javascript">
							var setting = {
								check: {
									enable: true
								},
								callback: {
									onCheck: onCheck
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
								
								function onCheck() {
									var zTree = $.fn.zTree.getZTreeObj("tree");
											var nodes = zTree.getCheckedNodes(true);
											var  resources= "";
											for(var i=0;i<nodes.length;i++){  
												resources +=nodes[i].code+ ",";
											} 
											$("#resourceCodes").val(resources);
										}
								$.fn.zTree.init($("#tree"), setting);
							</script>
	                   </div>
                    </c:if>
               </div>
         </div>
    </div>
</body>
<script type="text/javascript">

	$(document).ready(function() {
		
		 $('#roleTypeselect').select2({
			 data: getAjaxJson('dictType=roleType','dictManager/getDictSelect'),
		     placeholder:'请选择...',//默认文字提示
		     language: "zh-CN",//汉化
		     //multiple:true,//是否多选
		     allowClear: true//允许清空
		}); 
		$("#roleTypeselect").select2('val','1');
		
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
	            roleCode:{
	            	validators: {
		            	notEmpty: {
	                        message: '角色描述不允许为空!'
	                    }
	            	}
	            },
	            description: {
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
</html>