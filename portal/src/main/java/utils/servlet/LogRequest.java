package utils.servlet;

import controller.LoginCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by 你敬爱的彪哥 on 2018/2/11.
 */
public class LogRequest extends HttpServlet {
    private static final long serialVersionUID = 369840050351775312L;

    private static final Logger logger = LoggerFactory.getLogger(LogRequest.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 接收客户端信息
        String username = request.getParameter("username");
        username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        String password = request.getParameter("password");
        logger.info("用户名：{}，密码：{}", username,password);

        // 后台处理
        LoginCheck loginCheck= new LoginCheck();
        String res = loginCheck.logCheck(username,password);
        logger.info("登录结果：{}",res);
        // 返回信息到客户端
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
        out.close();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
