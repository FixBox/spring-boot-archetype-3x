package ${package}.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ${package}.jpa.crud.TestTableRepository;
import ${package}.jpa.models.TestTableEntity;
import ${package}.dto.request.TestTableDto;
import ${package}.dto.response.TestTableResponseDto;
import ${package}.services.interfaces.ServiceAppl;
import org.springframework.stereotype.Service;

@Service
public class ServiceApplImpl implements ServiceAppl {
    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private TestTableRepository testTableRepository;

    @Override
    public String getHello() {
        return "hello!";
    }

    @Override
    public TestTableResponseDto addTestTableDto(TestTableDto testTableDto) {
        TestTableEntity entitySave = testTableRepository.save(mapper.map(testTableDto, TestTableEntity.class));
        return mapper.map(entitySave, TestTableResponseDto.class);
    }
}