package com.runner.web.controller;

import com.runner.web.dto.ClubDto;
import com.runner.web.models.Club;
import com.runner.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @PostMapping("/club/new")
    public String saveClub(@ModelAttribute("club") Club club){
        clubService.saveClub(club);
        return"redirect:/club";
    }

    @GetMapping("/club/{clubId}/edit")
    public String editClubForm(@PathVariable Long clubId, Model model){
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "club-edit";
    }

    @PostMapping("/club/{clubId}/edit")
    public String updateClub(@PathVariable int clubId, @ModelAttribute("club") ClubDto club){
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/club";
    }
}
