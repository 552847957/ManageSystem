package login;

import resource.resource;

public class login implements resource {
	public static boolean loginAccount(String account, String password) {
		int wrongTime=3;//��½�ɳ��Դ���
		if (wrongTime>=0&&sqlOp.getAccount().equals(account)
				&& sqlOp.getPassword().equals(password)) {
			return true;
		}else if(wrongTime>=0){//��½�˺��������
			wrongTime-=1;
			return false;
		}else{//���Դ���Ϊ0
			return false;
		}
	}
}
