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
 *機械を追加登録するクラス
 */
@WebServlet("/MachineAdd")
public class MachineAdd extends HttpServlet{
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
	*機械名と機械番号を取得してデータベースに新規登録する。<br>
	*登録完了画面へ画面偏移する。
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//ログインした社員情報の取得
		Employee syain = (Employee)session.getAttribute("employee");

		//社員IDの取得
		int employee_id = syain.getEmployee_Id();

		//ゲストユーザーの場合、機械登録不可
		if(employee_id == 0){
			RequestDispatcher disp = request.getRequestDispatcher("not_add_machine.jsp");
			disp.forward(request, response);
			return;
		}

		//追加機械番号を取得
		String machine_number = request.getParameter("machine_number");

		//追加機械名を取得
		String machine_name = request.getParameter("machine_name");

		//機械名登録準備
		MachineDAO md = new MachineDAO();
		List<Machine> mlist = new ArrayList<>();

		//追加登録判定
		boolean addJudge = false;

		try {
			//データベース接続
			md.dbConnect();

			//機械名の追加登録
			addJudge = md.addMachine(machine_number,machine_name);

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

		if(addJudge){
			//追加登録成功
			RequestDispatcher disp = request.getRequestDispatcher("machine_add_succeed.jsp");
			disp.forward(request, response);
		}else{
			//追加登録失敗
			RequestDispatcher disp = request.getRequestDispatcher("error.jsp");
			disp.forward(request, response);
		}
	}
}