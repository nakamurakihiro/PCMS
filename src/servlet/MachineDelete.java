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

import dao.MachineDAO;
import dto.Employee;
import dto.Machine;

/**
 *@author Akihiro Nakamura
 *登録機械情報を削除するクラス
 */
@WebServlet("/MachineDelete")
public class MachineDelete extends HttpServlet{
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
	*データベースに接続して選択した機械名を削除する。<br>
	*削除完了画面へ画面偏移する。
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//ログインした社員情報の取得
		Employee syain = (Employee)session.getAttribute("employee");

		//社員IDの取得
		int employee_id = syain.getEmployee_Id();

		//ゲストユーザーの場合、機械削除不可
		if(employee_id == 0){
			RequestDispatcher disp = request.getRequestDispatcher("not_delete_machine.jsp");
			disp.forward(request, response);
			return;
		}

		//選択した機械名を取得
		String machine_name = request.getParameter("machine_name");

		//機械名削除準備
		MachineDAO md = new MachineDAO();
		List<Machine> mlist = new ArrayList<>();

		//機械名削除判定
		boolean deleteJudge = false;

		try {
			//データベース接続
			md.dbConnect();

			//選択した機械名を削除
			deleteJudge = md.deleteMachine(machine_name);

			//機械一覧表示
			mlist = md.showAllMachine();

			//セッションスコープに保存
        	session.setAttribute("mlist",mlist);

		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				//データベースの切断
				md.dbClose();

			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		if(deleteJudge) {
			//削除成功
			RequestDispatcher disp = request.getRequestDispatcher("delete_machine.jsp");
			disp.forward(request, response);
	    }else{
			//削除失敗
			RequestDispatcher disp = request.getRequestDispatcher("delete_error.jsp");
			disp.forward(request, response);
		}
	}
}