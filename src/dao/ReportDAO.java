package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Report;

/**
 *@author Akihiro Nakamura
 *工数テーブルに接続するDAOクラス
 */
public class ReportDAO extends BaseDAO {

	/**
	 *リストに格納
	 */
	private List<Report> rlist = new ArrayList<>();

	/**
	 *@param employee_id 社員ID
	 *@return List<Report> 工数記録一覧
	 *@throws SQLException データベース接続処理でエラー
	 *ログインした社員の工数記録を探すメソッド
	 */
	public List<Report> findReport(int employee_id) throws SQLException{

		//データベースからログインした社員の工数記録を取得するSQL文
		String sql ="select * from reports where employee_id = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		ps.setInt(1,employee_id);

		//SQL文の実行
		rs = ps.executeQuery();

		while(rs.next()){

			//データベースから取得した値をセット
			Report report = new Report();

			//社員ID
			report.setEmployee_Id(rs.getInt("employee_id"));
			//姓
			report.setLast_Name(rs.getString("last_name"));
			//名
			report.setFirst_Name(rs.getString("first_name"));
			//部署コード
			report.setDepartment_Id(rs.getString("department_id"));
			//日付
			report.setDay(rs.getString("day"));
			//機械名
			report.setMachine_Name(rs.getString("machine_name"));
			//作業項目
			report.setTask(rs.getString("task"));
			//実働時間
			report.setWork_Time(rs.getInt("work_time"));
			//残業時間
			report.setOver_Time(rs.getInt("over_time"));
			//休日出勤
			report.setHoliday_Work(rs.getInt("holiday_work"));
			//コメント
			report.setComment(rs.getString("comment"));

			rlist.add(report);
		}

		return rlist;
	}

	/**
	 *@param employee_id 社員ID
	 *@param last_name 姓
	 *@param first_name 名
	 *@param department_id 部署ID
	 *@param day 日付
	 *@param machine_name 機械名
	 *@param task 作業項目
	 *@param work_time 実働時間
	 *@param over_time 残業時間
	 *@param holiday_work 休日出勤
	 *@param comment コメント
	 *@return 工数を記録出来たらtrue,出来なかったらfalse
	 *@throws SQLException データベース接続処理でエラー
	 *工数記録メソッド
	 */
	public boolean addReport(int employee_id,String last_name,String first_name,String department_id,String day,String machine_name,String task,int work_time,int over_time,int holiday_work,String comment) throws SQLException{

		//オートコミットの無効
		con.setAutoCommit(false);

		//追加記録判定
		boolean recordJudge = false;

		//データベースに工数を追加記録するSQL文
		String sql = "insert into reports (employee_id,last_name,first_name,department_id,day,machine_name,task,work_time,over_time,holiday_work,comment) values (?,?,?,?,?,?,?,?,?,?,?)";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		//社員ID
		ps.setInt(1,employee_id);
		//姓
		ps.setString(2,last_name);
		//名
		ps.setString(3,first_name);
		//部署ID
		ps.setString(4,department_id);
		//日付
		ps.setString(5,day);
		//機械名
		ps.setString(6,machine_name);
		//作業項目
		ps.setString(7,task);
		//実働時間
		ps.setInt(8,work_time);
		//残業時間
		ps.setInt(9,over_time);
		//休日出勤
		ps.setInt(10,holiday_work);
		//コメント
		ps.setString(11,comment);

		//SQL文の実行
		int ar = ps.executeUpdate();

		//記録したらコミットする
		if(ar > 0){
			recordJudge = true;
			con.commit();
		}
		return recordJudge;
	}

