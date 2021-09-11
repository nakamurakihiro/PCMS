
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