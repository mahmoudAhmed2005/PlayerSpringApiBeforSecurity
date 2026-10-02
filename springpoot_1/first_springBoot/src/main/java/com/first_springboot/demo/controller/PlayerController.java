package com.first_springboot.demo.controller;


import com.first_springboot.demo.controller.vm.PlayerVM;
import com.first_springboot.demo.dto.PlayerDto;

import com.first_springboot.demo.model.Player;
import com.first_springboot.demo.service.PlayerService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController   // Controller use jason value


public class PlayerController {

    @Autowired
    private PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }





    @GetMapping("/players")
public ResponseEntity<List<PlayerDto>>  getAllPlayers(){

        return ResponseEntity.ok(playerService.getAllPlayers()) ;
}


@PostMapping("/players")

    public ResponseEntity<PlayerDto>  addPlayer(@RequestBody @Validated PlayerDto playerDto) throws SystemException, URISyntaxException {

      return ResponseEntity.created(new URI("/players")).body(playerService.savePlayer(playerDto)) ;
}


@PutMapping("/players")

    public ResponseEntity<PlayerDto> updatePlayer(@RequestBody PlayerDto playerDto) throws SystemException {
        return ResponseEntity.ok(playerService.updatePlayer(playerDto))  ;
}


 //   http://localhost:8080/players?id=13
 /*@DeleteMapping("/players")
    public void deletePlayer(@RequestParam Long id){
     playerService.removePlayer(id);

 }*/


    //or

    //   http://localhost:8080/players/13
    @DeleteMapping("/players/{id}")
    public ResponseEntity<Void>  deletePlayer(@PathVariable Long id){
     playerService.removePlayer(id);
     return ResponseEntity.noContent().build();

 }




 @GetMapping("/players/{id}")

    public ResponseEntity<PlayerDto>  getPlayerById(@PathVariable Long id) throws SystemException {
       return ResponseEntity.ok(playerService.getPlayerById(id)) ;
 }


 @GetMapping("/players/name/{name}")

    public ResponseEntity<PlayerDto> getPlayerByName(@PathVariable String name) throws SystemException {

        return ResponseEntity.ok(playerService.getPlayerByName(name)) ;

 }


    @GetMapping("/players/search/{name}")

    public ResponseEntity <List<PlayerDto>> searchName(@PathVariable String name) throws SystemException {

        return ResponseEntity.ok(playerService. searchName(name)) ;
}


}
