<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Employee"%>

<%
//セッションスコープに保存された社員情報を取得
Employee syain=(Employee)session.getAttribute("employee");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者権限取得画面</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<!-- ヘッダー -->
<header>
	<h1>PCMS</h1>
</header>

<!-- 画面名称 -->
<h5>管理者権限取得</h5>

<!-- 登録内容の入力 -->
<div class="center_screen-3">
	<p>共通の管理者パスワードを入力して下さい。</p>
		<form action="./AdminRegister" method="post">
			<table class="border_table">
				<tr class="border_style">
					<th>社員ID</th>
					<td><input type="hidden" name="employee_id" value="<%=syain.getEmployee_Id()%>"><%=syain.getEmployee_Id()%></td>
				</tr>

				<tr class="border_style">
					<th>メールアドレス</th>
					<td><input type="hidden" name="email_address" value="<%=syain.getEmail_Address()%>"><%=syain.getEmail_Address()%>@pcms.co.jp</td>
				</tr>

				<tr class="border_style">
					<th>氏名</th>
					<td><input type="hidden" name="last_name" value="<%=syain.getLast_Name()%>">
						<input type="hidden" name="first_name" value="<%=syain.getFirst_Name()%>">
						<%=syain.getLast_Name()%><%=syain.getFirst_Name()%>
					</td>
				</tr>

				<tr class="border_style">
					<th>管理者パスワード<span>(必須)</span></th>
					<td><input type="password" name="input_admin_password" class="textbox" autocomplete="off" required></td>
				</tr>
			</table>

		<!-- 管理者権限取得ボタン -->
		<button type="submit" class="button lightgreen">管理者権限を取得する</button>
	</form>
</div>

<!-- 管理者ログイン画面へ戻るボタン -->
<a href="admin_login.jsp"><button type="button" class="back_button">戻る</button></a>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>