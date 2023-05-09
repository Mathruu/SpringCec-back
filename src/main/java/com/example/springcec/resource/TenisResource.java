package com.example.springcec.resource;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcec.controller.TenisController;
import com.example.springcec.dominio.Tenis;
import com.example.springcec.repository.TenisRepository;

@RestController
@RequestMapping(value = "/api/tenis")
public class TenisResource {
    
    @Autowired
    private TenisRepository tenisRepository;

    @GetMapping(value = "/list")
    public List<Tenis> list() {
        return tenisRepository.findAll();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Tenis> create(@RequestBody Tenis tenis) {
        TenisController tenisController = new TenisController();
        if (!tenisController.isTenisValido(tenis)) {
            return new ResponseEntity("O modelo do tênis é invalálido!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        tenis = tenisRepository.save(tenis);
        return new ResponseEntity<Tenis>(tenis, HttpStatus.OK);
    }

    @GetMapping("getById/{id}")
    public Optional<Tenis> getById(@PathVariable(value = "id") long id) {
        return tenisRepository.findById(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<Tenis> editar(@RequestBody Tenis tenis) {
        TenisController tenisController = new TenisController();
        if (!tenisController.isTenisValido(tenis)) {
            return new ResponseEntity("O modelo do tênis é invalálido!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        tenis = tenisRepository.save(tenis);
        return new ResponseEntity<Tenis>(tenis, HttpStatus.OK);
    }

    @GetMapping("/total")
    public long total() {
        return tenisRepository.count();
    }

    @DeleteMapping("/remove/{id}")
    public Tenis remove(@PathVariable(value = "id") long id) {
        Optional<Tenis> optionalTenis = tenisRepository.findById(id);
        tenisRepository.delete(optionalTenis.get());
        return optionalTenis.get();
    }
}
