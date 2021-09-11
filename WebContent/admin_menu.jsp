<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Admin"%>

<%
//セッションスコープに保存された管理者情報を取得
Admin manager = (Admin)session.getAttribute("admin");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者画面</title>
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
			<li><a href="./Logout">ログアウト</a></li>
		</ul>
	</nav>
</header>

<div class="admin_button">
	<!-- 管理者権限削除 -->
	<form action="./AdminDelete" method="post">
		<button type="submit" class="small_button red" onClick="return Delete_Check()">管理者権限削除</button>
	</form>
</div>

<!-- 画面名称 -->
<h5>管理者メニュー</h5>

<div class="side_screen">
	<!-- 工数一覧表示 -->
	<form action="./AllReportListDisplay" method="post">
		<button type="submit" class="menu_button gray">工数一覧</button>
	</form>

	<!-- 社員一覧表示 -->
	<form action="./EmployeeListDisplay" method="post">
		<button type="submit" class="menu_button gray">社員一覧</button>
	</form>

	<!-- 機械毎の工数一覧表示 -->
	<form action="./MachineListDisplay" method="post">
		<button type="submit" name="machine_hours" class="menu_button gray">各機械工数</button>
	</form>
</div>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>