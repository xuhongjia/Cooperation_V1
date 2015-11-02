package cn.project.aoyolo.cooperation_v1.entity;

/**
 * 搜索实体
 * @author Hy
 *
 */
public class Search 
{
	private String word;//关键字 
	private float lowPrice;//最低价格
	private float hightPrice;//最高价格
	private String place;//地点
	private int flag;//搜索类型
	private long searchTime;//搜索时间，用于判断是否在截至日期之前
	private SearchType type;//搜索类型
	private int index;//当前请求的最后一条记录的下标
	public enum SearchType
	{
		GOODS,
		RECRUITING,
		TRAIN,
		RENTHOUSE,
		HOMEMAKING,
		GROUPBUYING;
	}

	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public float getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(float lowPrice) {
		this.lowPrice = lowPrice;
	}
	public float getHightPrice() {
		return hightPrice;
	}
	public void setHightPrice(float hightPrice) {
		this.hightPrice = hightPrice;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public long getSearchTime() {
		return searchTime;
	}
	public void setSearchTime(long searchTime) {
		this.searchTime = searchTime;
	}
	public SearchType getType() {
		return type;
	}
	public void setType(SearchType type) {
		this.type = type;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
