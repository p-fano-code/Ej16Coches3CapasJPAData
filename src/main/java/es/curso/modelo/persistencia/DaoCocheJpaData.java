package es.curso.modelo.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.curso.modelo.entidad.Coche;

@Repository
public interface DaoCocheJpaData extends JpaRepository<Coche, Integer>{


}
