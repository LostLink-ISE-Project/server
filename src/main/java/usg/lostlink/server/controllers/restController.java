package usg.lostlink.server.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class restController {

    @GetMapping("/{id}")
	public String GetItem() {
		return "test";
	}


    @GetMapping("/lost-items")
    public String createItem() {
        System.out.println("Item was added to the form");
        return "";
    }
}
