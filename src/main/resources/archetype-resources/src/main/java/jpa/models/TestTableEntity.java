package ${package}.jpa.models;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Table(name="TEST_TABLE")
@Data
public class TestTableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "DESCRIPTION")
    private String description;
}
