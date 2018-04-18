package utils.servlet;

import controller.PlateNumQueryController;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Enum.Constants;
import utils.Enum.RequestTypeEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/4/17.
 */
public class queryServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(queryServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 接收客户端信息
        String res = this.dealRequest(request);
        // 返回信息到客户端
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
        out.close();
    }

    /**
     * 处理请求
     * @param request
     * @return
     */
    private String dealRequest(HttpServletRequest request) throws IOException {
        Map<String, Object> res = new HashedMap();
        Map<String, Object> params = new HashedMap();
        //车牌请求
        if (RequestTypeEnum.PLATE_NUMBER_QUERY.getName().equals(request.getParameter(Constants.requestType))){
            params = this.getParams(request);
            PlateNumQueryController plateNumQueryController= new PlateNumQueryController();
            res = plateNumQueryController.query(request.getParameter(Constants.plateNumberParams));
        }
        return JSONObject.valueToString(res);
    }

    /**
     * 获取请求参数
     * @param request
     * @return
     */
    private Map<String, Object> getParams(HttpServletRequest request) throws IOException {
        Map<String, Object> res = new HashedMap();
        //车牌请求
        if (RequestTypeEnum.PLATE_NUMBER_QUERY.getName().equals(request.getParameter(Constants.requestType))){
            res.put(Constants.plateNumberParams, new String(request.getParameter(Constants.plateNumberParams).getBytes("ISO-8859-1"), "UTF-8"));
        }
        return res;
    }
}
