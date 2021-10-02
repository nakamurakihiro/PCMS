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

import dao.AdminDAO;
import dto.Admin;

/**
 *@author Akihiro Nakamura
 *管理者権限取得クラス
 */
@WebServlet("/AdminRegister")
public class AdminRegister extends HttpServlet{
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
	*データベースに接続して管理者パスワードを取得し、入力した管理者パスワードと照合<br>
	*照合して問題なければ、管理者権限を付与する。<br>
	*管理者権限付与画面へ画面偏移する。
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//データベースから管理者パスワードを取得
		Admin manager = (Admin)session.getAttribute("admin");
		String admin_password = manager.getAdmin_Password();

		//管理者登録画面から登録情報の取得
		//社員ID
		int employee_id = Integer.parseInt(request.getParameter("employee_id"));
		//メールアドレス
		String email_address = request.getParameter("email_address");
		//姓
		String last_name = request.getParameter("last_name");
		//名
		String first_name = request.getParameter("first_name");
		//入力した管理者パスワードの取得
		String input_admin_password = request.getParameter("input_admin_password");

		//データベースと入力した管理者パスワードの照合
		if(!admin_password.equals(input_admin_password)){
			RequestDispatcher disp = request.getRequestDispatcher("admin_password_match_error.jsp");
			disp.forward(request, response);
			return;
		}

		//管理者権限付与準備
		AdminDAO ad = new AdminDAO();

		//管理者権限付与判定
		boolean registJudge = false;

		try{
			//データベース接続
			ad.dbConnect();

			//管理者としてデータベースに新規登録
			registJudge = ad.registAdmin(employee_id,email_address,last_name, first_name,admin_password);

		}catch (SQLException e){
			e.printStackTrace();

		}finally{
			try{
				//データベースの切断
				ad.dbClose();

			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		if(registJudge) {
			//管理者権限付与成功
			RequestDispatcher disp = request.getRequestDispatcher("admin_regist_succeed.jsp");
			disp.forward(request, response);
		}else{
			//管理者権限付与失敗
			RequestDispatcher disp = request.getRequestDispatcher("admin_regist_error.jsp");
			disp.forward(request, response);
		}
	}
}
