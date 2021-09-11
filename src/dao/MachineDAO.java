package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Machine;
import dto.Report;

/**
 *@author Akihiro Nakamura
 *機械テーブルに接続するDAOクラス
 */
public class MachineDAO extends BaseDAO {

	/**
	 *リストに格納
	 */
	private List<Machine> mlist = new ArrayList<>();

	/**
	 *@param machine_number 機械番号
	 *@return 指定した機械情報を返す
	 *@throws SQLException データベース接続処理でエラー
	 *選択した機械番号から機械情報を取得するメソッド
	 */
	public Machine findMachine(String machine_number) throws SQLException{

		//初期化
		Machine machine = null;

		//データベースから指定した機械情報を取得するSQL文
		String sql ="select * from machines where machine_number = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		ps.setString(1,machine_number);

		//SQL文の実行
		rs = ps.executeQuery();

		if(rs.next()){

			//データベースから取得した値をセット
			machine = new Machine();

			//機械番号
			machine.setMachine_Number(rs.getString("machine_number"));
			//機械名
			machine.setMachine_Name(rs.getString("machine_name"));
			//機械別工数合計時間
			machine.setMachine_Hours(rs.getInt("machine_hours"));
		}
		return machine;
	}

	/**
	 *@param machine_number 機械番号
	 *@param machine_name 機械名
	 *@return 機械情報を新規登録出来たらtrue,出来なかったらfalse
	 *@throws SQLException データベース接続処理でエラー
	 *機械番号,機械名追加登録メソッド
	 */
	public boolean addMachine(String machine_number,String machine_name) throws SQLException{

		//オートコミットの無効
		con.setAutoCommit(false);

		//追加登録判定
		boolean addJudge = false;

		//データベースに新規登録するSQL文
		String sql = "insert into machines (machine_number,machine_name) values (?,?)";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		//機械番号
		ps.setString(1,machine_number);
		//機械名
		ps.setString(2,machine_name);

		//SQL文の実行
		int am = ps.executeUpdate();

		//追加登録したらコミットする
		if(am > 0){
			addJudge = true;
			con.commit();
		}
		return addJudge;
	}

	/**
	 *@param machine_name 機械名
	 *@return 選択した機械を削除出来たらtrue,出来なかったらfalse
	 *@throws SQLException データベース接続処理でエラー
	 *選択した機械を削除するメソッド
	 */
	public boolean deleteMachine(String machine_name) throws SQLException{

		//オートコミットの無効
		con.setAutoCommit(false);

		//データベースから機械情報を削除するSQL文
		String sql = "delete from machines where machine_name = ?";
		ps = con.prepareStatement(sql);

		//削除判定
		boolean deleteJudge = false;

		//プレースホルダに値をセット
		//機械名
		ps.setString(1,machine_name);

		//SQL文の実行
		int dm = ps.executeUpdate();

		//削除したらコミットする
		if(dm > 0){
			deleteJudge = true;
			con.commit();
		}
		return deleteJudge;
	}

	/**
	 *@return mlist 機械一覧
	 *@throws SQLException データベース接続処理でエラー
	 *機械一覧表示メソッド
	 */
	public List<Machine> showAllMachine() throws SQLException{

		//データベースから全機械番号、機械名を取得するSQL文
		String sql ="select machine_number,machine_name from machines";
		ps = con.prepareStatement(sql);

		//SQL文の実行
		rs = ps.executeQuery();

		while(rs.next()){

			//データベースから取得した値をセット
			Machine machine = new Machine();

			//機械番号
			machine.setMachine_Number(rs.getString("machine_number"));
			//機械名
			machine.setMachine_Name(rs.getString("machine_name"));

			mlist.add(machine);
		}
		return mlist;
	}

	/**
	 *@param report 工数記録
	 *@param machine 機械情報
	 *@return 計算した機械別工数合計時間を返す
	 *@throws SQLException データベース接続処理でエラー
	 *機械別工数合計時間を計算するメソッド
	 */
	public Machine sumMachineHours(Report report,Machine machine) throws SQLException{

		//オートコミットの無効
		con.setAutoCommit(false);

		//機械別工数合計時間を計算するSQL文
		String sql = "update machines set machine_hours = (select sum(work_time) + sum(over_time) + sum(holiday_work) from reports where machine_name = ? group by machine_number) where machine_name = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		ps.setString(1,report.getMachine_Name());
		ps.setString(2,machine.getMachine_Name());

		//SQL文の実行
		int smh = ps.executeUpdate();

		//計算したらコミットする
		if(smh > 0){
			con.commit();
		}
		return machine;
	}

	/**
	 *@param machine 機械情報
	 *@return 機械別工数合計時間を返す
	 *@throws SQLException データベース接続処理でエラー
	 *機械別工数合計時間を取得するメソッド
	 */
	public Machine getMachineHours(Machine machine) throws SQLException{

		//データベースから更新したデータを取得するSQL文
		String sql ="select machine_hours from machines where machine_name = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		ps.setString(1,machine.getMachine_Name());

		//SQL文の実行
		rs = ps.executeQuery();

		if(rs.next()){

			//機械別工数合計時間の取得
			machine.setMachine_Hours(rs.getInt("machine_hours"));
		}
		return machine;
	}
}
