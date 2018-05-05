package dao.impl;

import dao.QueryCarDao;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by 你敬爱的彪哥 on 2018/5/6.
 */
public class QueryCarDaoImplTest {
    private QueryCarDao queryCarDao = new QueryCarDaoImpl();
    @Test
    public void queryCarInfoById() throws Exception {
        Map<String, Object> res = queryCarDao.queryCarInfoById("津8888");
        System.out.println(JSONObject.valueToString(res));
        /* for (String key:res.keySet()) {
            System.out.println(key+" = " + res.get(key));
        }*/
    }

}