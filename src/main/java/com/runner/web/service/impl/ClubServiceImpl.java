package com.runner.web.service.impl;

import com.runner.web.dto.ClubDto;
import com.runner.web.models.Club;
import com.runner.web.repo.ClubRepo;
import com.runner.web.service.ClubService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepo clubRepo;

    public ClubServiceImpl(ClubRepo clubRepo){
        this.clubRepo = clubRepo;
    }

    @Override
    public List <ClubDto> findAllClubs() {
       List<Club> clubs = clubRepo.findAll();

       return clubs.stream()
               .map((club)->mapToClubDto(club))
               .collect(Collectors.toList());
       //collect(Collectors.toList()) allows for a List to be returned
    }

    @Override
    public Club saveClub(Club club) {
        return clubRepo.save(club);
    }

    private ClubDto mapToClubDto(Club club){
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
        return clubDto;
    }
}
