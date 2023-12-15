package ${package}.services.interfaces;

import ${package}.dto.request.TestTableDto;
import ${package}.dto.response.TestTableResponseDto;
import ${package}.jpa.models.TestTableEntity;
public interface ServiceAppl {
    String getHello();

    TestTableResponseDto addTestTableDto(TestTableDto testTableDto);
}