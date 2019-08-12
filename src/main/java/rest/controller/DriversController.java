package rest.controller;

import static rest.controller.Constants.DRIVERS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.model.DriverTable;

@RestController
@RequestMapping(DRIVERS)
public class DriversController extends ErgastController<DriverTable> {
	
	private static final Logger logger = LoggerFactory.getLogger(DriversController.class);
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
}
