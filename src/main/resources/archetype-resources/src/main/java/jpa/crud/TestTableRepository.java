package ${package}.jpa.crud;


import ${package}.jpa.models.TestTableEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestTableRepository  extends CrudRepository<TestTableEntity, UUID> {
}