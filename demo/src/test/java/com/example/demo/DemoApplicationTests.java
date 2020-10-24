package com.example.demo;

import com.example.demo.common.utils.SnowflakeIdWorker;
import com.example.demo.test.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

	@Resource
	TestService testService;

	@Test
	void contextLoads() {
		System.out.println("fine");
	}

	@Test
	void twitterIdGen(){
		SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
		for (int i = 0; i < 1000; i++) {
			long id = idWorker.nextId();
			System.out.println(Long.toBinaryString(id));
			System.out.println(id);
		}
		System.out.println("id:"+SnowflakeIdWorker.generate());
	}

	@Test
	void testPage() throws Exception {
//		testService.getPage(1,20);
		System.out.println(testService.getPage(1,20));
	}

}