	/**
	 *@param report 工数記録
	 *@return 編集し、更新した工数記録を返す
	 *@throws SQLException データベース接続処理でエラー
	 *工数記録を更新するメソッド
	 */
	public Report updateReport(Report report) throws SQLException{

		//オートコミットの無効
		con.setAutoCommit(false);

		//データベースの工数記録を更新するSQL文
		String sql = "update reports set machine_name = ?,work_time = ?,over_time = ?,holiday_work = ?,task = ? where employee_id = ? and day = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		//機械名
		ps.setString(1,report.getMachine_Name());
		//実働時間
		ps.setInt(2,report.getWork_Time());
		//残業時間
		ps.setInt(3,report.getOver_Time());
		//休日出勤
		ps.setInt(4,report.getHoliday_Work());
		//作業内容
		ps.setString(5,report.getTask());
		//社員ID
		ps.setInt(6,report.getEmployee_Id());
		//日付
		ps.setString(7,report.getDay());

		//SQL文の実行
		int ur = ps.executeUpdate();

		//更新したらコミットする
		if(ur != 0){
			con.commit();
		}
		return report;
	}

	/**
	 *@param employee_id 社員ID
	 *@param day 日付
	 *@return 選択した工数記録を削除出来たらtrue,削除出来なかったらfalse
	 *@throws SQLException データベース接続処理でエラー
	 *選択した工数記録を削除するメソッド
	 */
	public boolean deleteReport(int employee_id,String day) throws SQLException{

		//オートコミットの無効
		con.setAutoCommit(false);

		//データベースから選択した工数記録を削除するSQL文
		String sql = "delete from reports where employee_id = ? and day = ?";
		ps = con.prepareStatement(sql);

		//削除判定
		boolean deleteJudge = false;

		//プレースホルダに値をセット
		//社員ID
		ps.setInt(1,employee_id);
		//日付
		ps.setString(2,day);

		//SQL文の実行
		int dr = ps.executeUpdate();

		//削除したらコミットする
		if(dr > 0){
			deleteJudge = true;
			con.commit();
		}
		return deleteJudge;
	}

	/**
	 *@param employee_id 社員ID
	 *@param day 日付
	 *@return 選択した工数記録を返す
	 *@throws SQLException データベース接続処理でエラー
	 *選択した工数情報を探すメソッド
	 */
	public Report selectReport(int employee_id,String day) throws SQLException{

		//初期化
		Report report = null;

		//データベースから工数記録を取得するSQL文
		String sql ="select * from reports where employee_id = ? and day = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		//社員ID
		ps.setInt(1,employee_id);
		//日付
		ps.setString(2,day);

		//SQL文の実行
		rs = ps.executeQuery();

		if(rs.next()){

			//データベースから取得した値をセット
			report = new Report();

			//社員IDの取得
			report.setEmployee_Id(rs.getInt("employee_id"));
			//姓の取得
			report.setLast_Name(rs.getString("last_name"));
			//名の取得
			report.setFirst_Name(rs.getString("first_name"));
			//部署IDの取得
			report.setDepartment_Id(rs.getString("department_id"));
			//日付の取得
			report.setDay(rs.getString("day"));
			//機械名の取得
			report.setMachine_Name(rs.getString("machine_name"));
			//作業項目の取得
			report.setTask(rs.getString("task"));
			//実働時間の取得
			report.setWork_Time(rs.getInt("work_time"));
			//残業時間の取得
			report.setOver_Time(rs.getInt("over_time"));
			//休日出勤の取得
			report.setHoliday_Work(rs.getInt("holiday_work"));
			//コメントの取得
			report.setComment(rs.getString("comment"));

		}
		return report;
	}

	/**
	 *@param department_id 部署ID
	 *@param machine_name 機械名
	 *@return List<Report> 工数記録一覧
	 *@throws SQLException データベース接続処理でエラー
	 *所属部署と機械名毎に工数記録を取得するメソッド
	 */
	public List<Report> eachReport(String department_id,String machine_name) throws SQLException{

		//データベースから工数記録を取得するSQL文
		String sql ="select last_name,first_name,day,task,work_time,over_time,holiday_work,comment from reports where department_id = ? and machine_name = ? order by day";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		ps.setString(1,department_id);
		ps.setString(2,machine_name);

		//SQL文の実行
		rs = ps.executeQuery();

		while(rs.next()){

			//データベースから取得した値をセット
			Report report = new Report();
			//姓
			report.setLast_Name(rs.getString(1));
			//名
			report.setFirst_Name(rs.getString(2));
			//日付
			report.setDay(rs.getString(3));
			//作業内容
			report.setTask(rs.getString(4));
			//実働時間
			report.setWork_Time(rs.getInt(5));
			//残業時間
			report.setOver_Time(rs.getInt(6));
			//休日出勤
			report.setHoliday_Work(rs.getInt(7));
			//コメント
			report.setComment(rs.getString(8));

			rlist.add(report);
		}
		return rlist;
	}

