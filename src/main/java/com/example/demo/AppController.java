package com.example.demo;


import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.JarException;
import java.util.stream.Collectors;

import javax.persistence.Id;

import com.example.data.Game;
import com.example.data.GameEvent;
import com.example.data.Player;
import com.example.data.PlayerEvent;
import com.example.data.Team;
import com.example.data.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.events.Event.ID;

// docker exec -it scoredei_devcontainer_database_1 psql -U postgres


@Controller
public class AppController {
    @Autowired
    UserService userService;
    
    @Autowired
    TeamService teamService;
    
    @Autowired
    PlayerService playerService;
    
    @Autowired
    GameService gameService;

    @Autowired
    GameEventService gameEventService;

    @Autowired
    PlayerEventService playerEventService;


	@GetMapping("/")
	public String HomePage() {
		return "homepage";
	}
	

    @GetMapping("/getAPI")
	public String getAPI(Model m) throws JsonMappingException, JsonProcessingException {


        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("x-apisports-key", "b67298323b763631cd1c54be771f0464");
        HttpEntity request= new HttpEntity(headers); 

        ResponseEntity<String> result = 
        restTemplate.exchange("https://v3.football.api-sports.io/teams?league=94&season=2021", HttpMethod.GET, request,String.class);
    
        ObjectMapper mapper = new ObjectMapper();
        String str = result.getBody();
        JsonNode rootNode = mapper.readTree(str);

        JsonNode responseNode = rootNode.path("response");
        Iterator<JsonNode> teamNodes = responseNode.elements();


        while(teamNodes.hasNext()){
            JsonNode team = teamNodes.next();
            JsonNode teamName = team.path("team").path("name");
            JsonNode teamLogo = team.path("team").path("logo");
            JsonNode teamLocal = team.path("venue").path("name");

            Team t = new Team(teamName.textValue(), teamLocal.textValue(), teamLogo.textValue());
            
            this.teamService.create_team(t);
        }

        ResponseEntity<String> result2 = 
        restTemplate.exchange("https://v3.football.api-sports.io/players?season=2021&league=94", HttpMethod.GET, request,String.class);
    
        String str2 = result2.getBody();
        JsonNode rootNode2 = mapper.readTree(str2);


        JsonNode responseNodePlayer = rootNode2.path("response");
        Iterator<JsonNode> playerNodes = responseNodePlayer.elements();

        while(playerNodes.hasNext()){
            JsonNode player = playerNodes.next();
            JsonNode playerName = player.path("player").path("name");
            JsonNode playerBirth = player.path("player").path("birth").path("date");
            JsonNode playerPic = player.path("player").path("photo");
            
            JsonNode statisticsNode = player.path("statistics");
            Iterator<JsonNode> statsNodes = statisticsNode.elements();

            JsonNode playerPos = null;
            JsonNode playerTeam = null;
            while(statsNodes.hasNext()){
                JsonNode stats = statsNodes.next();
                playerTeam = stats.path("team").path("name");
                playerPos = stats.path("games").path("position");
            }

            Player p = new Player(playerName.textValue(),playerPos.textValue(),playerBirth.textValue(),playerPic.textValue(),null);
            this.playerService.create_player_without_team(p, playerTeam.textValue());            
        }

        return "homepage";
    }



    // --------------------------------------------------------- User Register

    @GetMapping("/register")
	public String Register(Model m) {
        m.addAttribute("user", new Users());

        return "register";
	}
    


    @PostMapping("/process_register")
	public String process_register(@ModelAttribute Users u) {

        if(this.userService.register(u) == null)
            return "register";
        
        return "redirect:/";
	}
    
    @GetMapping("/login")
	public String Login(Model m) {
        m.addAttribute("loginRequest", new Users());
		return "login";
	}


    @PostMapping("/process_login")
	public String process_login(@ModelAttribute Users u) {
        
        Users user = this.userService.authenticateUsers(u);

        if(user == null){
            return "login";
        }

        if(user.isAdmin()){
            return "admin";
        }

        return "userRegistado";
	}
    
    @GetMapping("/notLogged")
	public String withoutRegister(Model m) {
		return "guest";
	}
    
    // --------------------------------------------------------- Team Register
    @GetMapping("/register_team")
    public String register_team(Model m){
        m.addAttribute("team", new Team());
        return "createTeam";
    }
    
    @PostMapping("/register_team")
    public String register_team(@ModelAttribute Team eq){

        if(this.teamService.create_team(eq) == null)
            System.out.println("err");

        else
            return "admin";

        return null;
    }

    // --------------------------------------------------------- Player Register

    @GetMapping("/register_player")
    public String register_player(Model m){
        m.addAttribute("teams", this.teamService.get_all_teams());
        m.addAttribute("player", new Player());
        return "createPlayer";
    }
    
