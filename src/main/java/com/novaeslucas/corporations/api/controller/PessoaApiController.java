package com.novaeslucas.corporations.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novaeslucas.corporations.api.model.PaginacaoPessoa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class PessoaApiController implements PessoaApi {

    private static final Logger log = LoggerFactory.getLogger(PessoaApiController.class);

    private final ObjectMapper objectMapperPessoa;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PessoaApiController(ObjectMapper objectMapperPessoa, HttpServletRequest request) {
        this.objectMapperPessoa = objectMapperPessoa;
        this.request = request;
    }

    @Override
    public ResponseEntity<PaginacaoPessoa> getAllPessoas() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PaginacaoPessoa>(objectMapperPessoa.readValue("{  \"pessoas\" : [{\"id\":\"1\",\"nome\":\"pessoa111\"}]}", PaginacaoPessoa.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PaginacaoPessoa>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PaginacaoPessoa>(HttpStatus.NOT_IMPLEMENTED);
    }

//    @Override
//    public ResponseEntity<PaginacaoPessoaFisica> getAllPessoasFisicas() {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<PaginacaoPessoaFisica>(objectMapperPessoa.readValue("{  \"pessoas\" : [ {    \"nome\" : \"pessoa1\",    \"id\" : 0  }, {    \"nome\" : \"pessoa2\",    \"id\" : 1  } ]}", PaginacaoPessoaFisica.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<PaginacaoPessoaFisica>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//
//        return new ResponseEntity<PaginacaoPessoaFisica>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    @Override
//    public ResponseEntity<PaginacaoPessoaJuridica> getAllPessoasJuridicas() {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<PaginacaoPessoaJuridica>(objectMapperPessoa.readValue("{  \"pessoas\" : [ {    \"nome\" : \"pessoa1\",    \"id\" : 0  }, {    \"nome\" : \"pessoa2\",    \"id\" : 1  } ]}", PaginacaoPessoaJuridica.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<PaginacaoPessoaJuridica>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//
//        return new ResponseEntity<PaginacaoPessoaJuridica>(HttpStatus.NOT_IMPLEMENTED);
//    }

}
