<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Admin"%>
<%@ page import="dto.Department" %>
<%@ page import="dto.Report" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>

<%
//セッションスコープに保存された管理者情報を取得
Admin manager=(Admin)session.getAttribute("admin");
%>

<%
//セッションスコープに保存された部署情報を取得
Department busho=(Department)session.getAttribute("department");
%>

<%
//セッションスコープに保存された工数情報を取得
List<Report> rlist=(List<Report>)session.getAttribute("rlist");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工数記録詳細</title>
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
	<nav class="sub_header_menu">
		<ul>
			<!-- 選択した機械名、部署名、部署ごとの工数合計時間の表示 -->
			<li class="display">機械名:<%=busho.getMachine_Name()%></li>
			<li class="display">部署名:<%=busho.getDepartment_Name()%></li>
			<li class="display">工数合計時間:<%=busho.getDepartment_Hours()%>h</li>
		</ul>
	</nav>
</header>

<!-- 工数記録詳細 -->
<div class="center_screen-4">
	<table class="border_table-2">
		<!-- 部署ごとの作業工数 -->
		<tr class="border_style gray">
			<th class="fixed">日付</th>
			<th class="fixed">作業項目</th>
			<th class="fixed">作業者</th>
			<th class="fixed">実働時間</th>
			<th class="fixed">残業時間</th>
			<th class="fixed">休日出勤</th>
		</tr>

		<%for(int i=0; i<rlist.size(); i++){%>
			<%Report report=(Report)rlist.get(i);%>
				<tr>
					<td><%=report.getDay()%></td>
					<td><%=report.getTask()%></td>
					<td><%=report.getLast_Name()%><%=report.getFirst_Name()%></td>
					<td><%=report.getWork_Time()%></td>
					<td><%=report.getOver_Time()%></td>
					<td><%=report.getHoliday_Work()%></td>
				</tr>
			<% } %>
	</table>
</div>

<!-- 各機械工数合計時間メニューへ戻るボタン -->
<a href="machine_each_time.jsp"><button type="button" class="back_button">戻る</button></a>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>