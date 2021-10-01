<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者ログイン</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<!-- ヘッダー -->
<header>
	<h1>PCMS</h1>
</header>

<div class="center_screen">
		<!-- システム名 -->
		<h2>Product Cost Management System</h2>
		<p>-工数管理システム-</p>

	<!-- 管理者ログイン情報の入力 -->
	<form action="./AdminLogin" method="post">
		<table class="center_table">
			<tr>
				<th>メールアドレス</th>
				<td><input type="text" name="email_address" class="textbox" autocomplete="off" required>@pcms.co.jp</td>
			</tr>

			<tr>
				<th>管理者パスワード</th>
				<td><input type="password" name="admin_password" class="textbox" autocomplete="off" required></td>
			</tr>
		</table>

		<!-- ログインボタン -->
		<button type="submit" class="button blue">ログイン</button>
	</form>

	<!-- 管理者ゲストログイン -->
	<div class="button_fixed-2">
		<p>管理者ゲストログインはこちらから</p>

		<form action="./GuestAdminLogin" method="post">
			<a href="admin_menu.jsp"><button type="submit" class="guest_button white">管理者ゲストログイン</button></a>
		</form>
	</div>

	<div class="button_fixed">
		<p>管理者権限を取得する場合はこちらから</p>

		<!-- 管理者権限取得ボタン -->
		<a href="login_check.jsp"><button type="button" class="button lightgreen">管理者権限取得</button></a>
	</div>
</div>

<!-- ログイン画面へ戻るボタン -->
<a href="login.jsp"><button type="button" class="back_button">戻る</button></a>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>