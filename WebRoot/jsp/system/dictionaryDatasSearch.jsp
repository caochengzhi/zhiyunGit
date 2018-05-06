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
         <li class="breadcrumb-item active">字典数据</li>
    </ol>
	<div class="container">
		
		<div class="modal-header">
			<h4 class="modal-title" id="title"><i class="fa fa-codepen"></i> 字典数据(${dictName })</h4>
		</div>
		
		<div id="toolbar">
			<button id="insertbtn" class="btn btn-default"><i class="glyphicon glyphicon-plus"></i> 插入行</button>
		</div>
		<table id="mytable"></table>
		<input type="hidden" name="dictId" value="${dictId}">
		
		<div class="modal-footer">
			<button id="savebtn" type="submit" class="btn btn-success" ><i class="glyphicon glyphicon-ok"></i> 保 存</button>
			<button type="button" class="btn btn-default" onclick="javascript:history.back(-1);"><i class="fa fa-reply-all"></i> 返 回</button>  
		</div>
	</div>
	
	<script type="text/javascript">
	    var $table = $('#mytable');
	    $table.bootstrapTable({
	    	url: 'dictManager/searchSysDictDatas?dictId=${dictId}',
            method: 'post',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性
            sortable: false,                    //是否启用排序
            toolbar: "#toolbar",				//指定工具栏
            toolbarAlign: "left",				//工具栏对齐方式
            sortOrder: "asc",                   //排序方式
            minimumCountColumns: 2,             //最少允许的列数
            singleSelect: true,
            height: 450,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                 //每一行的唯一标识，一般为主键列
            exportDataType: "basic",            //basic', 'all', 'selected'.
            columns: [{
                field: 'number', 
                formatter: function (value, row, index) {
                	var num = index+1;
                	var i = ['<div style="margin-top:6px;" >'+num+'</div>'];
					return i.join('');
                }
            },{
                field: 'id',
                title: 'ID',
                visible:false,
                formatter: function operateFormatter(value, row, index) {
					var i = ['<div><input type="hidden"  name="id" value="' + row.id + '"></div>'];
					return i.join('');
				}
            },{
                field: 'dictDataCode',
                title: '字典键值',
                formatter: function operateFormatter(value, row, index) {
					var i = ['<div><input class="form-control" type="text"  name="dictDataCode" value="' + row.dictDataCode + 
					         '" onchange=\'reloadRowData($(this),'+index+')\'/></div>'];
					return i.join('');
				}
            }, {
                field: 'dictDataName',
                title: '字典标签',
                formatter: function operateFormatter(value, row, index) {
					var i = ['<div><input class="form-control" type="text"  name="dictDataName" value="' + row.dictDataName + 
					         '" onchange=\'reloadRowData($(this),'+index+')\'/></div>'];
					return i.join('');
				}
            },{
                field: 'updateDate',
                title: '更新时间',
                formatter: function operateFormatter(value, row, index) {
					var i = ['<div><input class="form-control" type="text" readonly name="updateDate" value="' + row.updateDate + '"></div>'];
					return i.join('');
				}
            }, {
                field: 'status',
                title: '是否生效',
                formatter: function operateFormatter(value, row, index) {
					var i=	['<select class="form-control" onchange=\'reloadRowData($(this),'+index+')\' id="status" name="status" > '+
					'<option value="Y" >Y</option> <option value="N" '+ (row.status=='N'?"selected":"") +'>N</option> </select>']
					return i.join('');
				}
            }, {
                field: 'remarks',
                title: '备注信息',
                formatter: function operateFormatter(value, row, index) {
					var i = ['<div><input class="form-control" type="text" name="remarks" value="' + row.remarks +
					         '" onchange=\'reloadRowData($(this),'+index+')\'/></div>'];
					return i.join('');
				}
            },{
            	field: 'operator',
                title: '操作',
                formatter: function operateFormatter(value, row, index) {
					var i = ['<div style="margin-top:3px;" ><button onclick="removeRow('+row.number+');" title="删除当前行" class="btn btn-default"><i class="fa fa-trash-o"></i></button></div>'];
					return i.join(''); 
				}
            }]
	    });
	    
	 	// 改变 input 编辑框值时，更新 data 数据，便于新增行时原来填写好的数据不会被新增行强制覆盖
		function reloadRowData(obj,index){
			var v = obj.val();
			var n = obj.attr("name");
			$table.bootstrapTable('getOptions').data[index][n] = v;
		}
	    
	    $insertbtn = $('#insertbtn');
		$savebtn = $('#savebtn');
	
		$insertbtn.click(function() {
			var index = $(this).data('index');
			if (!index) {
                index = 1;
                $(this).data('index', 1);
            }
            index++;
            $(this).data('index', index);
            
			$table.bootstrapTable('insertRow', {
				index: 0,
				row: {
					number: index,
					dictDataCode: '',
					dictDataName: '',
					updateDate: new Date().Format("yyyy-MM-dd hh:mm:ss"),
					status:'',
					remarks:''
				}
			});
		});
		
		$savebtn.click(function() {
			var data = $table.bootstrapTable('getData');
			 $.ajax({
				type : 'post',
				url : "dictManager/saveSysDictDatas?dictId=${dictId}",
				contentType : 'application/json;charset=utf-8', //设置请求头信息
				cache : false,
				dataType : "json",
				data : JSON.stringify(data),
				success: function (data, status) {
					toastr.success(data);
				},
				error: function (data, status) {
					alert(status);
					toastr.error("操作失败!!!");
				}
			}); 
		});
		
		//删除指定行
		function removeRow(index){
			if(typeof(index) == 'undefined'){
				toastr.warning('已保存数据不允许删除，请做失效处理!');
			}else{
	            $table.bootstrapTable('remove', {
	                field: 'number',
	                values: [index] //删除的时候，values必须是数组形式，不然不起作用，请注意
	            });
			}
	    }
			
	</script>
</body>
</html>