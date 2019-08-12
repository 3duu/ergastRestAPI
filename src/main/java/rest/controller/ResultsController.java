package rest.controller;

import static rest.controller.Constants.RESULTS;
import static rest.controller.Constants.SEASON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.model.RaceTable;
import rest.model.Races;
import rest.model.Result;

@RestController
@RequestMapping(RESULTS)
public class ResultsController extends ErgastController<RaceTable> {
	
	private static final Logger logger = LoggerFactory.getLogger(ResultsController.class);
	
	@GetMapping(value="/races", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Races[]> getRaces(@RequestParam(SEASON) String season) {
		
    	ResponseEntity<Races[]> response = null;
    	try {
    		if(validateSeason(season)) {
    			final Result result = getResult(season);
    			response = new ResponseEntity<Races[]>(result.getMrData().getRaceTable().getRaces(), HttpStatus.OK);
    		}
    		else {
    			response = new ResponseEntity<Races[]>(HttpStatus.BAD_REQUEST);
    		}
    	}
    	catch(Exception e) {
    		response = new ResponseEntity<Races[]>(handleError(e));
    	}
    	
		return response;
    }
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
}
