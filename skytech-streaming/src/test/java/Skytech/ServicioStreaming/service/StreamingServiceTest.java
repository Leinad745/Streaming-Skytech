package Skytech.ServicioStreaming.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import Skytech.ServicioStreaming.repository.StreamingRepository;
import Skytech.ServicioStreaming.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.sql.Time;
import java.time.Year;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StreamingServiceTest {

    @Mock
    private StreamingRepository streamingRepository;

    @InjectMocks
    private StreamingService streamingService;

    private Rol rol = new Rol(1L, "USUARIO", "Rol de usuario estándar");
        
    private Suscripcion suscripcion = new Suscripcion(1L, "BASICA", Time.valueOf("02:00:00"), 10.99f);

    private Usuario usuario = new Usuario(1L, "jdoe", "John", "Doe", "Alberto", "De la sierra",
         "jdoe@example.com", "ContraseñaSegura123", rol, suscripcion);

    Year anioLanzamiento = Year.of(2017);

    private Juego juego = new Juego(1L, "The Legend of Zelda: Breath of the Wild", "Un juego de aventura en un mundo abierto", 
            "Aventura", anioLanzamiento, "Nintendo EPD", "Nintendo", "E10+", true);

    private Streaming streaming = new Streaming(1L, juego, usuario);

    @Test
    public void testFindAll() {
        when(streamingRepository.findAll()).thenReturn(List.of(streaming));
        List<Streaming> streamings = streamingService.findAll();
        assertNotNull(streamings);
        assertEquals(1, streamings.size());
    }

    @Test
    public void testFindById() {
        when(streamingRepository.findByIdStreaming(1L)).thenReturn(streaming);
        Streaming foundStreaming = streamingService.findById(1L);
        assertNotNull(foundStreaming);
        assertEquals(1L, foundStreaming.getIdStreaming());
    }

    @Test
    public void testFindByIdJuego() {
        when(streamingRepository.encuentraTodosLosStreamsPorJuego(1L)).thenReturn(List.of(streaming));
        List<Streaming> streamings = streamingService.findByIdJuego(1L);
        assertNotNull(streamings);
        assertEquals(1, streamings.size());
    }

    @Test
    public void testFindByIdUsuario() {
        when(streamingRepository.encuentraElStreamDeUsuario(1L)).thenReturn(streaming);
        Streaming foundStreaming = streamingService.findByIdUsuario(1L);
        assertNotNull(foundStreaming);
        assertEquals(1L, foundStreaming.getIdStreaming());

}

    @Test
    public void testSave() {
        when(streamingRepository.save(streaming)).thenReturn(streaming);
        Streaming savedStreaming = streamingService.save(streaming);
        assertNotNull(savedStreaming);
        assertEquals(1L, savedStreaming.getIdStreaming());
    }

    @Test
    public void testDelete() {
        doNothing().when(streamingRepository).deleteById(1L);
        streamingService.delete(1L);
        verify(streamingRepository, times(1)).deleteById(1L);
    }
}