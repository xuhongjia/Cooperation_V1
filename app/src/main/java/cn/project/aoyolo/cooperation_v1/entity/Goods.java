package cn.project.aoyolo.cooperation_v1.entity;

/**
 * 商品
 * @author Hy
 *
 */
public class Goods {
	private Integer id;
	private String name;
	private Integer cId;
	private String extra;
	private Float oldPrice;
	private Float newPrice;
	private String businessArea;
	private String phone;
	private String imgs;
	private long publishTime;
	private long stopTime;
	private int uId;
	private int volume;
	private int evaluationNumber;
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
	public int getEvaluationNumber() {
		return evaluationNumber;
	}
	public void setEvaluationNumber(int evaluationNumber) {
		this.evaluationNumber = evaluationNumber;
	}
	public long getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}
	public long getStopTime() {
		return stopTime;
	}
	public void setStopTime(long stopTime) {
		this.stopTime = stopTime;
	}
	private int flag;//0表示服务，1表示需求
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		if(flag>1)
			flag=1;
		this.flag = flag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public Float getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(Float oldPrice) {
		this.oldPrice = oldPrice;
	}
	public Float getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(Float newPrice) {
		this.newPrice = newPrice;
	}
	public String getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
	
	
}
