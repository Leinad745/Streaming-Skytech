package Skytech.ServicioStreaming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.*;
import Skytech.ServicioStreaming.service.StreamingService;
import Skytech.ServicioStreaming.assembler.StreamingModelAssembler;
import Skytech.ServicioStreaming.model.Streaming;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v4/streaming")
public class StreamingControllerV4 {

    @Autowired
    private StreamingService streamingService;

    @Autowired
    private StreamingModelAssembler assembler;

    // METODOS GET

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Streaming>> listarStreamings() {
        List<EntityModel<Streaming>> streamings = streamingService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(streamings,
                linkTo(methodOn(StreamingControllerV4.class).listarStreamings()).withSelfRel());
    }
    
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Streaming> buscarPorId(@PathVariable Long id) {
            Streaming streaming = streamingService.findById(id);
            return assembler.toModel(streaming);
        }
    }
