package myproject.Gummi.api.service;

import lombok.RequiredArgsConstructor;
import myproject.Gummi.api.repository.WeightRepository;
import myproject.Gummi.domain.entity.Weight;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeightService {

    private final WeightRepository weightRepository;

    public Weight save(Weight weight) {
        return weightRepository.save(weight);
    }
}
