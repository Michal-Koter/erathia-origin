package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiaMusicClient.musicsClient.contract.TrackDto;
import com.erathia.erathiaData.models.Track;

public class TrackMapper implements IMap<TrackDto, Track> {
    public Track map(TrackDto trackDto) {
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
