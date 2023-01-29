package com.erathia.erathiawebapi.music.mappers;

import com.erathia.erathiaData.models.Track;
import com.erathia.erathiawebapi.contracts.TrackDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TrackMapper implements IMapEntityDto<TrackDto, Track> {
    private static final Logger logger = LoggerFactory.getLogger(TrackMapper.class);

    @Override
    public TrackDto mapToDto(Track track) {
        logger.debug("Run mapToDto(Track), track={}", track);
        TrackDto trackDto = new TrackDto();
        trackDto.setBpm(track.getBpm());
        trackDto.setTrackPosition(track.getTrackPosition());
        trackDto.setDuration(track.getDuration());
        trackDto.setId(track.getId());
        trackDto.setTitle(track.getTitle());
        trackDto.setReleaseDate(track.getReleaseDate());
        return trackDto;
    }

    @Override
    public Track mapToEntity(TrackDto trackDto) {
        logger.debug("Run mapToEntity(TrackDto), trackDto={}", trackDto);
        Track track = new Track();
        track.setId(trackDto.getId());
        track.setBpm(trackDto.getBpm());
        track.setTrackPosition(trackDto.getTrackPosition());
        track.setDuration(trackDto.getDuration());
        track.setTitle(trackDto.getTitle());
        track.setReleaseDate(trackDto.getReleaseDate());
        return track;
    }
}
