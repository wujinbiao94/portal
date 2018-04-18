package controller;

import dao.PlateNumberDao;
import dao.impl.PlateNumberDaoImpl;

import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/4/17.
 */
public class PlateNumQueryController {
    private PlateNumberDao plateNumberDao = new PlateNumberDaoImpl();

    public Map<String,Object> query(String plateNum){
        return plateNumberDao.queryByPlateNumber(plateNum);
    }
}
