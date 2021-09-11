package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *@author Akihiro Nakamura
 *データベースへ接続するクラス
 */
public class ConnectionManager {

	/**
	 *Javaを使用してHerokuのClearDBに接続
	 */
	private static final String dsn = java.lang.System.getenv("CLEARDB_DATABASE_URL");

	/**
	 *特定のデータベースとの接続
	 */
	private Connection con;

	/**
	 *SQL文の解析と実行
	 */
	private PreparedStatement ps;

	/**
	 *SQL実行結果の取得
	 */
	private ResultSet rs;

	/**
	 *@return conを返す
	 *@throws SQLException データベース接続処理でエラー
	 *データベースへ接続するメソッド
	 */
	public Connection connect() throws SQLException{

		//初期化
		con = null;

		try{
			//データベース接続準備
			Class.forName("com.mysql.cj.jdbc.Driver");

			//データベース接続情報
			con = DriverManager.getConnection("jdbc:" + dsn);

		//Class.forName()で例外発生
		}catch(ClassNotFoundException e){
			e.printStackTrace();

		//getConnection()で例外発生
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 *@param con データベース接続
	 *@throws SQLException データベース接続処理でエラー
	 *データベース切断メソッド
	 */
	public void close(Connection con) throws SQLException{

		//データベースとの接続を切断
		try{
			if(con != null)
				con.close();

			if(ps != null)
				ps.close();

			if(rs != null)
				rs.close();

		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}