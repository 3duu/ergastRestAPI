package rest.controller;

import static rest.controller.Constants.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CONSTRUCTORS)
public class ConstructorsController extends ErgastController {
	
	private static final Logger logger = LoggerFactory.getLogger(ConstructorsController.class);

	@Override
	protected Logger getLogger() {
		return logger;
	}
}
