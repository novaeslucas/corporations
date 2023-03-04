package com.novaeslucas.corporations.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.novaeslucas.corporations.api.model.Corporation;
import com.novaeslucas.corporations.api.model.CorporationInput;
import com.novaeslucas.corporations.api.model.PaginacaoDeCorporation;
import com.novaeslucas.corporations.api.service.SenderMailService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-02-28T22:10:41.069-03:00")

@RestController
public class CorporationApiController implements CorporationApi {

    private static final Logger log = LoggerFactory.getLogger(CorporationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private SenderMailService mailService;

    @org.springframework.beans.factory.annotation.Autowired
    public CorporationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<PaginacaoDeCorporation> getAllCorporations(@Min(0)@ApiParam(value = "O número da página a ser retornada.", defaultValue = "0") @Valid @RequestParam(value = "page", required = false, defaultValue="0") Integer page,@Min(1) @Max(100) @ApiParam(value = "O tamanho da página a ser retornada.", defaultValue = "20") @Valid @RequestParam(value = "size", required = false, defaultValue="20") Integer size) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PaginacaoDeCorporation>(objectMapper.readValue("{  \"paginaAtual\" : 0,  \"totalDePaginas\" : 6,  \"corporations\" : [ {    \"nome\" : \"nome\",    \"id\" : 0 ,    \"tipo\" : \"Financeiro\"  }, {    \"nome\" : \"nome\",    \"id\" : 0,    \"tipo\" : \"Agro\"  } ]}", PaginacaoDeCorporation.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PaginacaoDeCorporation>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PaginacaoDeCorporation>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Corporation> insertCorporation(@ApiParam(value = "Dados do corporation a ser criado." ,required=true )  @Valid @RequestBody CorporationInput corporation) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if(corporation.getTipo() != null){
                    mailService.enviar("novaeslucas@gmail.com", "teste", "teste de email");
                    return new ResponseEntity<Corporation>(objectMapper.readValue("{  \"nome\" : \"" + corporation.getNome() + "\",  \"tipo\" : \"" + corporation.getTipo() + "\"}", Corporation.class), HttpStatus.NOT_IMPLEMENTED);
                }else{
                    throw new ApiException(500, "Tipo não permitido");
                }
            } catch (IOException | ApiException e) {
                JsonObject jsonReturn = new JsonObject();
                jsonReturn.addProperty("error", true);
                jsonReturn.addProperty("msg", e.getMessage());
                return new ResponseEntity(jsonReturn.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Corporation>(HttpStatus.NOT_IMPLEMENTED);
    }

}
