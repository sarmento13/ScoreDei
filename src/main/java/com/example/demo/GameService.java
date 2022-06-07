package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.example.data.Game;
import com.example.data.Player;
import com.example.data.Stats;
import com.example.data.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired    
    private GameRepository gameRepository;    
    
    @Autowired    
    private TeamRepository teamRepository;    

 
    public void create_game(Game game)  
    {  
        Game gameCheck = gameRepository.findGameName(game.getEquipaCasa().getName().concat(" vs ").concat(game.getEquipaFora().getName()));
        
        if(gameCheck != null && gameCheck.getData() == game.getData())
            return ;
        
        if(game.getEquipaCasa().getName() == game.getEquipaFora().getName())
            return ;
    

       game.setLocalizacao(game.getEquipaCasa().getLocalizacao());
       game.setNameGame(game.getEquipaCasa().getName().concat(" vs ").concat(game.getEquipaFora().getName()));
       game.getEquipaCasa().setNumGames(1);
       game.getEquipaFora().setNumGames(1);
       game.setStatus("nao iniciado");
       gameRepository.save(game);
    }

    public List<Game> get_all_games()  
    {    
        List<Game> listGames = new ArrayList<Game>();    
        gameRepository.findAll().forEach(listGames::add);    
        return listGames;    
    }
    

    public List<Game> get_all_games_by_order()  
    {    
        List<Game> listGames = new ArrayList<Game>();    
        gameRepository.findByOrder().forEach(listGames::add);  
        
        return listGames;    
    }
    
    public List<Game> get_all_games_init()  
    {    
        List<Game> listGames = new ArrayList<Game>();    
        gameRepository.findByGamesInit("iniciado").forEach(listGames::add);
        
        if(listGames.size() == 0)
            gameRepository.findByGamesInit(null).forEach(listGames :: add);
        
        return listGames;    
    }


    public GameRepository getGameRepository() {
        return gameRepository;
    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    
    public List<Game> teamvsTeam(Game g){
        List<Game> listGames = new ArrayList<Game>();    

        gameRepository.findGameNameList(g.getEquipaCasa().getName().concat(" vs ").concat(g.getEquipaFora().getName())).forEach(listGames :: add);
        gameRepository.findGameNameList(g.getEquipaFora().getName().concat(" vs ").concat(g.getEquipaCasa().getName())).forEach(listGames :: add);

        return listGames;
    }

    public List<Player> list_Players(String name){

        List<Player> list = new ArrayList<Player>();
        
        Game g = gameRepository.findGameName(name);

        for(Player p : g.getEquipaCasa().getListPlayers())
            list.add(p);
        
        for(Player p : g.getEquipaFora().getListPlayers())
            list.add(p);

        return list;
    }

    public Game name_by_game(String name){        
        return gameRepository.findGameName(name);
    }
}
