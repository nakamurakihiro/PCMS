<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Employee"%>

<%
//セッションスコープに保存された社員情報を取得
Employee syain = (Employee)session.getAttribute("employee");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>パスワード変更画面</title>
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
<h5>パスワード変更</h5>

<div class="center_screen-3">

	<p class = "input_comment">現在のパスワードと新しいパスワードを入力し、変更ボタンをクリックして下さい。</p>

	<!-- パスワード情報の入力 -->
	<form action="./EmployeePasswordChange" method="post">
			<table class="border_table">
				<tr class="border_style">
					<th>現在のパスワード<span>(必須)</span></th>
					<td>
						<input type="password" name="current_password" required class="longbox">
						<br>
						<span class="password_comment">※現在設定しているパスワードを入力して下さい。</span>
					</td>
				</tr>

				<tr class="border_style">
					<th>新しいパスワード<span>(必須)</span></th>
					<td>
						<input type="password" name="new_password" required class="longbox">
						<br>
						<span class="password_comment">※半角英数字,6桁以上11桁以内で入力して下さい。</span>
					</td>
				</tr>

				<tr class="border_style">
					<th>新しいパスワード(確認用)<span>(必須)</span></th>
					<td>
						<input type="password" name="renew_password" required class="longbox">
						<br>
						<span class="password_comment">※確認のため、新しいパスワードをもう一度入力して下さい。</span>
					</td>
				</tr>
			</table>

		<!-- パスワード変更ボタン -->
		<button type="submit" class="button blue button_fixed" onClick="return Password_Change_Check()">パスワード変更</button>
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