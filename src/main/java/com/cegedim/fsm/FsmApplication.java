package com.cegedim.fsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FsmApplication {
	public static void main(String[] args) {
		SpringApplication.run(FsmApplication.class, args);
	}

}
//War packaging
//@SpringBootApplication
//public class FsmApplication extends SpringBootServletInitializer{
//	public static void main(String[] args) {
//		SpringApplication.run(FsmApplication.class, args);
//	}
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(BootWarApplication.class);
//    }
//}