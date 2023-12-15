package ${package}.dto.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class TestTableDto implements Serializable{
    @Serial
    private static final long serialVersionUID = -1029489657947577146L;
    private String id;
    private String description;
}