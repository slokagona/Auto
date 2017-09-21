package com.verizon.bmc.testscripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPricingRequest extends TestBaseClass {
                String prReqId = "PR-1584";
                @Test(priority=1)
                public void testPriceRequest() {
                                login("accnt", "1234");
                                validateLoginUser();
                                clickPriceReq();
                                clickCreatePriceRequest();
                                selectSFDCOpp();
                                createPR();
                                createRequest();
                                assignPricing();
                                scrollToTop();
                                prReqId = getPRRequest();
                                System.out.println(prReqId);
                                logout();
                }

                @Test(priority=2)
                public void testValidateRequest() {

                                login("pricing", "1234");
                                activitiesAndWorkflow(prReqId);
                                selectPricingAnalyst();
                                assignToMe();
                                String bc = getBC();
                                bcTextEditor(bc);

                                String currentBC = getCurrentBCVersion();
                                System.out.println(currentBC);
                                expandDemandManagement();
                                expandDemandSetManagement();
                                expandUpdateDemandSet();
                                clickUploadDemandSet();
                                fileUpload();
                                verifyMappings();
                                typeMappingName(bc);
                                clickUploadToDatabase();
                                logout();
                }

                
                public void verifyUploadStatus(String bc){
                                String status = driver.findElement(By.xpath("//table[@id='ctl00_MainPage_RadUpload_GrdUploads_RadGridUI_ctl00']//tbody/tr[contains(@id,'ctl00_MainPage_RadUpload_GrdUploads_RadGridUI')]//td[contains(text(),'"+bc+"')][1]//preceding-sibling::td[3]")).getText();
                                System.out.println(status);
                                Assert.assertEquals(status, "");
                }
                public void expandDemandManagement() {

                                if (driver.findElement(By.xpath("//a[contains(@title,'Demand Management')]")).getAttribute("aria-expanded")
                                                                .contains("false")) {
                                                driver.findElement(
                                                                                By.xpath("//a[contains(@title,'Demand Management')]//span[contains(@class,'nav-label')]")).click();
                                }
                }

                public void expandDemandSetManagement() {

                                if (driver.findElement(By.xpath("//a[contains(@title,'Demand Management')]//following-sibling::ul//li[2]/a"))
                                                              .getAttribute("aria-expanded").contains("false")) {
                                                driver.findElement(By.xpath("//a[contains(@title,'Demand Management')]//following-sibling::ul//li[2]/a"))
                                                                                .click();
                                }
                }

                public void typeMappingName(String bc){
                                driver.findElement(By.id("ctl00_MainPage_RadUpload_txtMapName")).sendKeys(bc);;
                }
                public void expandUpdateDemandSet() {

                                if (driver.findElement(By.xpath("//a[contains(@title,'Demand Management')]//following-sibling::ul//li[2]/a//following-sibling::ul/li[5]/a"))
                                                                .getAttribute("aria-expanded").contains("false")) {
                                                driver.findElement(By.xpath("//a[contains(@title,'Demand Management')]//following-sibling::ul//li[2]/a//following-sibling::ul/li[5]/as"))
                                                                                .click();
                                }
                }
                
                public void clickUploadDemandSet(){
                                driver.findElement(By.xpath("//a[contains(text(),'Upload Demand Set')]")).click();
                }
                
                public void clickUploadToDatabase(){
                                driver.findElement(By.id("ctl00_MainPage_RadUpload_btnUploadToDB")).click();
                }
                public void verifyMappings(){
                                //TO-DO
                }
                public void fileUpload(){
                                //driver.findElement(By.id("ctl00_MainPage_RadUpload_RadUploadFilefile0")).sendKeys("");
                                sleep(15);
                }

                public void activitiesAndWorkflow(String bc) {
                                String stp[ ] = bc.split("-");
                                System.out.println(stp[0] + " " + stp[1]);
                                driver.findElement(By.id("ctl00_lnkRightSidebarToggle")).click();
                                sleep(2);
                                driver.switchTo().frame("IFrmWFActivities");
                                driver.findElement(By.xpath("//ul[@class='sidebar-list']//a[contains(@href,'PR') and contains(@href,'"+stp[1]+"')]")).click();
                                driver.switchTo().defaultContent();
                }

                public void selectPricingAnalyst() {
                                driver.findElement(By.cssSelector("div[username='pricing'] span.rddlFakeInput")).click();
                                sleep(3);
                                driver.findElement(By
                                                                .xpath("//div[@id='ctl00_MainPage_SummaryUI_dfScreenCntrl_ctrl0_drp2890_DropDown']//li[contains(text(),'Pricing Analyst')]"))
                                                                .click();

                }

                public void assignToMe() {
                                driver.findElement(By.xpath("//span[@title='Assign To Me' or @title='Assign To Account'] ")).click();
                }

                public void bcTextEditor(String bc) {
                                sleep(5);
                                driver.findElement(By.cssSelector("#ctl00_CmbBusinessCaseVersion_Input")).sendKeys(bc);
                                sleep(10);
                                driver.findElement(
                                                                By.xpath("//div[@id='ctl00_CmbBusinessCaseVersion_DropDown']//li[contains(text(),'"+bc+"')]"))
                                                                .click();
                }

                public String getCurrentBCVersion() {
                                return driver.findElement(By.cssSelector("#hyperBCVersion span")).getText().trim();
                }

                public String getBC() {
                                return driver
                                                                .findElement(By
                                                                                                .xpath("//a[contains(@id,'ctl00_MainPage_SummaryUI_dfScreenCntrl_ctrl0_RVSTDemandBCVersionId')]"))
                                                                .getText();
                                                }
                //public void downloadPricingRequest(){
                                //driver.findElement(By.xpath(xpathExpression))
                //}

                private void logout() {

                                driver.findElement(By.xpath("//ul[contains(@class,'navbar-top-links')]//li//i[contains(@class,'fa-user')]/.."))
                                                                .click();
                                sleep(1);
                                driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
                }

                public void login(String username, String password) {
                                driver.findElement(By.id("logInBMA_UserName")).sendKeys(username);
                                driver.findElement(By.id("logInBMA_Password")).sendKeys(password);
                                driver.findElement(By.name("logInBMA$LoginButton")).click();
                }

                public void clickPriceReq() {
                                driver.findElement(By.xpath("//a[@title='Price Quote']/span[contains(text(),'Price-Req')]")).click();
                }

                public void clickCreatePriceRequest() {
                                driver.findElement(By.xpath("//a[contains(text(),'Create Pricing Request')]")).click();
                }

                // TO-DO
                public boolean validateLoginUser() {
                                return true;
                }

                public void selectSFDCOpp() {
                                driver.findElement(By.xpath("//a[contains(text(),'SFDC Opportunity')]")).click();
                                driver.findElement(By
                                                                .xpath("//div[contains(@id,'SCPricingReq_RadSearchFilter')]//li//span[contains(text(),'Opportunity Name')]"))
                                                                .click();
                                driver.findElement(By
                                                                .xpath("//input[@id='ctl00_MainPage_SCPricingReq_RadSearchFilter_ctl01_ctl08_CmbSFDCOpprtunityTypeID_Input']"))
                                                                .sendKeys("kee");
                                driver.findElement(By
                                                                .xpath("//div[@id='ctl00_MainPage_SCPricingReq_RadSearchFilter_ctl01_ctl08_CmbSFDCOpprtunityTypeID_DropDown']//li[contains(text(),'KEENE PROMOS INC - CENTREX RENEWAL')]"))
                                                                .click();
                                driver.findElement(By.id("btnGenerateResult")).click();
                }

                public void createPR() {
                                driver.findElement(By.xpath("//a[@title='Create PR']")).click();
                }

                public void createRequest() {

                                // Bid due date
                                driver.findElement(
                                                                By.xpath("//a[@id='ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_drpPRReqBidDueDate_popupButton']"))
                                                                .click();
                                driver.findElement(By
                                                                .xpath("(//table[@id='ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_drpPRReqBidDueDate_calendar_Top']//a[@href='#'])[2]"))
                                                                .click();

                                sleep(3);

                                driver.findElement(
                                                                By.xpath("//a[@id='ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_drpPRReqDueDate_popupButton']"))
                                                                .click();
                                driver.findElement(By
                                                                .xpath("//table[@id='ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_drpPRReqDueDate_calendar_Top']//a[@href='#']"))
                                                                .click();

                                sleep(3);

                                Actions actions = new Actions(driver);
                                WebElement ele = driver
                                                                .findElement(By.xpath("//input[@id='ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_drp4457_Input']"));
                                actions.moveToElement(ele).build().perform();

                                // driver.findElement(By.xpath("//input[@id='ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_drp4457_Input']")).click();
                                driver.findElement(By.xpath("//input[@id='ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_drp4457_Input']"))
                                                                .sendKeys("AgencyName1");
                                sleep(3);
                                driver.findElement(By
                                                                .xpath("//div[@id='ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_drp4457_DropDown']//ul//li[contains(text(),'AgencyName1')]"))
                                                                .click();

                                sleep(3);

                                driver.findElement(By
                                                                .xpath("//div[@id='ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_ATT14885_ddlDocType']//span[@class='rddlFakeInput']"))
                                                                .click();
                                sleep(3);
                                driver.findElement(By.xpath("//li[contains(text(),'RFP / RFQ / RFI')]")).click();

                                WebElement fileEle =                    driver.findElement(By.xpath("//input[@type='file']"));

                                // fileEle.click();

                                actions.sendKeys(fileEle, "C:\\Users\\sankisu\\Downloads\\Demand small.xlsx").build().perform();

                                sleep(10);
                                uploadThroughROBOT("C:\\Users\\sankisu\\Downloads\\Demand small.xlsx");                              
                                if (!waitForElement(By.xpath(
                                                                "//li[contains(@id,'ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_ATT14885') and contains(@class,'ruUploadSuccess')]"))) {
                                                Assert.fail("Upload file failed");
                                }

                                driver.findElement(By.id("ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_ATT14885_RadBtnSubmit")).click();

                                sleep(5);

                                String imageValue = driver
                                                                .findElement(By
                                                                                                .xpath("//tr[contains(@id,'ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_ATT14885_grdDocChkList_ctl00')]//img"))
                                                                .getAttribute("src");
                                System.out.println(imageValue);
                                if (!imageValue.contains("green-accept.png")) {
                                                Assert.fail();
                                }

                                // driver.findElement(By.xpath("//span[@title='Create Request & Assign
                                // To Pricing']")).click();

                }
                
                

                private void uploadThroughROBOT(String filePath) {
                                //driver.findElement(By.xpath("//input[@type='file']")).click();
                                driver.findElement(By.cssSelector(".ruFileWrap")).click();
                                sleep(3);
                                
                                StringSelection stringSelection = new StringSelection(filePath);
                                   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                                   
                                      Robot robot;
                                                try {
                                                                robot = new Robot();
                                                                robot.keyPress(KeyEvent.VK_CONTROL);
                                            robot.keyPress(KeyEvent.VK_V);
                                            robot.keyRelease(KeyEvent.VK_V);
                                            robot.keyRelease(KeyEvent.VK_CONTROL);
                                            sleep(2);
                                            robot.keyPress(KeyEvent.VK_ENTER);
                                            robot.keyRelease(KeyEvent.VK_ENTER);
                                                } catch (AWTException e) {
                                                                // TODO Auto-generated catch block
                                                                e.printStackTrace();
                                                }
                                                
                           
                }

                public void assignPricing() {
                                
                                scrollToTop();                                    
                                WebElement pricing = driver.findElement(By.xpath("//span[@title='Create Request & Assign To Pricing']"));
                                WebDriverWait wait = new WebDriverWait(driver, 30);
                                wait.until(ExpectedConditions.elementToBeClickable(pricing)).click();
                }

                public void scrollToTop() {
                                JavascriptExecutor jse = (JavascriptExecutor) driver;
                                jse.executeScript("window.scrollBy(0,-250)", "");
                
                }
                public String getPRRequest() {
                                return driver
                                                                .findElement(By
                                                                                                .xpath("//table[@class='rgMasterTable']//tbody/tr[@id='ctl00_MainPage_SCRUIPReq_dfScreenCntrl_ctrl0_grd15005_RadGridUI_ctl00__0']/td[@class='boldCss']/a"))
                                                                .getAttribute("title").trim();

                                // driver.findelementby.xpath("//table[@class=rgMasterTable']//tbody
                                // /tr[@id='ct_mainpage_ScruipReq_dfscreenCntrl_ctr10_grd15005_RadGridUI_ct100_0']/td[@class='blood
                }

                public boolean waitForElement(By by) {
                                boolean flag = false;
                                for (int i = 0; i < 2; i++) {
                                                if (driver.findElements(by).size() > 0) {
                                                                flag = true;
                                                                break;
                                                }
                                                sleep(1);
                                }

                                return flag;
                }

}
