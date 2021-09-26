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
 *社員ログインクラス
 */
@WebServlet("/EmployeeLogin")
public class EmployeeLogin extends HttpServlet{
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
	*データベースに接続してログイン情報に該当する社員を検索<br>
	*ログインに成功したらセッションに社員情報をセットする。<br>
	*メインメニュー画面へ画面偏移する。
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//ログイン画面からログイン情報の取得
		//メールアドレス
		String email_address = request.getParameter("email_address");
		//パスワード
		String employee_password = request.getParameter("employee_password");

		//社員検索準備
		EmployeeDAO ed = new EmployeeDAO();
		Employee employee = new Employee();

		try {
			//データベース接続
			ed.dbConnect();

			//該当社員の検索
			employee = ed.loginEmployee(email_address,employee_password);

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

		//管理者権限取得画面ログインボタンのname属性を取得
		String lc = request.getParameter("login_check");

		if(employee != null && lc != null){
			//管理者権限取得前のログインチェック成功
			RequestDispatcher disp = request.getRequestDispatcher("./SelectAdminPassword");
			disp.forward(request, response);
		}else if(employee != null && lc == null){
			//ログイン成功
			RequestDispatcher disp = request.getRequestDispatcher("WorkSelect");
			disp.forward(request, response);
		}else{
			//ログイン失敗
			RequestDispatcher disp = request.getRequestDispatcher("login_error.jsp");
			disp.forward(request, response);
		}
	}
}