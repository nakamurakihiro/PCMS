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
import dto.Admin;

/**
 *@author Akihiro Nakamura
 *選択した社員を削除するクラス
 */
@WebServlet("/EmployeeDelete")
public class EmployeeDelete extends HttpServlet{
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
	*データベースに接続して選択した社員IDから該当社員を削除する。<br>
	*削除完了画面へ画面偏移する。
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//ログイン中の管理者IDを取得
		Admin manager = (Admin)session.getAttribute("admin");
		String last_name = manager.getLast_Name();

		//管理者ゲストユーザーの場合、社員削除不可
		if(last_name.equals ("ゲスト")){
			RequestDispatcher disp = request.getRequestDispatcher("not_delete_employee.jsp");
			disp.forward(request, response);
			return;
		}

		//社員一覧画面から選択した社員IDを取得
		int employee_id = Integer.parseInt(request.getParameter("employee_id"));

		//削除準備
		EmployeeDAO ed = new EmployeeDAO();

		//社員削除判定
		boolean deleteJudge = false;

		try {
			//データベース接続
			ed.dbConnect();

			//選択した社員情報を削除
			deleteJudge = ed.deleteEmployee(employee_id);

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

		if(deleteJudge) {
			//削除成功
			RequestDispatcher disp = request.getRequestDispatcher("delete_employee.jsp");
			disp.forward(request, response);
		}else{
			//削除失敗
			RequestDispatcher disp = request.getRequestDispatcher("error.jsp");
			disp.forward(request, response);
		}
	}
}