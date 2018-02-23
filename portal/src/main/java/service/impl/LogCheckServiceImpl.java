package service.impl;

import dao.LogCheckDao;
import dao.impl.LogCheckDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.LogCheckService;

/**
 * Created by 你敬爱的彪哥 on 2018/2/23.
 */
public class LogCheckServiceImpl implements LogCheckService {

    private static final Logger logger = LoggerFactory.getLogger(LogCheckServiceImpl.class);
    private LogCheckDao logCheckDao = new LogCheckDaoImpl();

    public String logCheck(String userName,String password){
        logger.info("判断是否存在该用户！用户名：{}，密码：{}",userName,password);
        String passwordCheck = logCheckDao.queryPasswordByUserName(userName);
        if ("".equals(passwordCheck)){
            return "该用户不存在！";
        }
        if (!password.equals(passwordCheck)){
            return "密码错误";
        }
        if (password.equals(passwordCheck)){
            return "true";
        }
        return null;
    }
}
