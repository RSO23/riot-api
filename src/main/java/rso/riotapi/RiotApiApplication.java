package rso.riotapi;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan
public class RiotApiApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory.setConnectTimeout(10000);
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RiotApiApplication.class, args);
	}

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshEvent(ContextRefreshedEvent contextRefreshedEvent) {
		ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
		MDC.put("applicationName", applicationContext.getId());
	}

}
