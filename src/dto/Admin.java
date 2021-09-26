package dto;

import java.io.Serializable;

/**
 *@author Akihiro Nakamura
 *管理者クラス
 */

public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *社員ID
	 */
	int employee_id;

	/**
	 *メールアドレス
	 */
	private String email_address;

	/**
	 *姓
	 */
	private String last_name;

	/**
	 *名
	 */
	private String first_name;

	/**
	 *管理者パスワード
	 */
	private String admin_password;

	/**
	 *コンストラクタ
	 */
	public Admin(){}

	/**
	 *@return employee_id - 社員ID
	 *社員IDを返す
	 */
	public int getEmployee_Id(){
		return employee_id;
	}

	/**
	 *@param employee_id - 社員ID
	 *社員IDのセット
	 */
	public void setEmployee_Id(int employee_id){
		this.employee_id = employee_id;
	}

	/**
	 *@return email_address - メールアドレス
	 *メールアドレスを返す
	 */
	public String getEmail_Address(){
		return email_address;
	}

	/**
	 *@param email_address - メールアドレス
	 *メールアドレスのセット
	 */
	public void seｔEmail_Address(String email_address){
		this.email_address = email_address;
	}

	/**
	 *@return last_name - 姓
	 *姓を返す
	 */
	public String getLast_Name(){
		return last_name;
	}

	/**
	 *@param last_name - 姓
	 *姓のセット
	 */
	public void setLast_Name(String last_name){
		this.last_name = last_name;
	}

	/**
	 *@return first_name - 名
	 *名を返す
	 */
	public String getFirst_Name(){
		return first_name;
	}

	/**
	 *@param first_name - 名
	 *名のセット
	 */
	public void setFirst_Name(String first_name){
		this.first_name = first_name;
	}

	/**
	 *@return admin_password - 管理者パスワード
	 *管理者パスワードを返す
	 */
	public String getAdmin_Password(){
		return admin_password;
	}

	/**
	 *@param admin_password - 管理者パスワード
	 *管理者パスワードのセット
	 */
	public void setAdmin_Password(String admin_password){
		this.admin_password = admin_password;
	}
}
