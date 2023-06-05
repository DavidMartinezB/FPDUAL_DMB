package com.example.PokemonApi.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PokemonApi.models.Pokemon;
import com.example.PokemonApi.repository.PokemonRepository;

@Service
public class PokemonServiceImplement implements PokemonService{

	@Autowired
	PokemonRepository pokemonRepository;
		
	@Override
	public ArrayList<Pokemon> showAllPokemon() {
		return (ArrayList<Pokemon>) pokemonRepository.findAll();
	}      

	@Override
	public Optional<Pokemon> showPokemonById(int numero_pokedex) {
		return pokemonRepository.findById(numero_pokedex);
	}

	@Override
	public Pokemon saveNewPokemon(Pokemon pokemon) {
		return pokemonRepository.save(pokemon);
	}

	@Override
	public boolean deletePokemonById(int numero_pokedex) {
		try {
			Optional<Pokemon> pokemon = showPokemonById(numero_pokedex);
			pokemonRepository.delete(pokemon.get());
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
