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

import dao.EmployeeDAO;
import dao.ReportDAO;
import dto.Employee;
import dto.Report;

/**
 *@author Akihiro Nakamura
 *選択した社員の工数一覧表示するクラス
 */
@WebServlet("/SelectReportListDisplay")
public class SelectReportListDisplay extends HttpServlet{
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
	*選択した社員の社員情報と工数記録を取得し、セッションにセットする。<br>
	*工数記録画面へ画面偏移する。
	*
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//社員一覧から選択した社員IDを取得
		int employee_id = Integer.parseInt(request.getParameter("employee_id"));

		//社員検索準備
		EmployeeDAO ed = new EmployeeDAO();
		Employee employee = new Employee();

		//工数記録検索準備
		ReportDAO rd = new ReportDAO();
		List<Report> rlist  = new ArrayList<>();

		try {
			//データベース接続
			ed.dbConnect();
			rd.dbConnect();

			//選択した社員の社員情報の取得
			employee = ed.selectEmployee(employee_id);

			//工数記録一覧表示
			rlist = rd.selectAllReport(employee_id);

			//選択した社員情報の保存
			session.setAttribute("employee",employee);

			//選択した社員の工数記録を保存
			session.setAttribute("rlist",rlist);

		}catch (SQLException e){
			e.printStackTrace();

		}finally{
			try{
				//データベースの切断
				rd.dbClose();

			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		if(employee != null && rlist != null){
			//選択した社員の工数記録一覧表示
			RequestDispatcher disp = request.getRequestDispatcher("select_work_report.jsp");
			disp.forward(request, response);
		}else{
			//読み込み失敗
			RequestDispatcher disp = request.getRequestDispatcher("error.jsp");
			disp.forward(request, response);
		}
	}
}