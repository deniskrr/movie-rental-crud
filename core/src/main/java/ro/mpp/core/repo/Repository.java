package ro.mpp.core.repo;

import ro.mpp.core.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 */
@NoRepositoryBean
public interface Repository<ID, T extends BaseEntity>
        extends JpaRepository<T, ID> {

}
