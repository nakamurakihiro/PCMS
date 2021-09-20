<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Employee"%>
<%@ page import="dto.Report"%>
<%@ page import="dto.Department" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>

<%
//セッションスコープに保存された部署一覧を取得
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
<title>マイページ編集</title>
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
<h5>マイページ編集</h5>

<!-- マイページ編集画面 -->
<div class="center_screen-5">
	<form action="./EmployeeUpd" method="post">
		<table class="border_table">
			<tr class="border_style">
				<th>社員ID</th>
				<td><%=syain.getEmployee_Id()%><input type="hidden" name="employee_id" value="<%=syain.getEmployee_Id()%>"></td>
			</tr>

			<tr class="border_style">
				<th>フリガナ</th>
				<td><input type="text" name="last_kana" value="<%=syain.getLast_Kana()%>" class="textbox">
					<input type="text" name="first_kana" value="<%=syain.getFirst_Kana()%>" class="textbox"></td>
			</tr>

			<tr class="border_style">
				<th>氏名</th>
				<td><input type="text" name="last_name" value="<%=syain.getLast_Name()%>" class="textbox">
					<input type="text" name="first_name" value="<%=syain.getFirst_Name()%>" class="textbox"></td>
			</tr>

			<tr class="border_style">
				<th>所属部署</th>
				<td><select name="department_name" class="selectbox">
					<option value="">選択して下さい</option>
						<%for(int i = 0; i < dlist.size(); i++){%>
							<%Department department = (Department)dlist.get(i);%>
								<option value="<%=department.getDepartment_Name()%>"><%=department.getDepartment_Name()%></option>
							<% } %>
					</select>
				</td>
			</tr>

			<tr class="border_style">
				<th>血液型</th>
				<td><select name="blood" class="selectbox">
						<option value="">選択して下さい</option>
						<option value="A型">A型</option>
						<option value="B型">B型</option>
						<option value="O型">O型</option>
						<option value="AB型">AB型</option>
					</select>
				</td>
			</tr>

			<tr class="border_style">
				<th>生年月日</th>
				<td>
					<input type="date" name="birthday" value="<%=syain.getBirthday()%>">
				</td>
			</tr>

			<tr class="border_style">
				<th>年齢</th>
				<td>
					<input type="text" name="age" value="<%=syain.getAge()%>" class="numberbox">歳
				</td>
			</tr>

			<tr class="border_style">
				<th>性別</th>
				<td><input type="radio" name="gender" value="男性" checked="checked">男性
					<input type="radio" name="gender" value="女性">女性
				</td>
			</tr>
		</table>

		<!-- 保存ボタン -->
		<button type="submit" class="button blue button_fixed">保存</button>
	</form>
</div>

<!-- マイページへ戻るボタン -->
<a href="mypage.jsp"><button type="button" class="back_button">戻る</button></a>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>