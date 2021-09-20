<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Employee"%>
<%@ page import="dto.Work"%>
<%@ page import="dto.Machine"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>

<%
//セッションスコープに保存された社員情報を取得
Employee syain = (Employee)session.getAttribute("employee");
%>

<%
//セッションスコープに保存された作業情報を取得
List<Work> wlist = (List<Work>)session.getAttribute("wlist");
%>

<%
//セッションスコープに保存された機械情報を取得
List<Machine> mlist = (List<Machine>)session.getAttribute("mlist");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工数記録画面</title>
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

<!-- 画面名称 -->
<h5>工数記録</h5>

<div class="center_screen-6">
	<!-- 工数情報の入力 -->
	<form action="./DepartmentAdd" method="post">
		<table class="border_table-3">
			<tr class="border_style">
				<th>日付</th>
				<td><input type="date" name="day" autocomplete="off" required></td>
			</tr>

			<tr class="border_style">
				<th>機械名</th>
				<td>
					<select name="machine_name" class="selectbox">
						<option>選択して下さい</option>
							<%for(int i = 0; i < mlist.size(); i++){%>
								<%Machine machine = (Machine)mlist.get(i);%>
									<option><%=machine.getMachine_Name()%></option>
							<% } %>
					</select>
				</td>

				<th></th>
				<th><button type="submit" name="edit_machine" class="small_button gray" form="form2">編集</button></th>
			</tr>

			<tr class="border_style">
				<th>作業項目</th>
				<td>
					<select name="task" class="selectbox">
						<option>選択して下さい</option>
							<%for(int i = 0; i < wlist.size(); i++){%>
								<%Work work = (Work)wlist.get(i);%>
								<option><%=work.getTask()%></option>
							<% } %>
					</select>
				</td>

				<th></th>
				<th><button type="submit" name="edit_work" class="small_button gray" form="form2">編集</button></th>
			</tr>

			<tr class="border_style">
				<th>実働時間</th>
				<td><input type="text" name="work_time" autocomplete="off" class="numberbox" required>h</td>

				<th>残業時間</th>
				<td><input type="text" name="over_time" autocomplete="off" class="numberbox" required>h</td>

				<th>休日出勤</th>
				<td><input type="text" name="holiday_work" autocomplete="off" class="numberbox" required>h</td>
			</tr>

			<tr class="border_style">
				<th>備考</th>
				<td><textarea name="comment" placeholder="問題点や進捗状況など記入して下さい。" class="comment"></textarea></td>
			</tr>
		</table>

		<!-- 保存ボタン -->
		<button type="submit" class="button blue button_fixed" onClick="return Save_Check()">保存</button>
	</form>
</div>

<!-- 機械編集 -->
<form action="./MachineListDisplay" method="post" id="form1"></form>

<!-- 作業項目編集 -->
<form action="./WorkSelect" method="post" id="form2"></form>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>