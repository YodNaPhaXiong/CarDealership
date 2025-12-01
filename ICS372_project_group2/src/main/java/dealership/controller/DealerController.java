package dealership.controller;

import dealership.entities.dealer.Dealer;
import dealership.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dealer")
public class DealerController {
    @Autowired
    DealerService dealerService;

    public DealerController(DealerService dealerService){
        this.dealerService = dealerService;
    }

    @GetMapping("/all")
    public List<Dealer> all(){
        return dealerService.findAll();
    }


}