	/**
	 *@param employee_id 社員ID
	 *@return  List<Report> 工数記録一覧
	 *@throws SQLException データベース接続処理でエラー
	 *選択した社員の工数記録を全件表示するメソッド
	 */
	public List<Report> selectAllReport(int employee_id) throws SQLException{

		//初期化
		Report report = null;

		//データベースから選択した社員の工数記録を取得するSQL文
		String sql ="select last_name,first_name,day,machine_name,task,work_time,over_time,holiday_work,comment from reports where employee_id = ? order by day";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		ps.setInt(1,employee_id);

		//SQL文の実行
		rs = ps.executeQuery();

		while(rs.next()){

			//データベースから取得した値をセット
			report = new Report();

			//姓
			report.setLast_Name(rs.getString(1));
			//名
			report.setFirst_Name(rs.getString(2));
			//日付
			report.setDay(rs.getString(3));
			//機械名
			report.setMachine_Name(rs.getString(4));
			//作業内容
			report.setTask(rs.getString(5));
			//実働時間
			report.setWork_Time(rs.getInt(6));
			//残業時間
			report.setOver_Time(rs.getInt(7));
			//休日出勤
			report.setHoliday_Work(rs.getInt(8));
			//コメント
			report.setComment(rs.getString(9));

			rlist.add(report);
		}
		return rlist;
	}

	/**
	 *@param machine_name 機械名
	 *@return List<Report> 工数記録一覧
	 *@throws SQLException データベース接続処理でエラー
	 *選択した機械名から工数記録を探すメソッド
	 */
	public List<Report> selectMachineReport(String machine_name) throws SQLException{

		//初期化
		Report report = null;

		//データベースから選択した機械名の工数記録を取得するSQL文
		String sql ="select machine_name,work_time,over_time,holiday_work,comment from reports where machine_name = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		//機械名
		ps.setString(1,machine_name);

		//SQL文の実行
		rs = ps.executeQuery();

		while(rs.next()){

			//データベースから取得した値をセット
			report = new Report();

			//機械名
			report.setMachine_Name(rs.getString("machine_name"));
			//実働時間
			report.setWork_Time(rs.getInt("work_time"));
			//残業時間
			report.setOver_Time(rs.getInt("over_time"));
			//休日出勤
			report.setHoliday_Work(rs.getInt("holiday_work"));
			//コメント
			report.setComment(rs.getString("comment"));

			rlist.add(report);
		}
		return rlist;
	}

	/**
	 *@return List<Report> 工数記録一覧
	 *@throws SQLException データベース接続処理でエラー
	 *全社員の工数記録を表示するメソッド
	 */
	public List<Report> showAllReport() throws SQLException{

		//初期化
		Report report = null;

		//データベースから全社員の工数記録を取得するSQL文
		String sql ="select employee_id,last_name,first_name,day,machine_name,task,work_time,over_time,holiday_work,comment from reports order by day";
		ps = con.prepareStatement(sql);

		//SQL文の実行
		rs = ps.executeQuery();

		while(rs.next()){

			//データベースから取得した値をセット
			report = new Report();

			//社員ID
			report.setEmployee_Id(rs.getInt("employee_id"));
			//姓
			report.setLast_Name(rs.getString("last_name"));
			//名
			report.setFirst_Name(rs.getString("first_name"));
			//日付
			report.setDay(rs.getString("day"));
			//機械名
			report.setMachine_Name(rs.getString("machine_name"));
			//作業内容
			report.setTask(rs.getString("task"));
			//実働時間
			report.setWork_Time(rs.getInt("work_time"));
			//残業時間
			report.setOver_Time(rs.getInt("over_time"));
			//休日出勤
			report.setHoliday_Work(rs.getInt("holiday_work"));
			//コメント
			report.setComment(rs.getString("comment"));

			rlist.add(report);
		}
		return rlist;
	}
}