<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Admin"%>

<%
//セッションスコープに保存された社員情報を取得
Admin manager=(Admin)session.getAttribute("admin");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工数記録削除</title>
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
	</nav>
</header>

<div class="center_screen">
	<p>選択した工数記録を削除しました。</p>

	<!-- 管理者メニューへ戻るボタン -->
	<a href="admin_menu.jsp"><button type="button" class="button blue">管理者メニューへ</button></a>
</div>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>