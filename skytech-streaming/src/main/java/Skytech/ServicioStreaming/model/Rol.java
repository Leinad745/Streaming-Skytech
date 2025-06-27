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

@Entity
@Table(name = "rol")
@Data // Genera automáticamente los métodos getter y setter, así como equals(), hashCode() y toString()
@NoArgsConstructor
@AllArgsConstructor

public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Column(unique = true, nullable = false)
    private String nombreRol;

    @Column(nullable = false)
    private String descripcion;
}
