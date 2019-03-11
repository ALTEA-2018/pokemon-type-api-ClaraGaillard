package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.bo.Translation;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService{

    PokemonTypeServiceImpl(){}

    public PokemonTypeRepository pokemonTypeRepository;

    @Autowired
    public TranslationRepository translationRepository;

    @Autowired
    public PokemonTypeServiceImpl(PokemonTypeRepository pockRepo){
        pokemonTypeRepository = pockRepo;
    }


    @Override
    public PokemonType getPokemonType(int id) {
        PokemonType pokemonType= pokemonTypeRepository.findPokemonTypeById(id);
        String name = translationRepository.getPokemonName(id, Locale.FRENCH);
        pokemonType.setName(name);
        return pokemonType;
    }

    @Override
    public List<PokemonType> getAllPokemonTypes(){
        List<PokemonType> listPokemonType = pokemonTypeRepository.findAllPokemonType();
        List<String> listName = new ArrayList<>();
        for(int i = 0; i < listPokemonType.size(); i++){
             listName.add(translationRepository.getPokemonName(listPokemonType.get(i).getId(), Locale.FRENCH));
        }

        for(int i = 0; i < listName.size(); i++){
            listPokemonType.get(i).setName(listName.get(i));
        }

        return listPokemonType;
    }

    @Override
    public PokemonType getPokemonName(String name){
        return pokemonTypeRepository.findPokemonTypeByName(name);
    }

    @Override
    public void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    @Override
    public void setTranslationRepository(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }
}