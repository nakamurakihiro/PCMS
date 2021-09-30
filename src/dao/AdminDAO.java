package dao;

import java.sql.SQLException;

import dto.Admin;

/**
 *@author Akihiro Nakamura
 *管理者テーブルに接続するDAOクラス
 */
public class AdminDAO extends BaseDAO {

	/**
	 *@param email_address メールアドレス
	 *@param admin_password 管理者パスワード
	 *@return 管理者情報を返す
	 *@throws SQLException データベース接続処理でエラー
	 *管理者ログインメソッド
	 */
	public Admin loginAdmin(String email_address,String admin_password) throws SQLException{

		//初期化
		Admin admin = null;

		//データベースから管理者情報を取得するSQL文
		String sql ="select * from admins where email_address = ? and admin_password = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		ps.setString(1,email_address);
		ps.setString(2,admin_password);

		//SQL文の実行
		rs = ps.executeQuery();

		if(rs.next()){

			//データベースから取得した値をセット
			admin = new Admin();

			//社員ID
			admin.setEmployee_Id(rs.getInt("employee_id"));
			//メールアドレス
			admin.setEmail_Address(rs.getString("email_address"));
			//姓
			admin.setLast_Name(rs.getString("last_name"));
			//名
			admin.setFirst_Name(rs.getString("first_name"));
			//管理者パスワード
			admin.setAdmin_Password(rs.getString("admin_password"));
		}
		return admin;
	}

	/**
	 *@return 管理者パスワードを返す
	 *@throws SQLException データベース接続処理でエラー
	 *管理者パスワードを取得するメソッド
	 */
	public Admin getAdminPassword() throws SQLException{

		//初期化
		Admin admin = null;

		//データベースから管理者パスワードをを取得するSQL文
		String sql ="select data from db_admin_password";
		ps = con.prepareStatement(sql);

		//SQL文の実行
		rs = ps.executeQuery();

		if(rs.next()){

			//データベースから取得した値をセット
			admin = new Admin();

			//管理者パスワード
			admin.setAdmin_Password(rs.getString("data"));
		}
		return admin;
	}

	/**
	 *@param employee_id 社員ID
	 *@param last_name 姓
	 *@param first_name 名
	 *@param admin_password 管理者パスワード
	 *@return 管理者権限を付与出来たらtrue,出来なかったらfalse
	 *@throws SQLException データベース接続処理でエラー
	 *管理者権限を付与するメソッド
	 */
	public boolean registAdmin(int employee_id,String last_name,String first_name,String admin_password) throws SQLException{

		//オートコミットの無効
		con.setAutoCommit(false);

		//管理者権限付与判定
		boolean registJudge = false;

		//データベースに管理者権限を登録するSQL文
		String sql = "insert into admins (employee_id,last_name,first_name,admin_password) values (?,?,?,?)";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		//社員ID
		ps.setInt(1,employee_id);
		//姓
		ps.setString(2,last_name);
		//名
		ps.setString(3,first_name);
		//管理者パスワード
		ps.setString(4,admin_password);

		//SQL文の実行
		int ra = ps.executeUpdate();

		//登録したらコミットする
		if(ra > 0){
			registJudge = true;
			con.commit();
		}
		return registJudge;
	}

	/**
	 *@param employee_id 社員ID
	 *@return 管理者権限を削除出来たらtrue,出来なかったらfalse
	 *@throws SQLException データベース接続処理でエラー
	 *管理者権限を削除するメソッド
	 */
	public boolean deleteAdmin(int employee_id) throws SQLException{

		//オートコミットの無効
		con.setAutoCommit(false);

		//データベースから指定した管理者を削除するSQL文
		String sql = "delete from admins where employee_id = ?";
		ps = con.prepareStatement(sql);

		//削除判定
		boolean deleteJudge = false;

		//プレースホルダに値をセット
		//管理者ID
		ps.setInt(1,employee_id);

		//SQL文の実行
		int da = ps.executeUpdate();

		//削除したらコミットする
		if(da > 0){
			deleteJudge = true;
			con.commit();
		}
		return deleteJudge;
	}

	/**
	 *@param admin_password 管理者パスワード
	 *@return 管理者パスワードを変更出来たらture,出来なかったらfalse
	 *@throws SQLException データベース接続処理でエラー
	 *管理者パスワード変更メソッド
	 */
	public boolean changeAdminPassword(String admin_password) throws SQLException{

		//オートコミットの無効
		con.setAutoCommit(false);

		//管理者パスワード変更判定
		boolean changeJudge = false;

		//データベースから管理者パスワードを取得するSQL文
		String sql ="update db_admin_password set data = ?";
		ps = con.prepareStatement(sql);

		//プレースホルダに値をセット
		ps.setString(1,admin_password);

		//SQL文の実行
		int cap = ps.executeUpdate();

		//変更したらコミットする
		if(cap > 0){
			changeJudge = true;
			con.commit();
		}
		return changeJudge;
	}

	/**
	 *@return 管理者ゲスト情報を返す
	 *@throws SQLException データベース接続処理でエラー
	 *管理者ゲストログインメソッド
	 */
	public Admin loginGuestAdmin() throws SQLException{

		//初期化
		Admin admin = null;

		//データベースから管理者ゲスト情報を取得するSQL文
		String sql ="select * from admins where employee_id = 0";
		ps = con.prepareStatement(sql);

		//SQL文の実行
		rs = ps.executeQuery();

		if(rs.next()){

			//データベースから取得した値をセット
			admin = new Admin();

			//社員ID
			admin.setEmployee_Id(rs.getInt("employee_id"));
			//姓
			admin.setLast_Name(rs.getString("last_name"));
			//名
			admin.setFirst_Name(rs.getString("first_name"));
			//管理者パスワード
			admin.setAdmin_Password(rs.getString("admin_password"));
		}
		return admin;
	}
}
