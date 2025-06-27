package Skytech.ServicioStreaming.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;


@Entity
@Table(name = "streaming")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Streaming {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStreaming;

    @JoinColumn(name = "idJuego", nullable = false)
    @ManyToOne
    private Juego juego;

    @JoinColumn(name = "idUsuario", nullable = false)
    @ManyToOne
    private Usuario usuario;
}
