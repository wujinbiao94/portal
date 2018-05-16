package service.impl;

import org.json.JSONObject;
import org.junit.Test;
import service.PeccancyService;

import static org.junit.Assert.*;

/**
 * Created by 你敬爱的彪哥 on 2018/5/16.
 */
public class PeccancyServiceImplTest {

    private PeccancyService peccancyService = new PeccancyServiceImpl();
    @Test
    public void peccancySearch() throws Exception {
        System.out.println(JSONObject.valueToString(peccancyService.peccancySearch("吴金彪")));
    }

}