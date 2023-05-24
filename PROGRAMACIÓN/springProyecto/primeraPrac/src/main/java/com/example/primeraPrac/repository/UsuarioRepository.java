package com.example.primeraPrac.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.primeraPrac.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

}
