package service.impl;

import dao.PeccancyDao;
import dao.impl.PeccancyDaoImpl;
import service.PeccancyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/5/6.
 */
public class PeccancyServiceImpl implements PeccancyService {

    private PeccancyDao peccancyDao = new PeccancyDaoImpl();
    /**
     * 查询违章信息返回处理的字符串
     *
     * @param paramData
     * @return
     */
    public List<String> peccancySearch(String paramData) {
        List<Map<String, Object>> res = peccancyDao.queryPeccancy(paramData);
        String[] fields = {"driver_name","plate_number","peccancy_date","isDeal","peccancy_type"};
        List<String> strings = new ArrayList<String>();
        for (Map<String,Object> map:res) {
            StringBuilder sb = new StringBuilder();
            for (String key:fields) {
                sb.append(map.get(key) == null?"":map.get(key)).append("-");
            }

            strings.add(sb.toString().substring(0,sb.toString().length()-1));
        }
        return strings;
    }
}