    @PostMapping("/register_player")
    public String register_player(@ModelAttribute Player p){

        this.playerService.create_player(p);

        return "admin";
    }

    // --------------------------------------------------------- Game Register

    @GetMapping("/create_game")
    public String register_game(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("teams", this.teamService.get_all_teams());
        return "createGame";
    }


    @PostMapping("/create_game")
    public String register_game(@ModelAttribute Game g) {
        this.gameService.create_game(g);
        return "admin";
    }


      // --------------------------------------------------------- EVENTS

      @GetMapping("/gameEvent")
      public String gameEvent(Model m) {  
          m.addAttribute("event", new GameEvent());
        m.addAttribute("gamesInit", this.gameService.get_all_games());
        return "GameEvent";
      }
      @PostMapping("/gameEvent")
      public String gameEvent(@ModelAttribute GameEvent g) {
          this.gameEventService.create_gameEvent(g);
          return "admin";
      }
      

      @GetMapping("/playerEvent")
      public String player_event(Model m) {
        m.addAttribute("games", this.gameService.get_all_games());
          return "playerEvent";
      }
      
    
      @GetMapping("/playerEventAux")
      public String playerEventAux(@RequestParam(name="name", required=true) String nameGame,Model model) {
          model.addAttribute("listPlayers", this.gameService.list_Players(nameGame));
          model.addAttribute("game", this.gameService.name_by_game(nameGame));
          model.addAttribute("event",new PlayerEvent());
          return "playerEvent2";
      }

      
      @PostMapping("/playerEvent")
      public String playerEventAux(@ModelAttribute PlayerEvent p) {
          this.playerEventService.create_PlayerEvent(p);
          return "admin";
      }


    @GetMapping("/listTeams")
    public String listTeams(Model model) {
        model.addAttribute("teams", this.teamService.get_all_teams());
        return "listTeams";
    }

    //tem que checkar
    @GetMapping("/players_team")
    public String listPlayers(@RequestParam(name="name", required=true) String name,Model model) {
        model.addAttribute("players_team", this.playerService.get_all_players(name));
        return "listPlayers";
    }
    
    @GetMapping("/details_game")
    public String details_game(@RequestParam(name="name", required=true) String name,Model model) {
        model.addAttribute("game_details", this.gameEventService.all_events(name));
        return "events";
    }

    

    @GetMapping("/game_details")
    public String game_details(Model model){
        model.addAttribute("by_order", this.gameService.get_all_games_by_order());
        return "gameDetails";
    }
    

    @GetMapping("/stats")
    public String stats(Model model){
        return "stats";
    }

    @GetMapping("/tablescore")
    public String tabelaScore(Model model){
        model.addAttribute("bestScore", this.teamService.bestScore());
        return "tablescore";
    }
    
    @GetMapping("/bestScores")
    public String bestScores(Model model){
        model.addAttribute("bestPlayers", this.playerService.bestPlayer());
        return "bestScores";
    }
    
    @GetMapping("/teamVsTeam")
    public String teamVsTeam(Model model){
        model.addAttribute("game", new Game());
        model.addAttribute("teams", this.teamService.get_all_teams());
        return "teamVsTeam";
    }
    
    @PostMapping("/teamVsTeam")
    public String teamVsTeam(@ModelAttribute Game g,Model model){
        List<Game> list = this.gameService.teamvsTeam(g);
        model.addAttribute("teamvsteam", list);
        return "teamVsTeamShow";
    }


    @GetMapping("/notifications")
    public String notifications(Model model){
        model.addAttribute("by_order", this.gameService.get_all_games_by_order());
        return "notifications";
    }



    @GetMapping("/adminGameEvent")
    public String adminGameEvent(@RequestParam(name="name", required=true) String name,Model model){
        model.addAttribute("gameEvent", new GameEvent());
        model.addAttribute("events", this.gameEventService.eventsFalse(name));
        return "adminGameEvent";
    }
    


    @PostMapping("/adminGameEvent")
    public String adminGameEvent(@ModelAttribute GameEvent id){
        String date = id.getEvento();
        this.gameEventService.notifications_admin_check(date);
        return "redirect:/notifications";
    }
    
    @GetMapping("/adminPlayerEvent")
    public String adminPlayerEvent(@RequestParam(name="name", required=true) String name,Model model){
        model.addAttribute("playerEvent", new PlayerEvent());
        model.addAttribute("events", this.playerEventService.eventsFalse(name));
        return "adminPlayerEvent";
    } 


    @PostMapping("/adminPlayerEvent")
    public String adminPlayerEvent(@ModelAttribute GameEvent id){
        String date = id.getEvento();
        this.playerEventService.notifications_admin_check(date);
        return "redirect:/notifications";
    }
    
    
}