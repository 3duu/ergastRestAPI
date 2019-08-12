package rest.controller;

import static rest.controller.Constants.CIRCUITS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.model.CircuitTable;

@RestController
@RequestMapping(CIRCUITS)
public class CircuitsController extends ErgastController<CircuitTable> {
	
	private static final Logger logger = LoggerFactory.getLogger(CircuitsController.class);

	@Override
	protected Logger getLogger() {
		return logger;
	}
}
