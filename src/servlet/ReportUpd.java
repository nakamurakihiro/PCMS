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

import dao.ReportDAO;
import dto.Admin;
import dto.Report;

/**
 *@author Akihiro Nakamura
 *工数記録を編集するクラス
 */
@WebServlet("/ReportUpd")
public class ReportUpd extends HttpServlet{
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
	*データベースに接続して編集した工数記録を更新する。<br>
	*更新完了画面へ画面偏移する。
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		//ログイン中の管理者情報を取得
		Admin manager = (Admin)session.getAttribute("admin");
		String last_name = manager.getLast_Name();

		//管理者ゲストユーザーの場合、工数記録編集不可
		if(last_name.equals ("ゲスト")){
			RequestDispatcher disp = request.getRequestDispatcher("not_edit_report.jsp");
			disp.forward(request, response);
			return;
		}

		//編集した工数記録情報を取得
		//機械名
		String machine_name = request.getParameter("machine_name");
		//実働時間
		int work_time = Integer.parseInt(request.getParameter("work_time"));
		//残業時間
		int over_time = Integer.parseInt(request.getParameter("over_time"));
		//休日出勤
		int holiday_work = Integer.parseInt(request.getParameter("holiday_work"));
		//作業項目
		String task = request.getParameter("task");
		//社員ID
		int employee_id = Integer.parseInt(request.getParameter("employee_id"));
		//日付
		String day = request.getParameter("day");

		//セレクトボックス未選択でエラー
		if(machine_name == "" || task == ""){
			//工数記録保存失敗
			RequestDispatcher disp = request.getRequestDispatcher("selectbox_null_error-2.jsp");
			disp.forward(request, response);
		}

		//編集した工数記録情報を工数表クラスにセット
		Report report = new Report();

		//機械名をセット
		report.setMachine_Name(machine_name);
		//実働時間をセット
		report.setWork_Time(work_time);
		//残業時間をセット
		report.setOver_Time(over_time);
		//休日出勤をセット
		report.setHoliday_Work(holiday_work);
		//作業内容をセット
		report.setTask(task);
		//社員IDをセット
		report.setEmployee_Id(employee_id);
		//日付をセット
		report.setDay(day);

		//工数記録更新準備
		ReportDAO rd = new ReportDAO();

		try {
			//データベース接続
			rd.dbConnect();

			//編集した工数記録をデータベースに更新
			report = rd.updateReport(report);

			//セッションスコープに保存
			session.setAttribute("report",report);

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

		if(report != null) {
			//更新成功
			RequestDispatcher disp = request.getRequestDispatcher("report_update.jsp");
			disp.forward(request, response);
		}else{
			//更新失敗
			RequestDispatcher disp = request.getRequestDispatcher("report_update_error.jsp");
			disp.forward(request, response);
		}
	}
}
