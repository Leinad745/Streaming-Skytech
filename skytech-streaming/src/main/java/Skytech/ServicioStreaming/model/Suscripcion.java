package Skytech.ServicioStreaming.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.sql.Time;

@Entity
@Table(name = "suscripcion")
@Data // Genera automáticamente los métodos getter y setter, así como equals(), hashCode() y toString()
@NoArgsConstructor
@AllArgsConstructor

public class Suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSuscripcion;

    @Column(unique = true, nullable = false)
    private String nombreSuscripcion;

    @Column(nullable = false)
    private Time horasJuego;

    @Column(nullable = false)
    private Float precioSuscripcion;
}
