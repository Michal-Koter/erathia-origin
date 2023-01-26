package com.erathia.erathiawebapi.music.mappers;

import com.erathia.erathiaData.models.Track;
import com.erathia.erathiawebapi.contracts.TrackDto;
import org.springframework.stereotype.Component;

@Component
public class TrackMapper implements IMapEntityDto<TrackDto, Track> {
    @Override
    public TrackDto mapToDto(Track track) {
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
        Track track = new Track();
        if ((Integer) trackDto.getId() != null) {
            track.setSourceId(trackDto.getId());
        } else {
            track.setSourceId(-1);
        }
        track.setBpm(trackDto.getBpm());
        track.setTrackPosition(trackDto.getTrackPosition());
        track.setDuration(trackDto.getDuration());
        track.setTitle(trackDto.getTitle());
        track.setReleaseDate(trackDto.getReleaseDate());
        return track;
    }
}
