package Skytech.ServicioStreaming.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import Skytech.ServicioStreaming.controller.StreamingControllerV4;
import Skytech.ServicioStreaming.model.Streaming;

@Component
public class StreamingModelAssembler implements RepresentationModelAssembler<Streaming, EntityModel<Streaming>> {

    @Override
    public EntityModel<Streaming> toModel(Streaming streaming) {
        return EntityModel.of(streaming,
                linkTo(methodOn(StreamingControllerV4.class).buscarPorId(streaming.getIdStreaming())).withSelfRel(),
                linkTo(methodOn((StreamingControllerV4.class)).listarStreamings()).withRel("juegos"));
    }

}
