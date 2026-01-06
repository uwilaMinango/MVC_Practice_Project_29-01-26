package com.runner.web.service;

import com.runner.web.dto.ClubDto;
import com.runner.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();

    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(Long clubId);
    void updateClub(ClubDto clubDto);
}
