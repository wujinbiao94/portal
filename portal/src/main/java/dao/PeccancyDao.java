package dao;

import java.util.List;
import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/5/6.
 */
public interface PeccancyDao {
    /**
     * 插入违章信息
     * @param data 插入信息
     */
    int insertPeccancyInfo(Map<String, Object> data);

    /**
     * 查询违章信息
     * @param paramData
     * @return
     */
    List<Map<String,Object>> queryPeccancy(String paramData);
}
