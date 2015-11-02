package cn.project.aoyolo.cooperation_v1.entity;



import java.util.List;

import com.google.gson.Gson;

/**
 * 通用json格式模型
 * @author HY
 *
 * @param <T>
 */
public class GeneralResponse <T>
{
	private boolean isSuccess;
	private T  data;
	private String msg;



	public boolean isSuccess() {
		return isSuccess;
	}



	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}



	public T getData() {
		return data;
	}



	public void setData(T data) {
		this.data = data;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}


	public GeneralResponse()
	{

	}

	public GeneralResponse (boolean isSuccess,String msg,T data)
	{
		this.data=data;
		this.msg=msg;
		this.isSuccess=isSuccess;
	}

	/**
	 * 返回json数据
	 * @return
	 */
	public String getGsonString()
	{
		Gson gson=new Gson();
		return gson.toJson(this);
	}
	/**
	 * 获得json字符串，应用于增改删操作
	 * @param index
	 * @return
	 */
	public String getGsonString(int index)
	{
		Gson gson=new Gson();
		T data=(T) Integer.valueOf(index);
		this.data=data;
		if(index==0)
		{
			msg="失败";
			isSuccess=false;
		}
		else
		{
			msg="成功";
			isSuccess=true;
		}
		return gson.toJson(this);
	}
	/**
	 * 数组的时候使用
	 * @param list
	 * @return
	 */
	public String getGsonString(T list)
	{
		Gson gson=new Gson();

		this.data=list;
		List<T> a=(List<T>)list;
		if(a.size()>0)
		{
			msg="成功";
			isSuccess=true;
		}
		else
		{
			msg="失败";
			isSuccess=false;
		}
		return gson.toJson(this);
	}
	/**
	 * 根据数据是否为空，加载内容
	 * @param data	数据
	 * @param errorMsg 自定义错误信息
	 * @param successMsg 自定义成功信息
	 * @return
	 */
	public String getGsonString(T data,String errorMsg,String successMsg)
	{
		if(data!=null)
		{
			this.data=data;
			this.isSuccess=true;
			this.msg=successMsg;
		}else
		{
			this.isSuccess=false;
			this.data=null;
			this.msg=errorMsg;
		}
		return new Gson().toJson(this);
	}
	/**
	 * 根据增改返回boolean，加载内容
	 * @param Success 是否成功
	 * @param errorMsg  自定义错误信息
	 * @param successMsg  自定义成功信息
	 * @return
	 */
	public String getGsonString(boolean Success,String errorMsg,String successMsg)
	{
		if(Success)
		{
			this.isSuccess=true;
			this.msg=successMsg;
			this.data=null;
		}
		else
		{
			this.isSuccess=false;
			this.msg=errorMsg;
			this.data=null;
		}
		return new Gson().toJson(this);
	}

}
