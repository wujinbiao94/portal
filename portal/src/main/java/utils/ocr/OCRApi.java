package utils.ocr;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

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

    private static OCRApi instance = new OCRApi();

    private OCRApi(){}

    public static OCRApi getInstance(){
        return instance;
    }

    public JSONObject getCarInfo(String picturePath){
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        /*是否检测多张车牌，默认为false，当置为true的时候可以对一张图片内的多张车牌进行识别*/
        options.put("multi_detect", "false");

        JSONObject res = client.plateLicense(picturePath, options);
        System.out.println(res.toString(2));
        return  res;
    }

}
