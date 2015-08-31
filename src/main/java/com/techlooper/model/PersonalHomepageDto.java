package com.techlooper.model;

import com.techlooper.dto.WebinarInfoDto;

import java.util.List;

/**
 * Created by NguyenDangKhoa on 7/27/15.
 */
public class PersonalHomepageDto {

    private TermStatisticResponse termStatistic;

    private ChallengeDetailDto latestChallenge;

    private List<WebinarInfoDto> latestEvents;

    private List<ProjectDto> latestProjects;

    public TermStatisticResponse getTermStatistic() {
        return termStatistic;
    }

    public void setTermStatistic(TermStatisticResponse termStatistic) {
        this.termStatistic = termStatistic;
    }

    public ChallengeDetailDto getLatestChallenge() {
        return latestChallenge;
    }

    public void setLatestChallenge(ChallengeDetailDto latestChallenge) {
        this.latestChallenge = latestChallenge;
    }

    public List<WebinarInfoDto> getLatestEvents() {
        return latestEvents;
    }

    public void setLatestEvents(List<WebinarInfoDto> latestEvents) {
        this.latestEvents = latestEvents;
    }

    public List<ProjectDto> getLatestProjects() {
        return latestProjects;
    }

    public void setLatestProjects(List<ProjectDto> latestProjects) {
        this.latestProjects = latestProjects;
    }
}
