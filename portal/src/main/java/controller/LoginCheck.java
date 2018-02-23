package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.LogCheckService;
import service.impl.LogCheckServiceImpl;

/**
 * Created by 你敬爱的彪哥 on 2018/2/23.
 */
public class LoginCheck {

    private static final Logger logger = LoggerFactory.getLogger(LoginCheck.class);
    private LogCheckService logCheckService = new LogCheckServiceImpl();
    /**
     * 帐号登陆
     * @param userName
     * @param password
     * @return true登陆成功
     */
    public String logCheck(String userName, String password){
        String result = "";
        try {
            result = logCheckService.logCheck(userName,password);
        } catch (Exception e){
            logger.error("登陆出错！",e);
            result = "登陆出错！";
        }
        return result;
    }
}
