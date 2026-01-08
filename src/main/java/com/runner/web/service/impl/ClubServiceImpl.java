package com.runner.web.service.impl;

import com.runner.web.dto.ClubDto;
import com.runner.web.models.Club;
import com.runner.web.repo.ClubRepo;
import com.runner.web.service.ClubService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

import static com.runner.web.mapper.ClubMapper.mapToClub;
import static com.runner.web.mapper.ClubMapper.mapToClubDto;

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
    public Club saveClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        return clubRepo.save(club);
    }

    @Override
    public ClubDto findClubById(Long clubId) {
        Club club = clubRepo.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepo.save(club);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepo.searchClubs(query);
        return clubs.stream()
                .map(club->mapToClubDto(club))
                .collect(Collectors.toList());
    }
}
