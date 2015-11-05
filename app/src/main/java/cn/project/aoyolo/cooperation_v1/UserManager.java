package cn.project.aoyolo.cooperation_v1;

import cn.project.aoyolo.cooperation_v1.entity.User;

/**
 * Created by Myy on 2015/11/4.
 */
public class UserManager {
    private static UserManager _userManager ;
    private User user=null;
    private UserManager(){

    }
    public static UserManager getInstance(){
        if(_userManager==null)
        {
            _userManager=new UserManager();
        }
        return _userManager;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user){
        this.user=user;
    }
    public static boolean isLogin(){
        return getInstance().getUser()!=null;
    }
}
