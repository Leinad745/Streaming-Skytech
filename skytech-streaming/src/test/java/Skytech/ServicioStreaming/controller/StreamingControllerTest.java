package Skytech.ServicioStreaming.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import Skytech.ServicioStreaming.model.*;
import Skytech.ServicioStreaming.service.StreamingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Time;
import java.util.List;
import java.time.Year;

@WebMvcTest(StreamingController.class)

public class StreamingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StreamingService streamingService;

    @Autowired
    private ObjectMapper objectMapper;

    private Streaming streaming;
    private Usuario usuario;
    private Juego juego;
    private Rol rol;
    private Suscripcion suscripcion;

    @BeforeEach
    void setUp() {
        rol = new Rol(1L, "USUARIO", "Rol de usuario estándar");
        
        suscripcion = new Suscripcion(1L, "BASICA", Time.valueOf("02:00:00"), 10.99f);
        
        usuario = new Usuario(1L, "jdoe", "John", "Doe", "Alberto", "De la sierra", "jdoe@example.com", "ContraseñaSegura123", rol, suscripcion);
        juego = new Juego(1L, "The Legend of Zelda: Breath of the Wild", "Un juego de aventura en un mundo abierto", "Aventura", Year.of(2017), "Nintendo EPD", "Nintendo", "E10+", true);
        streaming = new Streaming(1L, juego, usuario);
    }

    @Test
    public void testGetAllStreamings() throws Exception {
        when(streamingService.findAll()).thenReturn(List.of(streaming));
        mockMvc.perform(get("/api/v3/streaming"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idStreaming").value(1))
                .andExpect(jsonPath("$[0].juego.idJuego").value(1))
                .andExpect(jsonPath("$[0].usuario.idUsuario").value(1));

    }

    @Test
    public void testBuscarPorId() throws Exception {
        when(streamingService.findById(1L)).thenReturn(streaming);
        mockMvc.perform(get("/api/v3/streaming/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStreaming").value(1))
                .andExpect(jsonPath("$.juego.idJuego").value(1))
                .andExpect(jsonPath("$.usuario.idUsuario").value(1));
    }

    @Test
    public void testBuscarPorIdUsuario() throws Exception {
        when(streamingService.findByIdUsuario(1L)).thenReturn(streaming);
        mockMvc.perform(get("/api/v3/streaming/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStreaming").value(1))
                .andExpect(jsonPath("$.juego.idJuego").value(1))
                .andExpect(jsonPath("$.usuario.idUsuario").value(1));
    }

    @Test
    public void testBuscarPorIdJuego() throws Exception {
        when(streamingService.findByIdJuego(1L)).thenReturn(List.of(streaming));
        mockMvc.perform(get("/api/v3/streaming/juego/1/streams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idStreaming").value(1))
                .andExpect(jsonPath("$[0].juego.idJuego").value(1))
                .andExpect(jsonPath("$[0].usuario.idUsuario").value(1));
    }

    public void testGuardarStreaming() throws Exception {
        when(streamingService.save(any(Streaming.class))).thenReturn(streaming);
        mockMvc.perform(post("/api/v3/streaming")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(streaming)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idStreaming").value(1))
                .andExpect(jsonPath("$.juego.idJuego").value(1))
                .andExpect(jsonPath("$.usuario.idUsuario").value(1));
    }

    @Test
    public void testEliminarStreaming() throws Exception {
        doNothing().when(streamingService).delete(1L);
        mockMvc.perform(delete("/api/v3/streaming/1"))
                .andExpect(status().isNoContent());
        verify(streamingService, times(1)).delete(1L);
}

}