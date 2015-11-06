package cn.project.aoyolo.cooperation_v1.entity;

/**
 * 所有实体的公共类
 * @author Myy
 *
 */
public class Common 
{
	public int id;//在common中的id
	public int tId;//当前服务在它所处类别的id，和Type组成主键
	public String name;//名称
	public float price;//价格，薪水...
	public String address;//服务或交易地点...
	public long startTime;//发布时间
	public long endTime;//截止时间
	public int uId;//用户Id
	public int volume;//成交量
	public float longtitute;//经度
	public float latitute;//纬度
	public int flag;//0服务，1需求
	public int evaluationNumber;//评价数
	public int type;//类别   0:Goods,1:GroupBuying,2:HomeMalking,3:Recruiting,4:RentHouse,5:Train
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public float getLongtitute() {
		return longtitute;
	}
	public void setLongtitute(float longtitute) {
		this.longtitute = longtitute;
	}
	public float getLatitute() {
		return latitute;
	}
	public void setLatitute(float latitute) {
		this.latitute = latitute;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getEvaluationNumber() {
		return evaluationNumber;
	}
	public void setEvaluationNumber(int evaluationNumber) {
		this.evaluationNumber = evaluationNumber;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
