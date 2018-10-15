<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}
	
	function doView(){
		alert("查看...");
	}
	
	function doDelete(){
	    //把你选中的数据查出来
         var selectRows = $('#grid').datagrid("getSelections");
         //alert(selectRows.length);
		 if(selectRows.length==0){
		     //没有选中客户,给出提示信息
			 $.messager.alert("提示信息","请选择要删除的用户","warning");
		 } else{
		     //选中客户
			 $.messager.confirm("确认删除","你确定要删除选择的客户吗?",function (r) {
				 //alert("确认删除");
				// alert(r);
				 if(r){
                    var array = new Array();
                    for(var i=0;i<selectRows.length;i++){
                        var id = selectRows[i].id;
                        array.push(id);
					}
					var ids=array.join(",");
					 //alert(ids);
					 location.href="${ pageContext.request.contextPath }/StaffAction_deleteBatch?ids="+ids;
				 }
             });
		 }

	}
	
	function doRestore(){
        alert("将取派员还原...");
    }
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	},
        <shiro:hasPermission name="staff-delete">
        {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},
        </shiro:hasPermission>
        {
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];

	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	},{
		field : 'deltag',
		title : '是否删除',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已删除";
			}
		}
	},
        {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			//iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [10,20,30],
			pagination : true,
			toolbar : toolbar,
			url : "${ pageContext.request.contextPath }/StaffAction_QueryPage",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});

		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });

        // 修改取派员窗口
        $('#editStaffWindow').window({
            title: '修改取派员',
            width: 400,
            modal: true,   //定义如果窗口是一个模式窗口。
            shadow: false,  //如果设置为true,当窗口显示阴影将显示。
            closed: true,  //初始是否关闭
            height: 400,
            resizable:false   //定义是否窗口可以调整大小。
        });
		
	});

	function doDblClickRow(rowIndex, rowData){
		//alert("双击表格数据...");
		$("#editStaffWindow").window("open");
        $("#editStaffWindow").form("load",rowData);
        //alert(rowData.id);
	}

    $(function () {

        $("#save").click(function () {
            var v = $("#saveStaffForm").form("validate");
            //如果校验成功,表单提交
            if(v){
                $("#saveStaffForm").submit();
            }
        });

        $.extend($.fn.validatebox.defaults.rules, {
            telephone: {
                validator: function(value,param){
                    return /^1[3|5|7|8][0-9]{9}$/.test(value);
                },
                message: '手机号输入有误!'
            }
        });
    });
</script>

</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="saveStaffForm" action="${ pageContext.request.contextPath }/StaffAction_saveStaff" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<%--<tr>--%>
						<%--<td>取派员编号</td>--%>
						<%--<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>--%>
					<%--</tr>--%>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td>
							<input type="text" name="telephone" class="easyui-validatebox"  data-options="validType:'telephone'" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>


	<div class="easyui-window" title="对收派员进行修改" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >修改</a>
			</div>
		</div>

		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editStaffForm" action="${ pageContext.request.contextPath }/StaffAction_editStaff" method="post">
				<input type="hidden" name="id">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<%--<tr>--%>
					<%--<td>取派员编号</td>--%>
					<%--<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>--%>
					<%--</tr>--%>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td>
							<script>
                                $(function () {

                                    $("#edit").click(function () {
                                        var v = $("#editStaffForm").form("validate");
                                        //如果校验成功,表单提交
                                        if(v){
                                            $("#editStaffForm").submit();
                                        }
                                    });

                                    $.extend($.fn.validatebox.defaults.rules, {
                                        telephone: {
                                            validator: function(value,param){
                                                return /^1[3|5|7|8][0-9]{9}$/.test(value);
                                            },
                                            message: '手机号输入有误!'
                                        }
                                    });
                                });
							</script>
							<input type="text" name="telephone" class="easyui-validatebox"  data-options="validType:'telephone'" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="checkbox" name="haspda" value="1" />
							是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>	