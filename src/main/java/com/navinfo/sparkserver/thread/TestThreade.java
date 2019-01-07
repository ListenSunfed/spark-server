/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.thread
 * Author: wulongyue06158
 * Date: Created in 2019/1/7 19:39
 **************************************/
package com.navinfo.sparkserver.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/*************************************
 * Class Name: TestThreade
 * Description:〈〉
 * @author wulongyue
 * @create 2019/1/7
 * @since 1.0.0
 ************************************/
public class TestThreade extends Thread{
    public TestThreade(String s) {
    }

    @Override
    public void run() {
        super.run();
        while(true){
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("ss").format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
