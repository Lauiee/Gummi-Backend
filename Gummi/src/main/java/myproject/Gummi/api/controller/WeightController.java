package myproject.Gummi.api.controller;

import lombok.RequiredArgsConstructor;
import myproject.Gummi.api.service.WeightService;
import myproject.Gummi.domain.dto.request.WeightSaveRequest;
import myproject.Gummi.domain.entity.Weight;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/weights")
public class WeightController {

    private final WeightService weightService;

    // 몸무게 측정 결과 기록
    @PostMapping
    public ResponseEntity<Weight> saveWeight(@RequestBody WeightSaveRequest weightSaveRequest){
       Weight newWeight= weightService.save(Weight.fromDto(weightSaveRequest));
       return new ResponseEntity<>(newWeight, HttpStatus.CREATED);
    }

    // 몸무게 전체 추이 조회
    @GetMapping
    public ResponseEntity<List<Weight>> getWeights(){
        List<Weight> weightList= weightService.getWeights();
        return new ResponseEntity<>(weightList, HttpStatus.OK);
    }

    // 특정 몸무게 기록 삭제
    @DeleteMapping("/{weightId}")
    public ResponseEntity<String> deleteImage(@PathVariable("weightId") Long weightId){
        weightService.deleteWeight(weightId);
        return ResponseEntity.ok("몸무게 삭제에 성공하였습니다.");
    }
}
