package myproject.Gummi.api.controller;

import lombok.RequiredArgsConstructor;
import myproject.Gummi.api.service.WeightService;
import myproject.Gummi.domain.dto.request.WeightSaveRequest;
import myproject.Gummi.domain.entity.Weight;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/weights")
public class WeightController {

    private final WeightService weightService;

    @PostMapping
    public ResponseEntity<Weight> saveWeight(@RequestBody WeightSaveRequest weightSaveRequest){
       Weight newWeight= weightService.save(Weight.fromDto(weightSaveRequest));
       return new ResponseEntity<>(newWeight, HttpStatus.CREATED);
    }

}
