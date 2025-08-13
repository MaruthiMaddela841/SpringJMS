package in.ineuron.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.api.request.InventoryRequest;
import in.ineuron.command.action.InventoryAction;

@Service
public class InventoryService {

	@Autowired
	private InventoryAction action;

	public void addInventory(InventoryRequest request) {
		action.publishToKafka(request, "ADD");
	}

	public void subtractInventory(InventoryRequest request) {
		action.publishToKafka(request, "REMOVE");
	}

}
