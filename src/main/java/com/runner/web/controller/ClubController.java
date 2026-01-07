package com.runner.web.controller;

import com.runner.web.dto.ClubDto;
import com.runner.web.models.Club;
import com.runner.web.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }

    @GetMapping("club")
    public String club(Model model){
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "club-list";
    }

    @GetMapping("/club/new")
    public String createClubForm(Model model){
        ClubDto club = new ClubDto();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @PostMapping("/club/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model){
        //the validation is being applied but the messages are not showing on the page, work on that.
        if(result.hasErrors()){
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return"redirect:/club";
    }

    @GetMapping("/club/{clubId}")
    public String clubDetail(@PathVariable("clubId") Long clubId, Model model){
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "club-detail";
    }

    @GetMapping("/club/{clubId}/edit")
    public String editClubForm(@PathVariable Long clubId, Model model){
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "club-edit";
    }

    @PostMapping("/club/{clubId}/edit")
    public String updateClub(@PathVariable Long clubId,
                             @Valid @ModelAttribute("club") ClubDto club,
                             BindingResult result){
        if(result.hasErrors()){
            return "club-edit";
        }
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/club";
    }
}
