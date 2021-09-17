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

import check.CheckControlManager;
import dao.AdminDAO;
import dto.Admin;

/**
 *@author Akihiro Nakamura
 *管理者パスワード変更クラス
 */
@WebServlet("/AdminPasswordChange")
public class AdminPasswordChange extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 *検査対象文字列
	 */
	private String target;

	/**
	 *エラー該当文字列
	 */
	private String error_target;

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
	*新しい管理者パスワードのnull,未入力,正規表現のチェック<br>
	*問題がなければ新しい管理者パスワードとして変更する。<br>
	*管理者パスワード変更画面に画面偏移する。
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//ログイン中の管理者情報を取得
		Admin manager = (Admin)session.getAttribute("admin");

		//管理者パスワードの取得
		String admin_password = manager.getAdmin_Password();

		//管理者パスワード変更画面から現在の管理者パスワードを取得
		String currentPassword = request.getParameter("current_password");

		//管理者パスワード変更画面から新しい管理者パスワードを取得
		String newPassword = request.getParameter("new_password");

		//管理者パスワード変更画面から確認用の管理者パスワードを取得
		String renewPassword = request.getParameter("renew_password");

		//管理者パスワード検査準備
		CheckControlManager ccm = new CheckControlManager();

		//配列に検査対象パスワードを代入
		String[] password = {admin_password,currentPassword,newPassword,renewPassword};

		//null・未入力判定
		boolean nullEmptyJudge = true;

		//検査対象パスワードを順番にチェック
		for(String target:password){

			//null・入力チェック
			nullEmptyJudge = ccm.nullEmpty(target);

			//falseなら処理を抜ける
			if(!nullEmptyJudge) {
				//エラー該当箇所を特定
				error_target = target;
				break;
			}
		}

		//admin_passwordでエラー
		if(error_target == admin_password) {
			RequestDispatcher disp = request.getRequestDispatcher("admin_password_error.jsp");
			disp.forward(request, response);
			return;
		}

		//currentPasswordでエラー
		if(error_target == currentPassword) {
			RequestDispatcher disp = request.getRequestDispatcher("current_admin_password_error.jsp");
			disp.forward(request, response);
			return;
		}

		//newPasswordでエラー
		if(error_target == newPassword) {
			RequestDispatcher disp = request.getRequestDispatcher("new_admin_password_error.jsp");
			disp.forward(request, response);
			return;
		}

		//renewPasswordでエラー
		if(error_target == renewPassword) {
			RequestDispatcher disp = request.getRequestDispatcher("renew_admin_password_error.jsp");
			disp.forward(request, response);
			return;
		}

		//データベース登録管理者パスワードと入力した現在の管理者パスワードを照合
		if(!admin_password.equals(currentPassword)){
			RequestDispatcher disp = request.getRequestDispatcher("admin_password_match_error.jsp");
			disp.forward(request, response);
			return;
		}

		//新しい管理者パスワードと確認用パスワードを照合
		if(!newPassword.equals(renewPassword)){
			RequestDispatcher disp = request.getRequestDispatcher("new_password_match_error.jsp");
			disp.forward(request, response);
			return;
		}

		//新しい管理者パスワードの正規表現判定
		boolean regixJudge = true;

		//新しい管理者パスワードを検査対象文字列へ代入
		target = newPassword;

		//新しい管理者パスワードを正規表現でチェック
		regixJudge = ccm.regix(target);

		if(!regixJudge) {
			//正規表現チェックでエラー
			RequestDispatcher disp = request.getRequestDispatcher("admin_password_regix_error.jsp");
			disp.forward(request, response);
			return;
		}

		//管理者パスワード変更判定
		boolean changeJudge = false;

		//管理者パスワード変更登録準備
		AdminDAO ad = new AdminDAO();

		try {

			//データベース接続
			ad.dbConnect();

			//新しいパスワードに変更
			admin_password = newPassword;

			//データベースに新しいパスワードを変更登録
			changeJudge = ad.changeAdminPassword(admin_password);

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

		if(changeJudge) {
			//管理者パスワード変更成功
			RequestDispatcher disp = request.getRequestDispatcher("admin_password_change_succeed.jsp");
			disp.forward(request, response);
		}else{
			//管理者パスワード変更失敗
			RequestDispatcher disp = request.getRequestDispatcher("admin_password_change_error.jsp");
			disp.forward(request, response);
		}
	}
}
