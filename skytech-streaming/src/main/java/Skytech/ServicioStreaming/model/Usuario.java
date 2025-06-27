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
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

@Entity
@Table(name = "usuario")
@Data // Genera automáticamente los métodos getter y setter, así como equals(), hashCode() y toString()
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(unique = true, nullable = false)
    private String nombreUsuario;

    @Column(nullable = false)
    private String primerNombre;
    @Column(nullable = false)
    private String apellidoPaterno;
    @Column(nullable = false)
    private String segundoNombre;
    @Column(nullable = false)
    private String apellidoMaterno;

    @Column(nullable = false)
    private String correoElectronico;

    @Column(nullable = false, length = 60)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "idRol", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "idSuscripcion", nullable = false)
    private Suscripcion suscripcion;
}
