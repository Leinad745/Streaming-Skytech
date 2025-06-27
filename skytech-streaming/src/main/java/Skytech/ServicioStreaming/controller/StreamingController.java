package Skytech.ServicioStreaming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Skytech.ServicioStreaming.service.StreamingService;
import Skytech.ServicioStreaming.model.Streaming;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v3/streaming")
public class StreamingController {

    @Autowired
    private StreamingService streamingService;

    // METODOS GET

    @GetMapping
    public ResponseEntity<List<Streaming>> listarStreamings() {
        List<Streaming> streamings = streamingService.findAll();
        if (streamings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(streamings);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Streaming> buscarPorId(@PathVariable Long id) {
        try {
            Streaming streaming = streamingService.findById(id);
            return ResponseEntity.ok(streaming);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{idUsuario}")
    public ResponseEntity<Streaming> buscarPoridUsuario(@PathVariable("idUsuario") Long id) {
        try {
            Streaming streaming = streamingService.findByIdUsuario(id);
            if (streaming.equals(null)) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(streaming);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/juego/{idJuego}/streams")
    public ResponseEntity<List<Streaming>> buscarPorIdJuego(@PathVariable("idJuego") Long id) {
        try {
            List<Streaming> streamings = streamingService.findByIdJuego(id);
            if (streamings.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(streamings);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // METODOS POST

    @PostMapping
    public ResponseEntity<Streaming> guardarStreaming(@RequestBody Streaming streaming) {
        try {
            Streaming nuevoStreaming = streamingService.save(streaming);
            return ResponseEntity.status(201).body(nuevoStreaming);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // METODOS DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarStreaming(@PathVariable Long id) {
        try {
            streamingService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
