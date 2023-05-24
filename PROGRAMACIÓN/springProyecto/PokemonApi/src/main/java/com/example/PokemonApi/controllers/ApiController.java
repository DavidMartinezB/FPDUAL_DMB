package com.example.PokemonApi.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PokemonApi.models.Pokemon;
import com.example.PokemonApi.service.PokemonService;

@RestController
@RequestMapping("pokemondb")
@CrossOrigin(origins = "*")
public class ApiController {
	
	@Autowired
	PokemonService pokemonService;
	
	@GetMapping("/check")
	public String check() {
		return "hola pokemondb";
	}
	
	@GetMapping("/pokemon")
	public ArrayList<Pokemon> showAllPokemon() {
		return pokemonService.showAllPokemon();
	}
	
	@GetMapping("/pokemon/{numero_pokedex}")
	public Optional<Pokemon> showPokemonById(@PathVariable("numero_pokedex") int numero_pokedex) {
		return pokemonService.showPokemonById(numero_pokedex);
	}
	
	@PostMapping("/pokemon/save")
	public Pokemon saveNewPokemon(@RequestBody Pokemon pokemon) {
		return pokemonService.saveNewPokemon(pokemon);
	}
	
	@DeleteMapping("/pokemon/delete/{numero_pokedex}")
	public String deletePokemonById(@PathVariable("numero_pokedex") int numero_pokedex) {
		if (pokemonService.deletePokemonById(numero_pokedex)) {
			return "Se ha eliminado el pokemon correctamente";
		} else {
			return "No se ha podido eliminar dicho Pokemon";
		}
	}
	
	
}
