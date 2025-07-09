/*
 * This file is part of spring-boot-archetype-3x.
 *
 * Copyright (C) 2025 FixBox
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package ${package}.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ${package}.jpa.crud.TestTableRepository;
import ${package}.jpa.models.TestTableEntity;
import ${package}.dto.request.TestTableDto;
import ${package}.dto.response.TestTableResponseDto;
import ${package}.services.interfaces.ServiceAppl;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

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
        TestTableResponseDto testTableResponseDtoById = getTestTableResponseDtoById(UUID.fromString(testTableDto.getId()));
        if(Objects.nonNull(testTableResponseDtoById)) {
            TestTableResponseDto dupliTestTableResponseDto = mapper.map(testTableDto, TestTableResponseDto.class);
            dupliTestTableResponseDto.setMessaggeError("Duplicate Key : ".concat(testTableDto.getId()));
            return dupliTestTableResponseDto;
        }
        TestTableEntity entitySave = testTableRepository.save(mapper.map(testTableDto, TestTableEntity.class));
        return mapper.map(entitySave, TestTableResponseDto.class);
    }

    @Override
    public TestTableResponseDto getTestTableResponseDtoById(UUID id) {
        return testTableRepository.findById(id)
                .map(testTableEntity -> mapper.map(testTableEntity, TestTableResponseDto.class))
                .orElse(null);
    }
}