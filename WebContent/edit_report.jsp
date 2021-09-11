<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Admin"%>
<%@ page import="dto.Employee"%>
<%@ page import="dto.Report"%>
<%@ page import="dto.Machine"%>
<%@ page import="dto.Work"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>

<%
//セッションスコープに保存された管理者情報を取得
Admin manager = (Admin)session.getAttribute("admin");
%>

<%
//セッションスコープに保存された社員情報を取得
Employee syain = (Employee)session.getAttribute("employee");
%>

<%
//セッションスコープに保存された該当社員の工数一覧を取得
Report nippo = (Report)session.getAttribute("report");
%>

<%
//セッションスコープに保存された作業内容を取得
List<Work> wlist = (List<Work>)session.getAttribute("wlist");
%>

<%
//セッションスコープに保存された機械名を取得
List<Machine> mlist = (List<Machine>)session.getAttribute("mlist");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工数記録編集</title>
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

<header class="sub_header">
	<h3>工数記録編集</h3>
</header>

<!-- 選択した社員の工数記録を表示 -->
<div class="center_screen-4">
	<form action="./ReportUpd" method="post">
		<table class="border_table-2">
			<tr class="border_style fixed gray">
				<th class="fixed">社員ID</th>
				<th class="fixed">氏名</th>
				<th class="fixed">日付</th>
				<th class="fixed">機械名</th>
				<th class="fixed">作業内容</th>
				<th class="fixed">実働時間</th>
				<th class="fixed">残業時間</th>
				<th class="fixed">休日出勤</th>
			</tr>

			<tr class="border_style">
				<td><%=nippo.getEmployee_Id()%><input type="hidden" name="employee_id" value="<%=nippo.getEmployee_Id()%>"></td>
				<td><%=nippo.getLast_Name()%><%=nippo.getFirst_Name()%></td>
				<td><%=nippo.getDay()%><input type="hidden" name="day" value="<%=nippo.getDay()%>"></td>
				<td>
					<select name="machine_name" class="selectbox">
						<%for(int i=0; i < mlist.size(); i++){%>
							<%Machine machine=(Machine)mlist.get(i);%>
								<option><%=machine.getMachine_Name()%></option>
						<% } %>
					</select>
				</td>
				<td>
					<select name="task" class="selectbox">
						<%for(int i=0; i < wlist.size(); i++){%>
							<%Work work=(Work)wlist.get(i);%>
								<option><%=work.getTask()%></option>
						<% } %>
					</select>
				</td>
				<td><input type="text" name="work_time" value="<%=nippo.getWork_Time()%>" autocomplete="off" class="numberbox">h</td>
				<td><input type="text" name="over_time" value="<%=nippo.getOver_Time()%>" autocomplete="off" class="numberbox">h</td>
				<td><input type="text" name="holiday_work" value="<%=nippo.getHoliday_Work()%>" autocomplete="off" class="numberbox">h</td>
			</tr>
		</table>

		<!-- 工数記録更新ボタン -->
		<button type="submit" class="button blue">更新する</button>
	</form>

	<br>

	<!-- 工数記録削除ボタン -->
	<form action="./ReportDelete?name=<%=nippo.getEmployee_Id()%>&name=<%=nippo.getDay()%>&employee_id=<%=nippo.getEmployee_Id()%>&day=<%=nippo.getDay()%>" method="post">
		<button type="submit" class="button red" onClick="return Delete_Check()">削除する</button>
	</form>
</div>

<!-- 個人工数記録画面へ戻るボタン -->
<a href="select_work_load.jsp"><button type="button" class="back_button">戻る</button></a>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>