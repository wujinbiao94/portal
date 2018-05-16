package service;

import java.util.List;

/**
 * Created by 你敬爱的彪哥 on 2018/5/6.
 */
public interface PeccancyService {

    /**
     * 查询违章信息返回处理的字符串
     * @param paramData
     * @return
     */
    List<String> peccancySearch(String paramData);
}
