package utils.ocr;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 你敬爱的彪哥 on 2018/3/25.
 */
public class OCRApiTest {
    OCRApi ocrApi = OCRApi.getInstance() ;
    @Test
    public void getCarInfo() throws Exception {
        ocrApi.getCarInfo("D:/car/timg.jpg");
    }

}