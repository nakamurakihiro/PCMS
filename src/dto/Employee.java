package dto;

import java.io.Serializable;

/**
 *@author Akihiro Nakamura
 *社員クラス
 **/

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 *社員ID
	 */
	private int employee_id;

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
	 *姓フリガナ
	 */
	private String last_kana;

	/**
	 *名フリガナ
	 */
	private String first_kana;

	/**
	 *部署名
	 */
	private String department_name;

	/**
	 *部署ID
	 */
	private String department_id;

	/**
	 *血液型
	 */
	private String blood;

	/**
	 *年齢
	 */
	private int age;

	/**
	 *性別
	 */
	private String gender;

	/**
	 *生年月日
	 */
	private String birthday;

	/**
	 *社員パスワード
	 */
	private String employee_password;

	/**
	 *コンストラクタ
	 */
	public Employee(){}

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
	 *@return last_kana - 姓フリガナ
	 *姓フリガナを返す
	 */
	public String getLast_Kana(){
		return last_kana;
	}

	/**
	 *@param last_kana - 姓フリガナ
	 *姓フリガナを返す
	 */
	public void setLast_Kana(String last_kana){
		this.last_kana = last_kana;
	}

	/**
	 *@return first_kana - 名フリガナ
	 *名フリガナを返す
	 */
	public String getFirst_Kana(){
		return first_kana;
	}

	/**
	 *@param first_kana - 名フリガナ
	 *名フリガナを返す
	 */
	public void setFirst_Kana(String first_kana){
		this. first_kana = first_kana;
	}
	/**
	 *@return department_name - 部署名
	 *部署名を返す
	 */
	public String getDepartment_Name(){
		return department_name;
	}

	/**
	 *@param department_name - 部署名
	 *部署名のセット
	 */
	public void setDepartment_Name(String department_name){
		this.department_name = department_name;
	}

	/**
	 *@return department_id - 部署ID
	 *部署IDを返す
	 */
	public String getDepartment_Id(){
		return department_id;
	}

	/**
	 *@param department_id - 部署ID
	 *部署IDのセット
	 */
	public void setDepartment_Id(String department_id){
		this.department_id = department_id;
	}

	/**
	 *@return blood - 血液型
	 *血液型を返す
	 */
	public String getBlood(){
		return blood;
	}

	/**
	 *@param blood - 血液型
	 *血液型のセット
	 */
	public void setBlood(String blood){
		this.blood = blood;
	}

	/**
	 *@return age - 年齢
	 *年齢を返す
	 */
	public int getAge(){
		return age;
	}

	/**
	 *@param age - 年齢
	 *年齢のセット
	 */
	public void setAge(int age){
		this.age = age;
	}

	/**
	 *@return gender - 性別
	 *性別を返す
	 */
	public String getGender(){
		return gender;
	}

	/**
	 *@param gender - 性別
	 *性別のセット
	 */
	public void setGender(String gender){
		this.gender = gender;
	}

	/**
	 *@return birthday - 生年月日
	 *生年月日を返す
	 */
	public String getBirthday(){
		return birthday;
	}

	/**
	 *@param birthday - 生年月日
	 *生年月日のセット
	 */
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	/**
	 *@return employee_password - 社員パスワード
	 *社員パスワードを返す
	 */
	public String getEmployee_Password(){
		return employee_password;
	}

	/**
	 *@param employee_password - 社員パスワード
	 *社員パスワードのセット
	 */
	public void setEmployee_Password(String employee_password){
		this.employee_password = employee_password;
	}
}
