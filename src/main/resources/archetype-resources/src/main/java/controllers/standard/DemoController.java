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
package ${package}.controllers.standard;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import  ${package}.jpa.models.TestTableEntity;
import  ${package}.services.interfaces.ServiceAppl;
import  ${package}.dto.request.TestTableDto;
import ${package}.dto.response.TestTableResponseDto;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(name = "ApiV1", value = "/api/standard/V1")
public class DemoController {


    @Autowired
    ServiceAppl serviceAppl;


    @Operation(summary = "Test Hello" , description = "Only a Test")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Error"),
            @ApiResponse(responseCode = "404", description = "not found")
    })
    @GetMapping("/sayhello")
    public ResponseEntity<String> sayHello() {
        ResponseEntity<String> responseEntity =  new  ResponseEntity<String>(serviceAppl.getHello(), HttpStatus.OK );
        return responseEntity;
    }

    @Operation(summary = "Test Insert TestTable in DB" , description = "Test Insert TestTable in DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Error"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/save")
    public ResponseEntity<TestTableResponseDto> saveTestTable (@RequestBody TestTableDto testTableDto){
        return new  ResponseEntity<TestTableResponseDto>(serviceAppl.addTestTableDto(testTableDto), HttpStatus.OK );
    }
}
