package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.WorkDAO;
import dto.Employee;
import dto.Work;

/**
 *@author Akihiro Nakamura
 *作業項目を削除するクラス
 */
@WebServlet("/WorkDelete")
public class WorkDelete extends HttpServlet{
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
	*データベースに接続して選択した作業項目を削除する。<br>
	*削除完了画面へ画面偏移する。
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションオブジェクトの開始
		HttpSession session = request.getSession();

		//ログインした社員情報の取得
		Employee syain = (Employee)session.getAttribute("employee");

		//社員IDの取得
		int employee_id = syain.getEmployee_Id();

		//ゲストユーザーの場合、作業項目削除不可
		if(employee_id == 0){
			RequestDispatcher disp = request.getRequestDispatcher("not_delete_task.jsp");
			disp.forward(request, response);
			return;
		}

		//ログインした社員の部署IDを取得
		String department_id = syain.getDepartment_Id();

		//選択した作業項目を取得
		String task = request.getParameter("task");

		//作業項目削除準備
		WorkDAO wd = new WorkDAO();
		List<Work> wlist = new ArrayList<>();

		//作業項目削除判定
		boolean deleteJudge = false;

		try {
			//データベース接続
			wd.dbConnect();

			//選択した作業項目を削除
			deleteJudge = wd.deleteTask(task);

			//該当部署の作業項目取得
			wlist = wd.selectTask(department_id);

			//セッションスコープに保存
			session.setAttribute("wlist",wlist);

		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				//データベースの切断
				wd.dbClose();

			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		if(deleteJudge) {
			//削除成功
			RequestDispatcher disp = request.getRequestDispatcher("delete_task.jsp");
			disp.forward(request, response);
		}else{
			//削除失敗
			RequestDispatcher disp = request.getRequestDispatcher("delete_error.jsp");
			disp.forward(request, response);
		}
	}
}
