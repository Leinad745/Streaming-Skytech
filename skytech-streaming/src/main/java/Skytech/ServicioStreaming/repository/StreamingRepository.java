package Skytech.ServicioStreaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import Skytech.ServicioStreaming.model.Streaming;
import java.util.List;


@Repository
public interface StreamingRepository extends JpaRepository<Streaming, Long>{
    public Streaming findByIdStreaming(Long idStreaming);
    
    @Query(value = "SELECT * FROM streaming WHERE idJuego = ?1", nativeQuery = true)
    public List<Streaming> encuentraTodosLosStreamsPorJuego(Long idJuego);

    @Query(value = "SELECT * FROM streaming WHERE idUsuario = ?1", nativeQuery = true)
    public Streaming encuentraElStreamDeUsuario(Long idUsuario);
} 
