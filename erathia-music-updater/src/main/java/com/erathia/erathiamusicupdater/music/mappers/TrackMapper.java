package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiamusicclient.musicsClient.contract.TrackDto;
import com.erathia.erathiadata.models.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TrackMapper implements IMap<TrackDto, Track> {
    private static final Logger logger = LoggerFactory.getLogger(TrackMapper.class);

    public Track map(TrackDto trackDto) {
        logger.debug("Run map(TrackDto), trackDto={}", trackDto);
        Track track = new Track();
        track.setBpm(trackDto.getBpm());
        track.setDuration(trackDto.getDuration());
        track.setTrackPosition(trackDto.getTrackPosition());
        track.setReleaseDate(trackDto.getReleaseDate());
        track.setTitle(trackDto.getTitle());
        track.setSourceId(trackDto.getId());
        return track;
    }
}
