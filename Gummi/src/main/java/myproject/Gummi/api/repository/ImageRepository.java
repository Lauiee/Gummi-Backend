package myproject.Gummi.api.repository;

import myproject.Gummi.domain.entity.Image;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findByDeletedAtFalseOrderByShootingDateAsc(Pageable pageable);
}
