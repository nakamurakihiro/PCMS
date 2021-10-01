<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<!-- ヘッダー -->
<header>
	<h1>PCMS</h1>
</header>

<div class="admin_button">
	<!-- 管理者ログイン -->
	<a href="admin_login.jsp">管理者としてログイン</a>
</div>

<div class="center_screen">
		<!-- システム名 -->
		<h2>Product Cost Management System</h2>
		<p>-工数管理システム-</p>

	<!-- ログイン画面-->
	<!-- ログイン情報の入力 -->
	<form action="./EmployeeLogin"  method="post">
		<table class="center_table">
			<tr>
				<th>メールアドレス</th>
				<td><input type="text" name="email_address" class="textbox" autocomplete="off" required>@pcms.co.jp</td>
			</tr>

			<tr>
				<th>パスワード</th>
				<td><input type="password" name="employee_password" class="textbox" autocomplete="off" required></td>
			</tr>
		</table>

		<!-- ログインボタン -->
		<button type="submit" class="button blue">ログイン</button>
	</form>

	<!-- ゲストログイン -->
	<div class="button_fixed-2">
		<p>ゲストログインはこちらから</p>

		<form action="./GuestEmployeeLogin" method="post">
			<button type="submit" class="guest_button white">ゲストログイン</button>
		</form>
	</div>

	<!-- 新規社員登録 -->
	<div class="button_fixed">
		<p>はじめての方はこちらから</p>

		<form action="./DepartmentListDisplay" method="post">
			<button type="submit" name="new_regist" class="button lightgreen">新規社員登録</button>
		</form>
	</div>
</div>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>