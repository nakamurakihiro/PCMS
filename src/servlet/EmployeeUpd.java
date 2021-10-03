package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import dto.Employee;

/**
 *@author Akihiro Nakamura
 *マイページから社員情報を更新するクラス
 */
@WebServlet("/EmployeeUpd")
public class EmployeeUpd extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	*@see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*@param request クライアントがServletへ要求したリクエスト内容を含むHttpServletRequestオブジェクト
	*@param response Servletがクライアントに返すレスポンス内容を含むHttpServletResponseオブジェクト
	*@throws ServletException ServletException ServletがGetリクエストを処理中にServlet内で例外が発生
	*@throws IOException ServletがGetリクエストを処理中に入出力エラーが発生
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	*@see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*@param request クライアントがServletへ要求したリクエスト内容を含むHttpServletRequestオブジェクト
	*@param response Servletがクライアントに返すレスポンス内容を含むHttpServletResponseオブジェクト
	*@throws ServletException ServletがPostリクエストを処理中にServlet内で例外が発生
	*@throws IOException ServletがPostリクエストを処理中に入出力エラーが発生
	*データベースに接続して編集した社員情報を更新する。<br>
	*更新完了画面に画面偏移する。
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//マイページから編集した社員情報を取得
		//社員ID
		int employee_id = Integer.parseInt(request.getParameter("employee_id"));
		//メールアドレス
		String email_address = request.getParameter("email_address");
		//姓
		String last_name = request.getParameter("last_name");
		//名
		String first_name = request.getParameter("first_name");
		//姓フリガナ
		String last_kana = request.getParameter("last_kana");
		//名フリガナ
		String first_kana = request.getParameter("first_kana");
		//部署名
		String department_name = request.getParameter("department_name");
		//血液型
		String blood = request.getParameter("blood");
		//年齢
		int age = Integer.parseInt(request.getParameter("age"));
		//性別
		String gender = request.getParameter("gender");
		//生年月日
		String birthday = request.getParameter("birthday");

		//ゲストユーザーの場合、マイページ編集不可
		if(employee_id == 0){
			RequestDispatcher disp = request.getRequestDispatcher("not_edit_mypage.jsp");
			disp.forward(request, response);
			return;
		}

		//セレクトボックス未選択でエラー
		if(department_name == "" || blood == ""){
			//マイページ更新失敗
			RequestDispatcher disp = request.getRequestDispatcher("selectbox_null_error.jsp");
			disp.forward(request, response);
		}

		//編集した社員情報を社員表クラスにセット
		Employee employee = new Employee();

		//社員IDをセット
		employee.setEmployee_Id(employee_id);
		//メールアドレスをセット
		employee.setEmail_Address(email_address);
		//姓をセット
		employee.setLast_Name(last_name);
		//名をセット
		employee.setFirst_Name(first_name);
		//姓フリガナをセット
		employee.setLast_Kana(last_kana);
		//名フリガナをセット
		employee.setFirst_Kana(first_kana);
		//部署名をセット
		employee.setDepartment_Name(department_name);
		//血液型をセット
		employee.setBlood(blood);
		//年齢をセット
		employee.setAge(age);
		//生年月日をセット
		employee.setBirthday(birthday);
		//性別をセット
		employee.setGender(gender);

		//社員情報更新準備
		EmployeeDAO ed = new EmployeeDAO();

		try {
			//データベース接続
			ed.dbConnect();

			//編集した社員情報をデータベースに更新
			employee = ed.updateEmployee(employee);

			//セッションスコープに保存
			session.setAttribute("employee",employee);

		}catch (SQLException e){
			e.printStackTrace();

		}finally{
			try{
				//データベースの切断
				ed.dbClose();

			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		if(employee != null) {
			//更新成功
			RequestDispatcher disp = request.getRequestDispatcher("mypage_update_succeed.jsp");
			disp.forward(request, response);
		}else{
			//更新失敗
			RequestDispatcher disp = request.getRequestDispatcher("mypage_update_error.jsp");
			disp.forward(request, response);
		}
	}
}