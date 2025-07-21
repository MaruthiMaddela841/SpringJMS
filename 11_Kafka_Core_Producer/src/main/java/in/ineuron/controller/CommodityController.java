package in.ineuron.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ineuron.entity.Commodity;
import in.ineuron.service.CommodityService;

@RestController
@RequestMapping("/api/commodity/v1")
public class CommodityController {
	
	@Autowired
	private CommodityService commodityService;
	
	@GetMapping(value="/all")
	public List<Commodity> generateAllCommodities(){
		return commodityService.generateDummyCommodities();
	}

}
