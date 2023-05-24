package com.example.primeraPrac.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.primeraPrac.models.Usuario;
import com.example.primeraPrac.service.UsuarioService;

@RestController
@RequestMapping("api")
public class ApiDemo {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/saludo")
	public String saludo() {
		return "Hola mundo";
	}
	
	@GetMapping("/all")
	public ArrayList<Usuario> getAllUser() {
		return usuarioService.getAllUsers();
	}
	
	@GetMapping("/find/{id}")
	public Optional<Usuario> getUserById(@PathVariable("id") long id) {
		return usuarioService.getUserById(id);
	}
	
	@PostMapping("/save")
	public Usuario saveUser(@RequestBody Usuario u) {
		return usuarioService.saveUser(u);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUserById(@PathVariable("id") long id) {
		if (usuarioService.deleteById(id)) {
			return "Se eliminó correctamente el usuario {id}";
		} else {
			return "No se ha podido eliminar dicho usuario";
		}
	}
}
