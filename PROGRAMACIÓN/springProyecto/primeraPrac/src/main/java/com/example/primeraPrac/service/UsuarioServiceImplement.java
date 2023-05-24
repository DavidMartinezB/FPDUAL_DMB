package com.example.primeraPrac.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.primeraPrac.models.Usuario;
import com.example.primeraPrac.repository.UsuarioRepository;

@Service
public class UsuarioServiceImplement implements UsuarioService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public ArrayList<Usuario> getAllUsers() {
		return (ArrayList<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> getUserById(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario saveUser(Usuario u) {
		return usuarioRepository.save(u);
	}

	@Override
	public boolean deleteById(Long id) {
		try {
			Optional<Usuario> u = getUserById(id);
			usuarioRepository.delete(u.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
