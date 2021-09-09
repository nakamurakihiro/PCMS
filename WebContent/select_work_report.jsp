<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Employee"%>
<%@ page import="dto.Admin"%>
<%@ page import="dto.Report"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>

<%
//セッションスコープに保存された管理者情報を取得
Admin manager = (Admin)session.getAttribute("admin");
%>

<%
//セッションスコープに保存された社員情報を取得
Employee syain=(Employee)session.getAttribute("employee");
%>

<%
//セッションスコープに保存された該当社員の工数一覧を取得
List<Report> rlist = (List<Report>)session.getAttribute("rlist");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員工数記録一覧</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
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

<!-- 選択した社員ID、氏名、部署名,の表示 -->
<header class="sub_header">
	<nav class="sub_header_menu">
		<ul>
			<!-- 選択した機械名と工数合計時間の表示 -->
			<li class="display">社員ID:<%=syain.getEmployee_Id()%></li>
			<li class="display">氏名:<%=syain.getLast_Name()%><%=syain.getFirst_Name()%></li>
			<li class="display">部署名:<%=syain.getDepartment_Name()%></li>
		</ul>
	</nav>
</header>

<!-- 個人の過去に携わった機械、工数合計時間、日付、実働時間、残業時間、休日出勤の表示 -->
<div class="center_screen-4">
	<table class="border_table-2">
		<tr class="border_style gray fixed">
			<th class="fixed">日付</th>
			<th class="fixed">機械名</th>
			<th class="fixed">作業内容</th>
			<th class="fixed">実働時間</th>
			<th class="fixed">残業時間</th>
			<th class="fixed">休日出勤</th>
			<th class="fixed">工数編集</th>
		</tr>

		<%for(int i=0; i<rlist.size();i++){%>
			<%Report report = (Report)rlist.get(i);%>
			<tr>
				<td><%=report.getDay()%></td>
				<td><%=report.getMachine_Name()%></td>
				<td><%=report.getTask()%></td>
				<td><%=report.getWork_Time()%>h</td>
				<td><%=report.getOver_Time()%>h</td>
				<td><%=report.getHoliday_Work()%>h</td>
				<td><form action="./ReportEachSelect?name=<%=syain.getEmployee_Id()%>&name=<%=report.getDay()%>&employee_id=<%=syain.getEmployee_Id()%>&day=<%=report.getDay()%>" method="post">
						<button type ="submit" name="edit_report" class="small_button gray">編集</button>
					</form>
				</td>
			</tr>
		<% } %>
	</table>

	<!-- 社員一覧へ戻るボタン -->
	<a href="employee_list.jsp"><button type="button" class="back_button">戻る</button></a>
</div>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>
