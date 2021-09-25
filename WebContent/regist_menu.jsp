<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Department" %>
<%@ page import="dto.Employee" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>

<%
//セッションスコープに保存された部署情報を取得
List<Department> dlist = (List<Department>)session.getAttribute("dlist");
%>

<%
//セッションスコープに保存された社員情報を取得
Employee syain = (Employee)session.getAttribute("employee");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規社員登録</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<!-- ヘッダー -->
<header>
	<h1>PCMS</h1>
</header>

<!-- 画面名称 -->
<h5>新規社員登録</h5>

<!-- 登録内容の入力 -->
<div class="center_screen-2">
	<!-- 登録情報の入力 -->
	<form action="./EmployeeRegister" method="post">
		<table class="border_table">
			<tr class="border_style">
				<th>社員ID</th>
				<td><%=syain.getEmployee_Id()%></td>
			</tr>

			<tr class="border_style">
				<th>氏名<span>(必須)</span></th>
				<td><input type="text" name="last_name" class="textbox" autocomplete="off" placeholder="姓" required>
					<input type="text" name="first_name" class="textbox" autocomplete="off" placeholder="名" required></td>
			</tr>

			<tr class="border_style">
				<th>フリガナ<span>(必須)</span></th>
				<td><input type="text" name="last_kana" class="textbox" autocomplete="off" placeholder="セイ" required>
					<input type="text" name="first_kana" class="textbox" autocomplete="off" placeholder="メイ" required></td>
			</tr>

			<tr class="border_style">
				<th>所属部署<span>(必須)</span></th>
				<td>
					<select name="department_name" class="selectbox">
						<option>選択して下さい</option>
							<%for(int i = 0; i < dlist.size(); i++){%>
								<%Department department = (Department)dlist.get(i);%>
									<option value="<%=department.getDepartment_Name()%>"><%=department.getDepartment_Name()%></option>
							<% } %>
					</select>
				</td>
			</tr>

			<tr class="border_style">
				<th>血液型<span>(必須)</span></th>
				<td>
					<select name="blood" class="selectbox">
						<option>選択して下さい</option>
						<option value="A型">A型</option>
						<option value="B型">B型</option>
						<option value="O型">O型</option>
						<option value="AB型">AB型</option>
					</select>
				</td>
			</tr>

			<tr class="border_style">
				<th>パスワード<span>(必須)</span></th>
				<td><input type="password" name="employee_password" class="longbox" placeholder="※半角英数字6～11文字" required></td>
			</tr>
		</table>

		<!-- 登録ボタン -->
		<button type="submit" class="button lightgreen button_fixed">登録する</button>
	</form>
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