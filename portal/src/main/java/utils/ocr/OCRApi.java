package utils.ocr;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by 你敬爱的彪哥 on 2018/3/25.
 * 车牌识别工具类
 */
public class OCRApi {
    //设置APPID/AK/SK
    private static final String APP_ID = "10992519";
    private static final String API_KEY = "Cfg5S2Yu6Qe98cM4lPmirBmc";
    private static final String SECRET_KEY = "4HPeeBRbn9Nsu7Fqju8xbKPHhoG8qN4i";

    private static OCRApi instance ;
    private static final Logger logger = LoggerFactory.getLogger(OCRApi.class);
    private OCRApi(){}

    public static OCRApi getInstance(){
        if (instance == null){
            return new OCRApi();
        }
        return instance;
    }

    /**
     * 传入的是图片路径
     * @param picturePath
     * @return
     */
    public JSONObject getCarInfo(String picturePath){
        logger.info("请求车牌信息！路径="+picturePath);
        // 初始化一个AipOcr
        JSONObject res = new JSONObject();
        try {
            AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

            // 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);

            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<String, String>();
        /*是否检测多张车牌，默认为false，当置为true的时候可以对一张图片内的多张车牌进行识别*/
            options.put("multi_detect", "false");

            res = client.plateLicense(picturePath, options);
            logger.info("查询到的车牌信息=" + res);
        } catch (Exception e){
            logger.error("查询车牌信息失败！",e);
        }
        return  res;
    }

    /**
     * 图片二进制数组
     * @param files
     * @return
     */
    public JSONObject getCarInfo(byte[] files){
        logger.info("查询车牌信息！输入参数为二进制数组");
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        /*是否检测多张车牌，默认为false，当置为true的时候可以对一张图片内的多张车牌进行识别*/
        options.put("multi_detect", "false");
        JSONObject res = client.plateLicense(files, options);
        logger.info("查询到的车牌信息="+res);
        return res;
    }

}
