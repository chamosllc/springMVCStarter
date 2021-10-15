package spring.mvc.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * エンティティ 保管庫
 */
@Repository
public interface EntityRepository extends JpaRepository<Entity, Integer> {
	List<Entity> findAllByOrderByValueAsc();
}
