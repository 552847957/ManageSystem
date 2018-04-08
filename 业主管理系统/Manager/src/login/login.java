package login;

import resource.resource;

public class login implements resource {
	public static boolean loginAccount(String account, String password) {
		int wrongTime=3;//登陆可尝试次数
		if (wrongTime>=0&&sqlOp.getAccount().equals(account)
				&& sqlOp.getPassword().equals(password)) {
			return true;
		}else if(wrongTime>=0){//登陆账号密码错误
			wrongTime-=1;
			return false;
		}else{//尝试次数为0
			return false;
		}
	}
}
