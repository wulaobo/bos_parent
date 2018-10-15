package Utils;

import com.itheima.bos.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class BosUtils {
    //获取session
    public static HttpSession getSession() {
        return (HttpSession) ServletActionContext.getRequest().getSession();
    }
    //获取session域中的User对象
    public static User getUser() {
        return (User) getSession().getAttribute("loginUser");
    }
}
