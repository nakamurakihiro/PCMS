package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Department;
import dto.Report;

/**
 *@author Akihiro Nakamura
 *部署テーブルに接続するDAOクラス
 */
public class DepartmentDAO extends BaseDAO {

	/**
	 *リストに格納
	 */
	private List<Department> dlist = new ArrayList<>();

	/**
	 *@param department_name 部署名
	 *@param machine_name 機械名
	 *@return 部署情報を返す
	 *@throws SQLException データベース接続処理でエラー
	 *選択した部署名から部署情報を取得するメソッド
	 */
	public Department findDepartment(String department_name,String machine_name) throws SQLException{

		//初期化
		Department department = null;

		//データベースから部署情報を取得するSQL文
		String sql ="select * from departments where department_name = ? and machine_name = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		//部署名
		ps.setString(1,department_name);
		//機械名
		ps.setString(2,machine_name);

		//SQL文の実行
		rs = ps.executeQuery();

		if(rs.next()){

			//データベースから取得した値をセット
			department = new Department();

			//部署名
			department.setDepartment_Name(rs.getString("department_name"));
			//部署ID
			department.setDepartment_Id(rs.getString("department_id"));
			//機械名
			department.setMachine_Name(rs.getString("machine_name"));
			//部署別工数合計時間
			department.setDepartment_Hours(rs.getInt("department_hours"));
		}
		return department;
	}

	/**
	 *@param machine_name 機械名
	 *@return List<Department> 部署一覧
	 *@throws SQLException データベース接続処理でエラー
	 *選択した機械名から部署情報を取得するメソッド
	 */
	public List<Department> getDepartment(String machine_name) throws SQLException{

		//データベースから部署情報を取得するSQL文
		String sql ="select distinct department_id,department_name,machine_name from departments where machine_name = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		//機械名
		ps.setString(1,machine_name);

		//SQL文の実行
		rs = ps.executeQuery();

		while(rs.next()){

			//データベースから取得した値をセット
			Department department = new Department();

			//部署ID
			department.setDepartment_Id(rs.getString("department_id"));
			//部署名
			department.setDepartment_Name(rs.getString("department_name"));
			//機械名
			department.setMachine_Name(rs.getString("machine_name"));

			dlist.add(department);
		}
		return dlist;
	}

	/**
	 *@param department_id 部署ID
	 *@param department_name 部署名
	 *@param machine_name 機械名
	 *@return 部署テーブルに追加登録出来たらtrue,出来なかったらfalse
	 *@throws SQLException データベース接続処理でエラー
	 *部署テーブルに機械情報を登録するメソッド
	 */
	public boolean addDepartment(String department_id,String department_name,String machine_name) throws SQLException{

		//オートコミットの無効
		con.setAutoCommit(false);

		//追加登録判定
		boolean addJudge = false;

		//データベースに機械情報を登録するSQL文
		String sql ="insert into departments (department_id,department_name,machine_name) values (?,?,?)";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		//部署ID
		ps.setString(1,department_id);
		//部署名
		ps.setString(2,department_name);
		//機械名
		ps.setString(3,machine_name);

		//SQL文の実行
		int ad = ps.executeUpdate();

		//追加登録成功したらコミットする
		if(ad > 0){
			addJudge = true;
			con.commit();
		}
		return addJudge;
	}

	/**
	 *@return List<Department> 部署一覧
	 *@throws SQLException データベース接続処理でエラー
	 *部署を一覧表示するメソッド
	 */
	public List<Department> showAllDepartment() throws SQLException{

		//データベースから全部署情報を取得するSQL文
		String sql ="select distinct department_id,department_name from departments order by department_id";
		ps = con.prepareStatement(sql);

		//SQL文の実行
		rs = ps.executeQuery();

		while(rs.next()){

			//データベースから取得した値をセット
			Department department = new Department();

			//部署名の取得
			department.setDepartment_Name(rs.getString("department_name"));
			//部署IDの取得
			department.setDepartment_Id(rs.getString("department_id"));

			dlist.add(department);
		}
		return dlist;
	}

	/**
	 *@param report 工数記録
	 *@param department 部署
	 *@return 計算した部署別工数合計時間を返す
	 *@throws SQLException データベース接続処理でエラー
	 *部署別工数合計時間を計算するメソッド
	 */
	public Department sumDepartmentHours(Report report,Department department) throws SQLException{

		//オートコミットの無効
		con.setAutoCommit(false);

		//部署別工数合計時間を計算するSQL文
		String sql = "update departments set department_hours = (select sum(work_time) + sum(over_time) + sum(holiday_work) from reports where department_id = ? and machine_name = ? group by machine_name) where department_id = ? and machine_name = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		//部署ID
		ps.setString(1,report.getDepartment_Id());
		//機械名
		ps.setString(2,report.getMachine_Name());
		//部署ID
		ps.setString(3,department.getDepartment_Id());
		//機械名
		ps.setString(4,department.getMachine_Name());

		//SQL文の実行
		int sdh = ps.executeUpdate();

		//計算したらコミットする
		if(sdh > 0){
			con.commit();
		}
		return department;
	}

	/**
	 *@param department 部署
	 *@return 部署別工数合計時間を返す
	 *@throws SQLException データベース接続処理でエラー
	 *部署別工数合計時間を取得するメソッド
	 */
	public Department getDepartmentHours(Department department) throws SQLException{

		//データベースから更新したデータを取得するSQL文
		String sql ="select * from departments where department_id = ? and  machine_name = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		//部署ID
		ps.setString(1,department.getDepartment_Id());
		//機械名
		ps.setString(2,department.getMachine_Name());

		//SQL文の実行
		rs = ps.executeQuery();

		if(rs.next()){

			//部署ID
			department.setDepartment_Id(rs.getString("department_id"));
			//部署名
			department.setDepartment_Name(rs.getString("department_name"));
			//機械名
			department.setMachine_Name(rs.getString("machine_name"));
			//部署別工数合計時間の取得
			department.setDepartment_Hours(rs.getInt("department_hours"));

		}
		return department;
	}
}