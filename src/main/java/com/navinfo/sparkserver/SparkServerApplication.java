package com.navinfo.sparkserver;

import com.navinfo.sparkserver.thread.TestThreade;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SparkServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SparkServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		getTestThreade().start();
	}

	@Bean
	public TestThreade getTestThreade(){
		return new TestThreade("Thead-applicationMessageThead");
	}
//	@Bean
//	public DingTalkAlarmThead getDingTalkAlarmThead(){
//		return new DingTalkAlarmThead("Thead-dingTalkAlarm",5*60000l);
//	}
}

