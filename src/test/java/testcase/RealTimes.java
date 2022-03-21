package testcase;

import WebSocket.Client;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import common.EndPoint;
import common.ExpectedResult;
import common.RealTimesResponseModel;
import org.java_websocket.enums.ReadyState;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URI;

public class RealTimes extends BaseTest {
    Client c;

    @DataProvider(name="RealTimes")
    public Object[][] getData() throws IOException {
        //read data from excel
        String path = System.getProperty("user.dir") + "/src/test/java/resources/RealTimesData.xlsx";
        int rowNum = common.XLUtils.getRowCount(path,"Sheet1");
        int colNum = common.XLUtils.getCellCount(path,"sheet1",1);
        String[][] testData = new String [rowNum][colNum];

        for(int i=1;i<=rowNum;i++){
            for(int j=0;j<colNum;j++){
                testData[i-1][j] = common.XLUtils.getCellData(path,"Sheet1",i,j);
            }
        }
        return testData;
    }

    @BeforeClass
    public void startConnection(){
        try {
            c = new Client(new URI(EndPoint.SPOT_TESTNET_PUBLIC_TOPIC));
            c.connect();
            while (!c.getReadyState().equals(ReadyState.OPEN)) {
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @AfterClass
    public void closeConnection(){
        try{
            c.close();
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection(){
        ExtentTest extentTest = extentReports.createTest("RealTime testConnection","to verify RealTime Connection");
        try {
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(c.httpStatus, ExpectedResult.EXPECTED_HTTP_STATUS, "Http Status is not expected");
            softAssert.assertEquals(c.httpStatusMessage, ExpectedResult.EXPECTED_HTTP_STATUS_MESSAGE, "Http Status is not expected");
            softAssert.assertAll();
        }catch (Exception e){
            e.printStackTrace();
            extentTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(dataProvider = "RealTimes")
    public void testMessage(String symbol, String topic, String event, String binary){
        ExtentTest extentTest = extentReports.createTest("RealTime testMessage","to verify RealTime Message");
        try{
            String input = "{\"symbol\" : \"" + symbol + "\",\n"
                    + "\"topic\" : \"" + topic + "\",\n"
                    + "\"event\" : \"" + event + "\",\n"
                    + "\"params\" : {\"binary\": \"" + binary + "\"}"
                    + "}";
            c.send(input);
            Thread.sleep(3000);
            System.out.println(c.responseMessage.get(c.responseMessage.size()-1));

            Gson g = new Gson();
            RealTimesResponseModel res = g.fromJson(c.responseMessage.get(c.responseMessage.size()-1), RealTimesResponseModel.class);

            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(res.getSymbol(),symbol,"Symbol is incorrect");
            softAssert.assertEquals(res.getSymbolName(),symbol,"SymbolName is incorrect");
            softAssert.assertEquals(res.getTopic(),topic,"Topic is incorrect");
            softAssert.assertTrue(!res.getData().get(0).getT().isEmpty(),"Timestamp is empty");
            softAssert.assertEquals(res.getData().get(0).getS(),symbol,"Trading Pair is incorrect");
            softAssert.assertEquals(res.getData().get(0).getSn(),symbol,"Trading Pair is incorrect");
            softAssert.assertTrue(!res.getData().get(0).getC().isEmpty(),"ClosePrice is empty");
            softAssert.assertTrue(!res.getData().get(0).getH().isEmpty(),"High Price is empty");
            softAssert.assertTrue(!res.getData().get(0).getL().isEmpty(),"Low Price is empty");
            softAssert.assertTrue(!res.getData().get(0).getO().isEmpty(),"Open Price is empty");
            softAssert.assertTrue(!res.getData().get(0).getV().isEmpty(),"Trading Volume is empty");
            softAssert.assertTrue(!res.getData().get(0).getQv().isEmpty(),"Trading quote volume is empty");
            softAssert.assertTrue(!res.getData().get(0).getM().isEmpty(),"Change is empty");
            softAssert.assertTrue(!res.getData().get(0).getE().isEmpty(),"Exchange ID is empty");
            softAssert.assertAll();
        }catch (Exception e){
            e.printStackTrace();
            extentTest.log(Status.FAIL, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

}
