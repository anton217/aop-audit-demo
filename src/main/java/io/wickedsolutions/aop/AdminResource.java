package io.wickedsolutions.aop;

import io.wickedsolutions.aop.aop.Audit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController("api")
public class AdminResource {

    @Audit(description = "hello world endpoint")
    @RequestMapping(method = GET)
    public ResponseEntity<?> getList() {
        return new ResponseEntity<>("Hello World", OK);
    }

    @Audit(description = "save data endpoint")
    @RequestMapping(method = POST)
    public ResponseEntity<?> saveLocation(@RequestBody String location) {
        System.out.println("Sending Response");
        return new ResponseEntity<>(OK);
    }

}
