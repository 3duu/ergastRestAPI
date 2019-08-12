package rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import rest.model.RaceTable;
import rest.model.Races;
import rest.model.Result;

@RequestMapping("/")
@RestController
public class ErgastController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErgastController.class);
	private final static String URI ="http://ergast.com/api/f1/";
	private final static String JSON_FILE ="/last/results.json";
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Result getResult(String season) {
    	final ResponseEntity<Result> rateResponse =
    	        restTemplate.exchange(URI.concat(season).concat(JSON_FILE),
	                    HttpMethod.GET, null, new ParameterizedTypeReference<Result>() {
	            });
    	return rateResponse.getBody();
    }
    
    private HttpStatus handleError(Exception e) {

		if(e instanceof HttpClientErrorException) {
			logger.error("Não há resultados para os parametros de entrada");
			return HttpStatus.NOT_FOUND;
    	}
		else{
			logger.error("Houve um erro ao executar a operação");
			return HttpStatus.SERVICE_UNAVAILABLE;
    	}
    }
    
	@GetMapping(value="/racetable", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RaceTable> getRaceTable(@RequestParam("season") String season) {
		
    	ResponseEntity<RaceTable> raceTable = null;
    	try {
    		if(StringUtils.isNumeric(season)) {
    			raceTable = new ResponseEntity<RaceTable>(getResult(season).getMrData().getRaceTable(), HttpStatus.OK);
    		}
    		else {
    			raceTable = new ResponseEntity<RaceTable>(HttpStatus.BAD_REQUEST);
    		}
    	}
    	catch(Exception e) {
    		raceTable = new ResponseEntity<RaceTable>(handleError(e));
    	}
    	
		return raceTable;
    }
	
	@GetMapping(value="/races", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Races[]> getRaces(@RequestParam("season") String season) {
		
    	ResponseEntity<Races[]> races = null;
    	try {
    		if(StringUtils.isNumeric(season)) {
    			races = new ResponseEntity<Races[]>(getResult(season).getMrData().getRaceTable().getRaces(), HttpStatus.OK);
    		}
    		else {
    			races = new ResponseEntity<Races[]>(HttpStatus.BAD_REQUEST);
    		}
    	}
    	catch(Exception e) {
    		races = new ResponseEntity<Races[]>(handleError(e));
    	}
    	
		return races;
    }
}
