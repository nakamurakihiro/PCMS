<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Admin"%>
<%@ page import="dto.Machine" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>

<%
//セッションスコープに保存された管理者情報を取得
Admin manager = (Admin)session.getAttribute("admin");
%>

<%
//セッションスコープに保存された機械一覧を取得
 List<Machine> mlist = (List<Machine>)session.getAttribute("mlist");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>機械一覧</title>
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

<header class="sub_header">
	<h3>機械一覧</h3>
</header>

<!-- 機械一覧 -->
<div class="center_screen-4">
	<table class="border_table-2">
		<tr class = "border_style gray fixed">
			<th>機械名</th>
			<th>機械番号</th>
		</tr>

		<%for(int i=0; i<mlist.size(); i++){%>
			<%Machine machine=(Machine)mlist.get(i);%>
				<tr class="border_style">
					<td><%=machine.getMachine_Name()%></td>
					<td>
						<a href="./MachineFind?name=<%=machine.getMachine_Number()%>&machine_number=<%=machine.getMachine_Number()%>"><%=machine.getMachine_Number()%></a>
					</td>
				</tr>
		<% } %>
	</table>
</div>

<!-- 管理者メニューへ戻るボタン -->
<a href="admin_menu.jsp"><button type="button" class="back_button">戻る</button></a>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>