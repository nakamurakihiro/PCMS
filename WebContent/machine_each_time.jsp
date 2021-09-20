<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Admin"%>
<%@ page import="dto.Department" %>
<%@ page import="dto.Machine" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>

<%
//セッションスコープに保存された管理者情報を取得
Admin manager = (Admin)session.getAttribute("admin");
%>

<%
//セッションスコープに保存された部署情報一覧を取得
List<Department> dlist = (List<Department>)session.getAttribute("dlist");
%>

<%
//セッションスコープに保存された機械情報を取得
Machine kikai = (Machine)session.getAttribute("machine");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>各機械工数合計時間</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<script type="text/javascript" src="JavaScript/function.js"></script>
</head>
<body>
<!-- ヘッダー -->
<header>
	<h1>PCMS</h1>

	<nav class="header_menu">
		<ul>
			<!-- ログイン中の管理者氏名の表示 -->
			<li>管理者:<%=manager.getLast_Name()%><%=manager.getFirst_Name()%></li>
			<!-- ヘッダーメニューの表示 -->
			<li><a href="admin_password_change.jsp">管理者パスワード変更</a></li>
			<li><a href="./Logout" onClick="return Logout_Check()">ログアウト</a></li>
		</ul>
	</nav>
</header>

<!-- 選択した機械名と工数合計時間の表示 -->
<header class="sub_header">
	<nav class="sub_header_menu">
		<ul>
			<li class="display">機械名:<%=kikai.getMachine_Name()%></li>
			<li class="display">工数合計時間:<%=kikai.getMachine_Hours()%>h</li>
		</ul>
	</nav>
</header>

<!-- 部署毎の工数合計時間の表示 -->
<div class="center_screen-4">
	<table class="border_table-2">
		<tr class = "border_style gray fixed">
			<th class="fixed">部署名</th>
			<th class="fixed">工数時間</th>
		</tr>

		<%for(int i=0; i<dlist.size(); i++){%>
			<%Department department = (Department)dlist.get(i);%>
			<tr class = "border_style">
				<td><%=department.getDepartment_Name()%></td>
				<td><a href="./DepartmentSelect?name=<%=department.getDepartment_Name()%>&name=<%=department.getMachine_Name()%>&department_name=<%=department.getDepartment_Name()%>&machine_name=<%=department.getMachine_Name()%>">(内訳を参照)</a></td>
			</tr>
		<% } %>
	</table>
</div>

<!-- 機械一覧メニューへ戻るボタン -->
<a href="machine_list.jsp"><button type="button" class="back_button">戻る</button></a>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>