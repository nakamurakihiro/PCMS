<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Admin" %>
<%@ page import="dto.Employee" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>

<%
//セッションスコープに保存された社員情報を取得
Admin manager=(Admin)session.getAttribute("admin");
%>

<%
//セッションスコープに保存された社員一覧を取得
 List<Employee> elist = (List<Employee>)session.getAttribute("elist");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員一覧</title>
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
			<li><a href="./Logout" onClick="return Logout_Check()">ログアウト</a></li>
		</ul>
	</nav>
</header>

<header class="sub_header">
	<h3>社員一覧</h3>
</header>

<!-- 社員一覧 -->
<div class="center_screen-4">
	<table class="border_table-2">
		<tr class="border_style gray fixed">
			<th class="fixed">社員ID</th>
			<th class="fixed">氏名</th>
			<th class="fixed">フリガナ</th>
			<th class="fixed">メールアドレス</th>
			<th class="fixed">所属部署</th>
			<th class="fixed">年齢</th>
			<th class="fixed">生年月日</th>
			<th class="fixed">性別</th>
			<th class="fixed">血液型</th>
			<th class="fixed">工数実績</th>
			<th class="fixed">社員削除</th>
		</tr>

		<%for(int i=1; i<elist.size(); i++){%>
			<%Employee employee=(Employee)elist.get(i);%>
				<tr class="border_style fixed-2">
					<td><%=employee.getEmployee_Id()%></td>
					<td><%=employee.getLast_Name()%> <%=employee.getFirst_Name()%></td>
					<td><%=employee.getLast_Kana()%> <%=employee.getFirst_Kana()%></td>
					<td><%=employee.getEmail_Address()%></td>
					<td><%=employee.getDepartment_Name()%></td>
					<td><%=employee.getAge()%></td>
					<td><%=employee.getBirthday()%></td>
					<td><%=employee.getGender()%></td>
					<td><%=employee.getBlood()%></td>
					<!-- 詳細ボタンをクリックすると、各社員工数実績を表示 -->
					<td>
						<form action="./SelectReportListDisplay?name=<%=employee.getEmployee_Id()%>&employee_id=<%=employee.getEmployee_Id()%>" method="post">
							<button type="submit" class="small_button gray">詳細</button>
						</form>
					</td>

					<!-- 社員削除ボタン -->
					<td>
						<form action="./EmployeeReportDelete?name=<%=employee.getEmployee_Id()%>&employee_id=<%=employee.getEmployee_Id()%>" method="post">
							<button type="submit" class="small_button red" onClick="return Delete_Check()">削除</button>
						</form>
					</td>
				</tr>
		<% } %>
	</table>
</div>

<!-- 管理者メニューへ戻るボタン -->
<a href="admin_menu.jsp"><button type="button" class="back_button">戻る</button></a>

<!-- フッター -->
<footer>
	<div class="logo">PCMS</div>
	<h4><small>Copyright © 2020-2021 PCMS. All rights reserved.</small></h4>
</footer>
</body>
</html>