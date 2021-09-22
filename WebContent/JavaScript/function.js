
/*削除確認*/
function Delete_Check(){
	var deleted = confirm("削除してもよろしいですか？");
		if (deleted == true) {
			return true;
		}else{
			return false;
		}
	}

/*工数記録保存確認*/
function Save_Check(){
	var saved = confirm("工数記録を保存してもよろしいですか？");
		if (saved == true) {
			return true;
		}else{
			return false;
		}
	}

/*管理者権限削除確認*/
function Admin_Delete_Check(){
	var admin_deleted = confirm("管理者権限が失われます。n削除してもよろしいですか？");
		if (admin_deleted == true) {
			return true;
		}else{
			return false;
		}
	}

/*ログアウト確認*/
function Logout_Check(){
	var logouted = confirm("ログアウトしてもよろしいですか？");
		if (logouted == true) {
			return true;
		}else{
			return false;
		}
	}

/*パスワード変更確認*/
function Password_Change_Check(){
	var  password_changed = confirm("新しいパスワードに変更します。n変更してもよろしいですか？");
		if (password_changed == true) {
			return true;
		}else{
			return false;
		}
	}