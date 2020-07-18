package engine.repository;

import engine.model.Completion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CompletionRepository extends PagingAndSortingRepository<Completion, Long> {
    @Query("select c from Completion c where c.user.email = :email")
    Page<Completion> findAllByUserEmail(@Param("email") String email, Pageable pageable);
}
