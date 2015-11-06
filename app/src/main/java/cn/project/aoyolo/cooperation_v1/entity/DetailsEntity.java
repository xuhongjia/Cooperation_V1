package cn.project.aoyolo.cooperation_v1.entity;

public class DetailsEntity 
{
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
	private EntityType type; //请求详情的类别
	public EntityType getType() {
		return type;
	}
	public void setType(EntityType type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int id;//该服务或需求的id
}
