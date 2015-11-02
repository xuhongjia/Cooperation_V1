package cn.project.aoyolo.cooperation_v1.entity;

/**
 * 租房
 * @author Hy
 *
 */
public class RentHouse {
	private Integer id;
	private String name;
	private String address;
	private String village;
	private String houseType;
	private Float rent;
	private String phone;
	private Float area;
	private String decoration;
	private String orientation; 
	private String extra;
	private String imgs;
	private int flag;//0表示服务，1表示需求
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public Float getRent() {
		return rent;
	}
	public void setRent(Float rent) {
		this.rent = rent;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Float getArea() {
		return area;
	}
	public void setArea(Float area) {
		this.area = area;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
}
