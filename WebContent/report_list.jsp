<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Admin"%>
<%@ page import="dto.Report"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>

<%
//セッションスコープに保存された社員情報を取得
Admin manager=(Admin)session.getAttribute("admin");
%>

<%
//セッションスコープに保存された全工数一覧を取得
 List<Report> allReport = (List<Report>)session.getAttribute("allReport");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工数記録一覧</title>
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
	<h3>工数記録一覧</h3>
</header>

<!-- 工数記録一覧 -->
<div class="center_screen-4">
	<table class="border_table-2">
		<tr class="border_style gray fixed">
			<th class="fixed">日付</th>
			<th class="fixed">社員番号</th>
			<th class="fixed">氏名</th>
			<th class="fixed">機械名</th>
			<th class="fixed">作業項目</th>
			<th class="fixed">実働時間</th>
			<th class="fixed">残業時間</th>
			<th class="fixed">休日出勤</th>
			<th class="fixed">備考</th>
		</tr>

		<%for(int i=0; i<allReport.size(); i++){%>
			<%Report report=(Report)allReport.get(i);%>
				<tr class="border_style">
					<td><%=report.getDay()%></td>
					<td><%=report.getEmployee_Id()%></td>
					<td><%=report.getLast_Name()%> <%=report.getFirst_Name()%></td>
					<td><%=report.getMachine_Name()%></td>
					<td><%=report.getTask()%></td>
					<td><%=report.getWork_Time()%>h</td>
					<td><%=report.getOver_Time()%>h</td>
					<td><%=report.getHoliday_Work()%>h</td>
					<td><%=report.getComment()%></td>
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