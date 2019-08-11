package rest;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import rest.model.Result;


@SpringBootApplication
public class Application implements ApplicationRunner  {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	private static String URL = "https://ergast.com/api/f1/2017/last/results.json";
	private final static String URI ="http://ergast.com/api/f1/";
	private final static String JSON_FILE ="results.json";
	
	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
    public void run(ApplicationArguments args) throws Exception {
        
    	final String[] params = args.getSourceArgs();
    	logger.info("Application started with command-line arguments: {}", Arrays.toString(params));
    	
    	try {
    		logger.info(getResult().getMrData().toString());
    	}
    	catch(Exception e) {
    		handleError(e);
    	}
    	HttpServerErrorException e;
    }
    
    private Result getResult() {
    	final ResponseEntity<Result> rateResponse =
    	        restTemplate.exchange(URL,
	                    HttpMethod.GET, null, new ParameterizedTypeReference<Result>() {
	            });
    	return rateResponse.getBody();
    }
    
    private void handleError(Exception e) {

    	if(e instanceof HttpStatusCodeException) {
    		if(e instanceof HttpClientErrorException) {
    			logger.error("Não há resultados para os parametros de entrada");
        	}
    		else if(e instanceof HttpServerErrorException) {
    			logger.error("Houve um erro ao executar a operação");
        	}
    	}
    	else {
    		logger.error("Erro inesperado");
    	}
    }
}
