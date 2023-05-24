package com.example.primeraPrac.service;

import java.util.ArrayList;
import java.util.Optional;
import com.example.primeraPrac.models.Usuario;

public interface UsuarioService {
	
	ArrayList<Usuario> getAllUsers();
	Optional<Usuario> getUserById(Long id);
	Usuario saveUser(Usuario u);
	boolean deleteById(Long id);
}
