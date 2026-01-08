package com.runner.web.mapper;

import com.runner.web.dto.ClubDto;
import com.runner.web.models.Club;

import java.util.stream.Collectors;

import static com.runner.web.mapper.EventMapper.mapToEventDto;

public class ClubMapper {
    public static Club mapToClub(ClubDto club){
        return  Club.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
    }//I honestly think this code is repeated (after additional study it was concluded that this code is just okay)

    public static ClubDto mapToClubDto(Club club){
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .events(club
                        .getEvents()
                        .stream()
                        .map((event)-> mapToEventDto(event))
                        .collect(Collectors.toList()))
                .build();

    }
}
