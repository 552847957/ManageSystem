//package sql;
//
//import java.sql.ResultSet;
//import java.util.ArrayList;
//
//import OwnerInfo.OwnerInfo;
//
//public class OwnerList extends ArrayList<OwnerInfo>{
//	public boolean addOwner(OwnerInfo oi){
//		for(OwnerInfo o:this){
//			if(o.getHouseNum()==oi.getHouseNum()){
//				return false;
//			}
//		}
//		this.add(oi);
//		return true;
//	}
//	public boolean setOwner(OwnerInfo oi) {
//		for(OwnerInfo o:this){
//			if(o.getHouseNum()==oi.getHouseNum()){
//				this.remove(o);
//				this.add(oi);
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public boolean deleteOwner(String houseNum){
//		for(OwnerInfo o:this){
//			if(o.getHouseNum()==houseNum){
//				this.remove(o);
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public OwnerInfo getOwner(String houseNum) {
//		for(OwnerInfo o:this){
//			if(o.getHouseNum()==houseNum){
//				return o;
//			}
//		}
//		return null;
//	}
//
//	
//	public ResultSet getAll() {
//		
//	}
//}
