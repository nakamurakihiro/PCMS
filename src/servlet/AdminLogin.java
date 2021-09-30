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
 *管理者ログインクラス
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	*@see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*@param request クライアントがServletへ要求したリクエスト内容を含むHttpServletRequestオブジェクト
	*@param response Servletがクライアントに返すレスポンス内容を含むHttpServletResponseオブジェクト
	*@throws ServletException ServletException ServletException ServletがGetリクエストを処理中にServlet内で例外が発生
	*@throws IOException IOException ServletがGetリクエストを処理中に入出力エラーが発生
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
	*データベースに接続して管理者としてログインする。<br>
	*ログインに成功したらセッションに管理者情報をセットする。<br>
	*管理者メニュ―画面へ画面偏移する。
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//管理者ログイン画面から入力されたログイン情報を取得
		//メールアドレス
		String email_address = request.getParameter("email_address");
		//管理者パスワード
		String admin_password = request.getParameter("admin_password");

		//管理者検索準備
		AdminDAO ad = new AdminDAO();
		Admin admin = new Admin();

		try {
			//データベース接続
			ad.dbConnect();

			//管理者検索
			admin = ad.loginAdmin(email_address,admin_password);

			//セッションスコープに保存
			session.setAttribute("admin",admin);

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

		if(admin != null){
			//管理者ログイン成功
			RequestDispatcher disp = request.getRequestDispatcher("admin_menu.jsp");
			disp.forward(request, response);
		}else{
			//管理者ログイン失敗
			RequestDispatcher disp = request.getRequestDispatcher("login_error.jsp");
			disp.forward(request, response);
		}
	}
}
