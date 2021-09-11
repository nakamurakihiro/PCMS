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
 *管理者権限を削除するクラス
 */
@WebServlet("/AdminDelete")
public class AdminDelete extends HttpServlet{
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
	*データベースに接続して管理者権限を削除する。<br>
	*管理者権限削除完了画面へ画面偏移する。
	*
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//ログイン中の管理者IDを取得
		Admin manager = (Admin)session.getAttribute("admin");
		int employee_id = manager.getEmployee_Id();

		//管理者ゲストユーザーの場合、削除不可
		if(employee_id == 0){
			RequestDispatcher disp = request.getRequestDispatcher("not_delete_guest_admin.jsp");
			disp.forward(request, response);
			return;
		}

		//管理者権限削除準備
		AdminDAO ad = new AdminDAO();

		//管理者削除判定
		boolean deleteJudge = false;

		try {
			//データベース接続
			ad.dbConnect();

			//管理者権限削除
			deleteJudge = ad.deleteAdmin(employee_id);

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

		if(deleteJudge) {
			//管理者権限削除
			RequestDispatcher disp = request.getRequestDispatcher("delete_admin.jsp");
			disp.forward(request, response);
		}else{
			//削除失敗
			RequestDispatcher disp = request.getRequestDispatcher("error.jsp");
			disp.forward(request, response);
		}
	}
}