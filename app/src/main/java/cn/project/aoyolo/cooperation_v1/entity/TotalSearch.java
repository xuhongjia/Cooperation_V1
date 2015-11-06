package cn.project.aoyolo.cooperation_v1.entity;

/**
 * 综合排序
 * 成交量等大的综合搜索实体
 * @author Hy
 *
 */
public class TotalSearch
{
	/**
	 * 搜索的条件
	 * @author Myy
	 *
	 */
	public enum ConditionType
	{
	
		VOLUME,//成交量优先
		EVALUATIONNUMBER,//评价数最多
		LASTED,//服务最新或者需求最新
		LOWPRICE,//价格从低到高
		HIGHTPRICE;//价格从高到低
	}
	/**
	 * 搜索的实体类别
	 * @author Myy
	 *
	 */
	public enum EntityType
	{
		GOODS,
		RECRUITING,
		TRAIN,
		RENTHOUSE,
		HOMEMAKING,
		GROUPBUYING;
	}
	
	private ConditionType conditionType;
	private EntityType entityType;
	private long searchTime;//仅搜索符合截止日期在搜索 时间后面的额记录
	private int index;//当前请求数据的最后一条记录的下标
	private int flag;//0表示服务1表示需求
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public ConditionType getConditionType() {
		return conditionType;
	}
	public void setConditionType(ConditionType conditionType) {
		this.conditionType = conditionType;
	}
	public EntityType getEntityType() {
		return entityType;
	}
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
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
}
