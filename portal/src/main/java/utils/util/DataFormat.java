package utils.util;

/**
 * Created by 你敬爱的彪哥 on 2018/5/7.
 * 数据格式转换
 */
public class DataFormat {

    /**
     * 防止中文乱码
     * @param param
     * @return
     */
    public static String requestParam2String(String param) throws Exception{
        if (param == null || "".equals(param)){
            return "";
        }
        return new String(param.getBytes("ISO-8859-1"), "UTF-8");
    }
}
