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
<title>マイページ</title>
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
			<li><a href="mail_box.jsp">メール</a></li>
			<li><a href="mypage.jsp">マイページ</a></li>
			<li><a href="employee_password_change.jsp">パスワード変更</a></li>
			<li><a href="./Logout" onClick="return Logout_Check()">ログアウト</a></li>
		</ul>
	</nav>
</header>

<!-- 画面名称 -->
<h5>マイページ</h5>

<!-- ログインした社員情報の表示 -->
<div class="center_screen-5">
	<table class="border_table">
		<tr class="border_style">
			<th>社員ID</th>
			<td><%=syain.getEmployee_Id()%></td>
		</tr>

		<tr class="border_style">
			<th>氏名</th>
			<td><%=syain.getLast_Name()%><%=syain.getFirst_Name()%></td>
		</tr>

		<tr class="border_style">
			<th>メールアドレス</th>
			<td><%=syain.getEmail_Address()%></td>
		</tr>

		<tr class="border_style">
			<th>所属部署</th>
			<td><%=syain.getDepartment_Name()%></td>
		</tr>

		<tr class="border_style">
			<th>血液型</th>
			<td><%=syain.getBlood()%></td>
		</tr>

		<tr class="border_style">
			<th>生年月日</th>
			<td><%=syain.getBirthday()%></td>
		</tr>

		<tr class="border_style">
			<th>年齢</th>
			<td><%=syain.getAge()%>歳</td>
		</tr>

		<tr class="border_style">
			<th>性別</th>
			<td><%=syain.getGender()%></td>
		</tr>

		<tr class="border_style">
			<th>工数履歴一覧</th>
			<td><a href="./ReportFind"><button type="submit" class="small_button gray">詳細</button></a></td>
		</tr>
	</table>

	<!-- マイページ編集ボタン -->
	<form action="./DepartmentListDisplay" method="post">
		<button type="submit" class="button blue button_fixed">マイページを編集する</button>
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