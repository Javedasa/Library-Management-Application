package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.LibraryCard;
import com.example.Library.Management.System.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/generatePlainCard")
    public ResponseEntity generatePlainCard(){
        LibraryCard newCard=cardService.generatePlainCard();
        String response="The new card is generated and having a cardNo"+newCard.getCardNo();

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/associateWithStudent")
    public ResponseEntity associateWithStudent(@RequestParam("studentId")Integer studentId,@RequestParam("cardNo")Integer cardNo){
        String result=cardService.associateWithStudent(studentId,cardNo);
        return new ResponseEntity(result,HttpStatus.OK);
    }
}
