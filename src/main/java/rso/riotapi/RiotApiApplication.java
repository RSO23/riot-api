package rso.riotapi;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.merakianalytics.orianna.Orianna;

import lombok.RequiredArgsConstructor;
import rso.riotapi.config.ConfigRiotApi;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableSwagger2
@EnableDiscoveryClient
@RequiredArgsConstructor
@EnableAsync
public class RiotApiApplication {

	private final Logger log = LoggerFactory.getLogger(RiotApiApplication.class);
	private final ConfigRiotApi configRiotApi;

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
		Orianna.setRiotAPIKey(configRiotApi.getRiotApiKey());
		log.info("Riot api key changed to: " + configRiotApi.getRiotApiKey());
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("RiotApi");
		executor.initialize();
		return executor;
	}

	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("rso.riotapi"))
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(List.of(apiKey()))
				.apiInfo(apiDetails());
	}

	@Bean
	public UiConfiguration uiConfig()
	{
		return UiConfigurationBuilder.builder()
				.deepLinking(true)
				.operationsSorter(OperationsSorter.METHOD)
				.displayOperationId(false)
				.defaultModelExpandDepth(1)
				.defaultModelsExpandDepth(1)
				.defaultModelRendering(ModelRendering.EXAMPLE)
				.displayRequestDuration(true)
				.docExpansion(DocExpansion.LIST)
				.filter(false)
				.maxDisplayedTags(null)
				.showExtensions(true)
				.tagsSorter(TagsSorter.ALPHA)
				.supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
				.validatorUrl(null)
				.build();
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Riot api API",
				"This is a private API for League of Legends predictor application",
				"1.0",
				"Students license",
				new Contact("Jakob Maležič", "https://github.com/Blarc", "jm6421@student.uni-lj.si"),
				"API License",
				"https://google.com",
				Collections.emptyList()
		);
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

}
