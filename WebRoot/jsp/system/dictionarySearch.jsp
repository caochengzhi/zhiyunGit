<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/init/include.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典管理</title>
</head>
<body>
	<ol class="breadcrumb">
		 <li class="breadcrumb-item">首页</li>
         <li class="breadcrumb-item">系统管理</li>
         <li class="breadcrumb-item active">字典管理</li>
    </ol>
	<div class="container">
		<div class="row-fluid">
	        <div class="form-group">
		         <label class="col-md-1 col_style_label" >字典名称</label>
		         <div class="col-md-4 ">
		           	<input class="form-control" id="_dictName" name="_dictName" type="text" placeholder="请输入字典名称..."/>
	             </div>
	             <label class="col-md-1"></label>
   	             <label class="col-md-1 col_style_label">字典类型</label>
		         <div class="col-md-4">
		            <input class="form-control" id="_dictType" name="_dictType" type="text" placeholder="请输入字典类型..."/>
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
			</div>
		</div>
		<legend style="padding-top: 10px;margin-bottom:0px;border: 0;"></legend>
		<table id="mytable"></table>
		
		<!-- 弹出框 -->
		<div class="modal fade" id="themodal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="title"></h4>
					</div>
					<div class="modal-body" id="mydiv">
						<form id="myform" action="dictManager/saveSysDictType" method="post" class="form-horizontal" >
	                        <div class="form-group">
	                            <label class="col-xs-2 col_style_label">字典名称：</label>
	                            <div class="col-sm-7">
	                                <input type="text" class="form-control" name="dictName" id="dictName" placeholder="输入字典名称...不可为空" />
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-xs-2 col_style_label">字典类型：</label>
	                            <div class="col-sm-7">
	                                <input type="text" class="form-control" name="dictType" id="dictType" placeholder="输入字典类型...不可为空" />
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-xs-2 col_style_label">备注信息：</label>
	                            <div class="col-sm-7">
	                                <input type="text" class="form-control" name="remarks" id="remarks" placeholder="请输入备注信息..." />
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-xs-2 col_style_label">状态：</label>
	                            <div class="col-sm-7">
	                                <input type="checkbox" id="status" name="status" checked data-on-text="生效" data-off-text="失效">
	                            </div>
	                        </div>
	                        <input type="hidden" id="dictId" name="dictId">
	                        <div class="modal-footer">
								<button type="submit" id="savebtn"  class="btn btn-success" ><i class="glyphicon glyphicon-ok"></i> 保 存</button>
	                        	<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-reply-all"></i>返 回</button>  
							</div>
	                    </form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$("#status").bootstrapSwitch();
		
	    var $table = $('#mytable');
	    var url = "dictManager/searchSysDictTypes";
	    $table.bootstrapTable({
	    	url: url,
            method: 'post',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            contentType: "application/x-www-form-urlencoded",//如果是点击查询按钮请求数据必须加这条
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性
            sortable: false,                    //是否启用排序
            pagination: true,
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            queryParams:queryParams,			//请求服务器时所传的参数
            pageNumber: 1,                      //初始化加载第一页，默认第一页
            pageSize: 25,                       //每页的记录行数（*）
            pageList: [25, 50, 100],            //可供选择的每页的行数（*）
            sortOrder: "asc",                   //排序方式
            minimumCountColumns: 2,             //最少允许的列数
            singleSelect: true,
            clickToSelect: true,                //是否启用点击选中行
            height: 450,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "dictId",                 //每一行的唯一标识，一般为主键列
            exportDataType: "basic",            //basic', 'all', 'selected'.
            columns: [{
                checkbox: true
            },{
                field: 'dictId',
                title: 'ID',
                visible:false
            }, {
                field: 'dictName',
                title: '字典名称',
                formatter: actionFormatter
            }, {
                field: 'dictType',
                editable : true,
                title: '字典类型',
            }, {
                field: 'updateDate',
                title: '更新时间',
            }, {
                field: 'status',
                title: '是否生效'
            }, {
                field: 'remarks',
                title: '备注信息'
            }]
	    });
	    
	    function actionFormatter(value, row, index) {
	    	var url = "dictManager/toDictDatasSearch?dictId="+row.dictId+"&dictName="+row.dictName;
	        return ['<a href="'+url+'">'+value+'</a>' 
	        ].join('');
	    }
	    
	  //请求服务数据时所传参数
	    function queryParams(params){
	        return{
	        	pageNumber: params.offset,
                pageSize: params.limit,
                sortName: params.sort,
                sortOrder: params.order,
                dictName:$('#_dictName').val(),
                dictType:$('#_dictType').val()
	        }
	    }
	  
	    $(".search").click(function(){
	    	$table.bootstrapTable('refreshOptions',{pageNumber:1});
	    	$table.bootstrapTable('refresh', {url: url});
	    })
	    
	    $(".add").click(function() {
			 $("#mydiv input").each(function() {
				 $(this).val('');
			 });
			 $('#title').text('新增字典类型');
			 $('#savebtn').text('保存');
			 $("#themodal").modal("show");
			 $('#dictType').removeAttr("readonly");
			 $('#status').bootstrapSwitch('state',true); 
		 });
	    
	    $(".modify").click(function(){
			var selectContent = $('#mytable').bootstrapTable('getSelections')[0];  
	        if(typeof(selectContent) == 'undefined') {  
	            toastr.warning('请选择一列数据!');  
	            return false;
	        } 
	        
	        $('#dictName').val(selectContent.dictName);
	        $('#dictType').val(selectContent.dictType);
	        $('#remarks').val(selectContent.remarks);
	        $('#dictId').val(selectContent.dictId);
	        $('#title').text('编辑用户');
	        $('#savebtn').text('更新');
	        $('#themodal').modal('show');     // 项目立项面板  
	        $("#dictType").attr("readonly","readonly");//再改成disabled 
	        if(selectContent.status == 'Y')
	        	$('#status').bootstrapSwitch('state',true);  
	        else
	        	$('#status').bootstrapSwitch('state',false);
		});	
	</script>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$('#myform').bootstrapValidator({
	        container: 'tooltip',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	dictName: {
	                validators: {
	                    stringLength: {
	                        min: 2,
	                        message: '字典名称不允许少于2个字符!'
	                    },
	                    notEmpty: {
	                        message: '字典名称不允许为空!'
	                    }
	                }
	            },
	            dictType:{
	            	validators: {
		            	notEmpty: {
	                        message: '字典类型不允许为空!'
	                    }
	            	}
	            }
	        }
	    });
	});
	</script>
</body>
</html>