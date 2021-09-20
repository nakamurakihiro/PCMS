<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Employee"%>
<%@ page import="dto.Report"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>

<%
//セッションスコープに保存された社員情報を取得
Employee syain=(Employee)session.getAttribute("employee");
%>

<%
//セッションスコープに保存された該当社員の工数一覧を取得
List<Report> rlist=(List<Report>)session.getAttribute("rlist");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>個人工数記録</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<script type="text/javascript" src="JavaScript/function.js"></script>
</head>
<body>
<!-- ヘッダー -->
<header>
	<h1>PCMS</h1>

	<nav class="header_menu">
		<ul>
			<!-- ログイン中の社員ID、氏名、部署名,の表示 -->
			<li>社員ID:<%=syain.getEmployee_Id()%></li>
			<li>部署名:<%=syain.getDepartment_Name()%></li>
			<li>氏名:<%=syain.getLast_Name()%><%=syain.getFirst_Name()%></li>
			<!-- ヘッダーメニューの表示 -->
			<li><a href="mypage.jsp">マイページ</a></li>
			<li><a href="employee_password_change.jsp">パスワード変更</a></li>
			<li><a href="./Logout" onClick="return Logout_Check()">ログアウト</a></li>
		</ul>
	</nav>
</header>

<header class="sub_header">
	<h3><%=syain.getLast_Name()%><%=syain.getFirst_Name()%>さんの工数記録</h3>
</header>

<!-- 個人の過去に携わった工数記録の表示 -->
<div class="center_screen-4">
	<table class="border_table-2">
		<tr class="border_style gray fixed">
			<th class="fixed">日付</th>
			<th class="fixed">機械名</th>
			<th class="fixed">作業内容</th>
			<th class="fixed">実働時間</th>
			<th class="fixed">残業時間</th>
			<th class="fixed">休日出勤</th>
			<th class="fixed">備考</th>
		</tr>

		<%for(int i=0;i<rlist.size();i++){%>
			<%Report report=(Report)rlist.get(i);%>
				<tr class = "border_style">
					<td><%=report.getDay()%></td>
					<td><%=report.getMachine_Name()%></td>
					<td><%=report.getTask()%></td>
					<td><%=report.getWork_Time()%>h</td>
					<td><%=report.getOver_Time()%>h</td>
					<td><%=report.getHoliday_Work()%>h</td>
					<td><%=report.getComment()%></td>
			<% } %>
		</tr>
	</table>
</div>

<!-- マイページへ戻るボタン -->
<a href="mypage.jsp"><button type="button" class="back_button">戻る</button></a>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>
