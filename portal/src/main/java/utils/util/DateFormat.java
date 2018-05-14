package utils.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 你敬爱的彪哥 on 2018/5/13.
 */
public class DateFormat {

    private final static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化
     * @param date 时间
     * @param format 格式串
     * @return
     */
    public static String date2Str(Date date, String format) {
        if ("".equals(format) || format == null){
            format = YYYYMMDDHHMMSS;
        }
        Date ss = new Date();
        System.out.println("一般日期输出：" + ss);
        System.out.println("时间戳：" + ss.getTime());
        SimpleDateFormat format0 = new SimpleDateFormat(format);
        String time = format0.format(ss.getTime());//这个就是把时间戳经过处理得到期望格式的时间
        return time;
    }

}
