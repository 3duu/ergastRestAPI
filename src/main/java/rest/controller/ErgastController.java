package rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import rest.model.RaceTable;
import rest.model.Result;

@RequestMapping("/")
@RestController
public class ErgastController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErgastController.class);
	private static String URL = "https://ergast.com/api/f1/2017/last/results.json";
	private final static String URI ="http://ergast.com/api/f1/";
	private final static String JSON_FILE ="results.json";
	
	@Autowired
	private RestTemplate restTemplate;
	
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
    
    public RaceTable getRaceTable() {
    	Result result = null;
    	try {
    		result = getResult();
    	}
    	catch(Exception e) {
    		handleError(e);
    	}
    	
    	return result != null ? result.getMrData().getRaceTable() : new RaceTable();
    }
}
