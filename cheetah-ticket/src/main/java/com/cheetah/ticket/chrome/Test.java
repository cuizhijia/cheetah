package com.cheetah.ticket.chrome;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.logging.log4j.util.Base64Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;

public class Test {



    public static int[] maxSlidingWindow(int[] nums, int k) {

        if(nums.length <= k) {
            return new int[]{maxNum(nums)};
        }
        int[] maxSlidingWindow = new int[nums.length-k+1];
        int a = 0;
        for (int j = 0; j < nums.length-k+1; j++) {
            int[] windowNums = new int[k];
            int b = j;
            for (int i = 0; i < windowNums.length; i++) {
                windowNums[i] = nums[b++];
            }

            maxSlidingWindow[a++] = maxNum(windowNums);
        }

        return maxSlidingWindow;
    }


    private static int maxNum(int[] nums) {
        return IntStream.of(nums).max().orElse(0);
    }

    /**
     * 故宫账号：
     * 旅行社：北京福游国际旅行社有限公司
     * 登录账号：13681107606
     * 密码：FY+123456
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        maxSlidingWindow(nums,3);
        
        System.setProperty("webdriver.chrome.driver", "/Users/cuizhijia/Documents/soft/chrome-mac-x64/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://usermg.dpm.org.cn/usercenter/login");

        WebElement tel = driver.findElement(By.xpath("//form/div[1]/div/div/input"));tel.sendKeys("13681107606");

        TimeUnit.SECONDS.sleep(1);

        WebElement password = driver.findElement(By.xpath("//form/div[2]/div/div/input"));password.sendKeys("FY+123456");

        WebElement codeImg = driver.findElement(By.xpath("//form/div[3]/div/div/img"));

        String imageBase64 = codeImg.getAttribute("src").split(";")[1].split(",")[1];

        String codeVal = imageToText(imageBase64);


        WebElement code = driver.findElement(By.xpath("//form/div[3]/div/div/div/input"));code.sendKeys(codeVal);

        WebElement submit = driver.findElement(By.xpath("//form/button/span"));submit.click();
        driver.manage().logs();
//        driver.get("https://lotsmg.dpm.org.cn/tyApi/zybprod/market/saleable/page?currentPage=1&itemsPerPage=50&ownerCorpCode=GGBWY&productType=1&marketType=1&playDate=2024-11-18&productName=&scenicId=242");

//        driver.manage().logs().get("performance").getAll().toString();
        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        LocalStorage localStorage = webStorage.getLocalStorage();
        SessionStorage sessionStorage = webStorage.getSessionStorage();

        System.out.println("localStorage = " + localStorage);
        //body/div/div/div/div[2]/div/div/div/div/ul/li/span

//        driver.close();

    }

    private static String imageToText(String base64) {
        return "1";
//        Map<String,String> body = new HashMap<>();
//        body.put("image", base64);
//        HttpRequest request = HttpRequest.post("https://api.acedata.cloud/captcha/recognition/image2text")
//                .header("accept", "application/json")
//                .header("authorization", "Bearer 8db7aab3238a4864aa4c36601eb21773")
//                .header("content-type", "application/json")
//                .body(JSONUtil.toJsonStr(body));
//
//        String res = request.execute().body();
//        System.out.println("res = " + res);
//        Object text = JSONUtil.parse(res).getByPath("text");
//        if(Objects.nonNull(text)) {
//            return text.toString();
//        }
//        throw new RuntimeException("获取验证码异常");
    }



}
