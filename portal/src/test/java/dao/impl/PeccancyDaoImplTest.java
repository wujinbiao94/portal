package dao.impl;

import dao.PeccancyDao;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by 你敬爱的彪哥 on 2018/5/13.
 */
public class PeccancyDaoImplTest {
    @Test
    public void queryPeccancy() throws Exception {

        List<Map<String,Object>> re = peccancyDao.queryPeccancy("吴金彪");
        System.out.println(JSONObject.valueToString(re));
    }

    private PeccancyDao peccancyDao = new PeccancyDaoImpl();
    @Test
    public void insertPeccancyInfo() throws Exception {
        Map<String,Object> data = new HashedMap();
        data.put("peccancy_place","北京市朝阳区");
        data.put("peccancy_date","2018-05-13 8:32:09");
        data.put("ID_num",4);
        data.put("peccancy_type","肇事逃逸");
        data.put("dealPersion","警员001");
        peccancyDao.insertPeccancyInfo(data);
    }

}