package com.citt.controller;

import com.citt.exceptions.DespachoNotFoundException;
import com.citt.persistence.entity.Despacho;
import com.citt.persistence.services.DespachoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/despachos")
@Tag(name = "Despacho", description = "Controlador para gestionar despachos")
public class DespachoController {

    @Autowired
    private DespachoService despachoService;

    @Operation(summary = "Crear un nuevo despacho")
    @PostMapping
    public ResponseEntity<Despacho> crearDespacho(
            @RequestBody Despacho despacho){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idDespacho}")
                .buildAndExpand(despacho.getIdDespacho())
                .toUri();
        despachoService.saveDespacho(despacho);
        return ResponseEntity.created(location).body(despacho);
    }

    @Operation(summary = "Actualizar un despacho existente")
    @PutMapping("/{idDespacho}")
    public ResponseEntity<Despacho> actualizarDespacho(
            @PathVariable Long idDespacho,
            @Valid @RequestBody Despacho despacho) throws DespachoNotFoundException {
        Despacho despachoActualizado = despachoService.updateDespacho(idDespacho, despacho);
        return ResponseEntity.ok(despachoActualizado);
    }

    @Operation(summary = "Obtener todos los despachos")
    @GetMapping
    public ResponseEntity<List<Despacho>> getAllDespachos() {
        // Mockeado para presentación: evitar dependencia de MySQL
        // return ResponseEntity.ok(despachoService.findAllDespachos());
        return ResponseEntity.ok(List.of(
                new Despacho(101L, java.time.LocalDate.of(2026, 5, 2), "LXHK33", 1, 5001L, "Av. Apoquindo 4501, Las Condes, Santiago", 1250000L, true),
                new Despacho(102L, java.time.LocalDate.of(2026, 5, 6), "JKLZ98", 2, 5002L, "Av. Grecia 320, Ñuñoa, Santiago", 980000L, false),
                new Despacho(103L, java.time.LocalDate.of(2026, 5, 10), "PTAM72", 1, 5003L, "Camino a San Antonio 1205, Maipú, Santiago", 1410000L, false)
        ));
    }


    @Operation(summary = "Obtener un despacho por ID")
    @GetMapping("/{idDespacho}")
    public ResponseEntity<Despacho> obtenerDespacho(
            @PathVariable Long idDespacho) throws DespachoNotFoundException {
        Despacho despacho = despachoService.findById(idDespacho);
        return ResponseEntity.ok(despacho);
    }

    @Operation(summary = "Eliminar un despacho por ID")
    @DeleteMapping("/{idDespacho}")
    public ResponseEntity<Void> eliminarDespacho(@PathVariable Long idDespacho) throws DespachoNotFoundException {
        despachoService.deleteDespacho(idDespacho);
        return ResponseEntity.noContent().build();
    }
}
