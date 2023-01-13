package com.erathia.erathiamusicupdater.music.mappers;

import com.erathia.erathiaMusicClient.musicsClient.contract.TrackDto;
import com.erathia.erathiadata.models.Track;

public class TrackMapper implements IMap{
    public static Track map(TrackDto trackDto) {
        Track track = new Track();
        track.setBpm(trackDto.getBpm());
        track.setDuration(trackDto.getDuration());
        track.setTrackPosition(trackDto.getTrackPosition());
        track.setLink(trackDto.getLink());
        track.setRank(trackDto.getRank());
        track.setReleaseDate(trackDto.getReleaseDate());
        track.setTitle(trackDto.getTitle());
        track.setSourceId(trackDto.getId());
        return track;
    }
}
