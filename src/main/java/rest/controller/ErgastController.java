package rest.controller;

import static rest.controller.Constants.CURRENT;
import static rest.controller.Constants.JSON_FILE;
import static rest.controller.Constants.ROUND;
import static rest.controller.Constants.ROUNDS;
import static rest.controller.Constants.SEASON;
import static rest.controller.Constants.URI;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import rest.model.MRData;
import rest.model.Result;

public abstract class ErgastController<T> {
	
	@Autowired
	private RestTemplate restTemplate;
	
	protected final String getServiceName() {
		
		final RequestMapping requestMappings = getClass().getAnnotation(RequestMapping.class);
		if(requestMappings != null && requestMappings.value().length > 0) {
			return "/".concat(requestMappings.value()[0]);
		}
		return StringUtils.EMPTY;
	}
	
	@SuppressWarnings({ "unchecked"})
	private T getTable(Object bean) {
	  try {
		  
		  PropertyDescriptor[] properties = Introspector.getBeanInfo(bean.getClass(), Object.class)
          .getPropertyDescriptors();
		  
		  for(PropertyDescriptor pd : properties){
			  
			  Method method = pd.getReadMethod();
			  if(method.getReturnType().getTypeName().equals(((ParameterizedType) getClass()
                      .getGenericSuperclass()).getActualTypeArguments()[0].getTypeName())){
				  return (T) method.invoke(bean);
              }
		  }
		  
	  } catch (Exception e) {
	    
	  }
	  return null;
	}
	
	@GetMapping(value="/", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> get(@RequestParam(value=SEASON, required=false) String season, @RequestParam(value=ROUND, required=false) String round) {
		
    	ResponseEntity<T> response = null;
    	if(season == null){
    		season = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    	}
    	if(round == null){
    		round = ROUNDS[1];
    	}
    	try {
    		if(validateSeason(season) && validateRound(round)) {
    			
				final MRData data = getResult(season).getMrData();
    			T table = getTable(data);
    			if(table != null) {
    				response = new ResponseEntity<T>((T)table, HttpStatus.OK);
    			}
    			else {
    				response = new ResponseEntity<T>(HttpStatus.SERVICE_UNAVAILABLE);
    			}
    		}
    		else {
    			response = new ResponseEntity<T>(HttpStatus.BAD_REQUEST);
    		}
    	}
    	catch(Exception e) {
    		response = new ResponseEntity<T>(handleError(e));
    	}
    	
		return response;
    }
	
	protected boolean validateSeason(String season) {
		
		if(!StringUtils.isEmpty(season) && StringUtils.isNumeric(season) || CURRENT.equals(season)) {
			return true;
		}
		else {
			return false;
		}
    }
	
	protected boolean validateRound(String round) {
		
		if(!StringUtils.isEmpty(round) && Arrays.asList(ROUNDS).contains(round)) {
			return true;
		}
		else {
			return false;
		}
    }
	
	protected Result getResult(String season, String round) {
		
		final String url;
		
		if(StringUtils.isEmpty(round)){
			url = URI.concat(season).concat(getServiceName()).concat(JSON_FILE);
		}
		else {
			url = URI.concat(season).concat("/").concat(round).concat(getServiceName()).concat(JSON_FILE);
		}
		
		getLogger().info("Connecting to: ".concat(url));
		
    	final ResponseEntity<Result> rateResponse =
    	        restTemplate.exchange(url,
	                    HttpMethod.GET, null, new ParameterizedTypeReference<Result>() {
	            });
    	return rateResponse.getBody();
    }
	
	protected Result getResult(String season) {
		return getResult(season, ROUNDS[1]);
    }
	
	protected HttpStatus handleError(Exception e) {

		if(e instanceof HttpClientErrorException) {
			getLogger().error("Não há resultados para os parametros de entrada - ".concat(e.getMessage()));
			return HttpStatus.NOT_FOUND;
    	}
		else{
			getLogger().error("Houve um erro ao executar a operação - ".concat(e.getMessage()));
			return HttpStatus.SERVICE_UNAVAILABLE;
    	}
    }
	
	protected abstract Logger getLogger();
}
