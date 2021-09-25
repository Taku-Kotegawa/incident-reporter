package com.example.incidentapi.incident.presentation.controller;

import com.example.incidentapi.common.datatables.DataTablesInput;
import com.example.incidentapi.incident.application.service.IncidentService;
import com.example.incidentapi.incident.domain.model.Incident;
import com.example.incidentapi.incident.domain.model.IncidentAuthority;
import com.example.incidentapi.incident.domain.model.Incidents;
import com.example.incidentapi.incident.presentation.dto.IncidentResource;
import com.github.dozermapper.core.Mapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api(value = "値", tags = "インシデント")
@RequestMapping("api/incidents")
@RestController
@AllArgsConstructor
public class IncidentRestController {

    private final IncidentService incidentService;
    private final Mapper beanMapper;
    private final IncidentAuthority authority;

    /**
     * 検索(一覧の取得)
     */
//    @ApiOperation("検索(一覧の取得)")
//    @ApiParam(value = "api_param")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    List<IncidentResource> list() {

        authority.hasAuthority("list");

        DataTablesInput input = new DataTablesInput();
        input.setLength(Integer.MAX_VALUE);
        return new Incidents(incidentService.findPageByInput(input).toList(), beanMapper).getResources().toList();
    }

    private IncidentResource toResource(Incident incident) {
        return beanMapper.map(incident, IncidentResource.class);
    }

    private Incident toEntity(IncidentResource incident) {
        return beanMapper.map(incident, Incident.class);
    }


    /**
     * 参照
     */
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    IncidentResource view(@PathVariable("id") Long id) {
        authority.hasAuthority("view");
        return toResource(incidentService.findById(id));
    }

    /**
     * 新規登録
     */
    @PostMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    IncidentResource create(@RequestBody @Validated IncidentResource resource) {
        authority.hasAuthority("create");
        return toResource(incidentService.save(toEntity(resource)));
    }

    /**
     * 変更(全項目)
     */
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    IncidentResource update(@RequestBody @Validated IncidentResource resource) {
        authority.hasAuthority("update");
        return toResource(incidentService.save(toEntity(resource)));
    }

    /**
     * 変更(一部)
     */

    /**
     * 削除
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") Long id) {
        authority.hasAuthority("delete");
        incidentService.delete(id);
    }


    /**
     * メール送信
     */

    /**
     * メール送信履歴の取得
     */

    /**
     * 変更履歴の取得
     */


}
