package myproject.Gummi.api.repository;

import myproject.Gummi.domain.entity.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {
    @Query("SELECT w FROM Weight w WHERE w.deletedAt = false ORDER BY w.calDate ASC")
    List<Weight> findAllActiveWeightsSortedByDate();
}
