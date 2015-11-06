package cn.project.aoyolo.cooperation_v1.entity;

/**
 * 团购
 * @author Hy
 *
 */
public class GroupBuying {
	private Integer id;
	private String name;
	private Float originPrice;
	private Float groupPrice;
	private Long startTime;
	private Long endTime;
	private String extra;
	private String imgs;
	private String addres;

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	private int flag;//0表示服务，1表示需求
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
	public Float getOriginPrice() {
		return originPrice;
	}
	public void setOriginPrice(Float originPrice) {
		this.originPrice = originPrice;
	}
	public Float getGroupPrice() {
		return groupPrice;
	}
	public void setGroupPrice(Float groupPrice) {
		this.groupPrice = groupPrice;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
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
