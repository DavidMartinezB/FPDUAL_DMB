package com.example.PokemonApi.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.PokemonApi.models.Pokemon;

public interface PokemonService {
	
	ArrayList<Pokemon> showAllPokemon();
	Optional<Pokemon> showPokemonById(int numero_pokedex);
	Pokemon saveNewPokemon(Pokemon pokemon);
	boolean deletePokemonById(int numero_pokedex);
	
}
