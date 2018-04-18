package dao.impl;

import dao.PlateNumberDao;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 你敬爱的彪哥 on 2018/4/17.
 */
public class PlateNumberDaoImplTest {
    @Test
    public void queryByPlateNumber() throws Exception {
        PlateNumberDao plateNumberDao = new PlateNumberDaoImpl();
        System.out.println(plateNumberDao.queryByPlateNumber("津8888"));
    }

}