package cn.project.aoyolo.cooperation_v1.entity;

public class DistanceEntity 
{
	private String address;
	private int index;//当前需要查询的记录数的最后一条，必须以20条为倍数，0-19则index=20,20-39则index=40;
	private int flag;//0表示服务，1表示需求
	private long searchTime;//搜索时间
	public int type;//类别   0:Goods,1:GroupBuying,2:HomeMalking,3:Recruiting,4:RentHouse,5:Train,其他为综合类
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getSearchTime() {
		return searchTime;
	}
	public void setSearchTime(long searchTime) {
		this.searchTime = searchTime;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
