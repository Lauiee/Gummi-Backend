package myproject.Gummi.api.repository;

import myproject.Gummi.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    // isSettled 값이 null일 경우 전체 조회, 값이 있으면 그에 따라 조회 + 삭제된 것 제외
    @Query("SELECT p FROM Payment p WHERE p.isDeleted = false AND (:isSettled IS NULL OR p.isSettled = :isSettled)")
    List<Payment> findByIsSettled(@Param("isSettled") Boolean isSettled);
}
