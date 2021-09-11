<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Employee"%>
<%@ page import="dto.Machine"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>

<%
//セッションスコープに保存された社員情報を取得
Employee syain = (Employee)session.getAttribute("employee");
%>

<%
//セッションスコープに保存された機械情報を取得
List<Machine> mlist = (List<Machine>)session.getAttribute("mlist");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>機械編集</title>
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
			<li><a href="./Logout">ログアウト</a></li>
		</ul>
	</nav>
</header>

<!-- 画面名称 -->
<h5>機械項目編集</h5>

<!-- 選択項目の編集 -->
<div class="center_screen-2">
	<!-- 機械情報の入力 -->
	<p>追加する機械名と機械番号を記入して下さい。</p>
	<form action="./MachineAdd" method="post">
		<table class="center_table">
			<tr>
				<td>　機械名:<input type="text" name="machine_name" autocomplete="off" required class="textbox"></td>
			</tr>

			<tr>
				<td>機械番号:<input type="text" name="machine_number" autocomplete="off" required class="textbox"></td>
			</tr>
		</table>

		<!-- 追加登録ボタン -->
		<button type="submit" class="button lightgreen">追加登録する</button>
	</form>

	<br>
	<hr>
	<br>

	<!-- 機械削除 -->
	<p>削除する機械を選択して下さい。</p>
	<form action="./MachineDelete"  method="post">
		<table class="center_table">
			<tr>
				<td>機械名:
					<select name="machine_name" class="selectbox">
						<option>選択して下さい</option>
							<%for(int i = 0; i < mlist.size(); i++){%>
								<%Machine machine  = (Machine)mlist.get(i);%>
								<option><%=machine.getMachine_Name()%></option>
						<% } %>
					</select>
				</td>
			</tr>
		</table>

		<!-- 削除ボタン -->
		<button type="submit" class="button red" onClick="return Delete_Check()">削除する</button>
	</form>
</div>

<!-- 工数記録画面へ戻るボタン -->
<a href="work_report.jsp"><button type="button" class="back_button">戻る</button></a>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>