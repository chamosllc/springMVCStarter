package spring.mvc.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * エンティティ 保管庫
 */
@Repository
public interface EntityRepository extends JpaRepository<Entity, Integer> {
}
