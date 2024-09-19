package myproject.Gummi.api.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import myproject.Gummi.api.repository.WeightRepository;
import myproject.Gummi.domain.entity.Weight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeightService {

    private final WeightRepository weightRepository;

    @Transactional
    public Weight save(Weight weight) {
        return weightRepository.save(weight);
    }

    public List<Weight> getWeights() {
        return weightRepository.findAllActiveWeightsSortedByDate();
    }

    @Transactional
    public void deleteWeight(Long weightId) {
        Weight deletedWeight = weightRepository.findById(weightId).orElseThrow(()
                -> new EntityNotFoundException("Weight not found with id: " + weightId));
        deletedWeight.setDeletedAt(true);
        weightRepository.save(deletedWeight);
    }

}
