package utils.servlet;

import controller.PlateNumQueryController;
import dao.PeccancyDao;
import dao.QueryCarDao;
import dao.impl.PeccancyDaoImpl;
import dao.impl.QueryCarDaoImpl;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.PeccancyService;
import service.impl.PeccancyServiceImpl;
import service.impl.QueryFactory;
import utils.Enum.Constants;
import utils.Enum.RequestTypeEnum;
import utils.util.DataFormat;
import utils.util.DateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
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

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("post请求！");
        //路径前面带有/add.do
        String urlPath=request.getServletPath();
        System.out.println("请求的路径为："+ urlPath);
        //去除路径的 /
        String methodName = urlPath.substring(1);
        //去掉 .do
        methodName = methodName.substring(0, methodName.length() - 3);
        System.out.println("请求的方法为：" + methodName);

        //QueryFactory queryFactory = new QueryFactory(methodName);
        //查询车辆信息
        String res = "";
        //TODO 更改成工厂模式
        try {
            QueryCarDao queryCarDao = new QueryCarDaoImpl();
            if (Constants.requestCarInfo.equals(methodName)) {
                String carNum = DataFormat.requestParam2String(request.getParameter("carNum"));
                System.out.println("查询参数="+carNum);
                if (!"".equals(carNum)) {
                    Map<String, Object> map = queryCarDao.queryCarInfoById(carNum);
                    res = JSONObject.valueToString(map);
                    System.out.println("查询到的信息=" + res);
                } else {
                    System.out.println("传参为空！");
                    return;
                }
            } else if (Constants.requestByDriverLicence.equals(methodName)){
                String driverLisence = DataFormat.requestParam2String(request.getParameter("driverLisence"));
                System.out.println("查询参数="+driverLisence);

                Map<String, Object> map = queryCarDao.queryByDriverLisence(driverLisence);
                res = JSONObject.valueToString(map);
            } else if (Constants.requestByinsert.equals(methodName)){
                String peccancy_place = DataFormat.requestParam2String(request.getParameter("peccancyPlace"));
                String peccancy_date = DataFormat.requestParam2String(request.getParameter("peccancyDate"));
                String ID_num = DataFormat.requestParam2String(request.getParameter("license"));
                String peccancy_type = DataFormat.requestParam2String(request.getParameter("peccancyType"));
                String dealPersion = DataFormat.requestParam2String(request.getParameter("dealPersion"));
                String carPlateNum = DataFormat.requestParam2String(request.getParameter("carPlateNum"));
                LinkedHashMap<String,Object> data = new LinkedHashMap();
                if (peccancy_date.equals("")){
                    peccancy_date = DateFormat.date2Str(new Date(),null);
                }
                data.put("peccancy_place",peccancy_place);
                data.put("peccancy_date",peccancy_date);
                data.put("ID_num",ID_num);
                data.put("peccancy_type",peccancy_type);
                data.put("dealPersion",dealPersion);
                data.put("carPlateNum",carPlateNum);
                System.out.println("查询参数="+JSONObject.valueToString(data));
                PeccancyDao peccancyDao = new PeccancyDaoImpl();
                int i = peccancyDao.insertPeccancyInfo(data);
                res = JSONObject.valueToString(i);
            } else if (Constants.requestByPeccancy.equals(methodName)){
                PeccancyService peccancyService = new PeccancyServiceImpl();
                String param = DataFormat.requestParam2String(request.getParameter("searchParam"));
                List<String> strings = peccancyService.peccancySearch(param);
                res = JSONObject.valueToString(strings);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter responseWriter = response.getWriter();
            responseWriter.print(res);
            responseWriter.flush();
            responseWriter.close();
        } catch (Exception e){
            System.out.println("返回请求失败！");
            e.printStackTrace();
        }


    }
}
