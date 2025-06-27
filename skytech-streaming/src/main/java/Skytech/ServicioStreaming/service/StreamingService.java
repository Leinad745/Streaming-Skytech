package Skytech.ServicioStreaming.service;

import Skytech.ServicioStreaming.repository.StreamingRepository;
import Skytech.ServicioStreaming.model.Streaming;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class StreamingService {
    @Autowired
    private StreamingRepository streamingRepository;

    public List<Streaming> findAll() {
        return streamingRepository.findAll();
    }

    public Streaming findById(Long id) {
        return streamingRepository.findByIdStreaming(id);
    }

    public List<Streaming> findByIdJuego(Long idJuego) {
        return streamingRepository.encuentraTodosLosStreamsPorJuego(idJuego);
    }

    public Streaming findByIdUsuario(Long idUsuario) {
        return streamingRepository.encuentraElStreamDeUsuario(idUsuario);
    }

    public Streaming save(Streaming streaming) {
        return streamingRepository.save(streaming);
    }

    public void delete(Long id) {
        streamingRepository.deleteById(id);
    }
}
