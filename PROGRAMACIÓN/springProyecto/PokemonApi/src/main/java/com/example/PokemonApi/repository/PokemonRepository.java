package com.example.PokemonApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.PokemonApi.models.Pokemon;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Integer>{
}
