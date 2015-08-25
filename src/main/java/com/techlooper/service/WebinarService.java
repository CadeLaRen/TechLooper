package com.techlooper.service;

import com.techlooper.dto.WebinarInfoDto;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by phuonghqh on 8/17/15.
 */
public interface WebinarService {

    WebinarInfoDto createWebinarInfo(WebinarInfoDto webinarInfoDto, String organiser) throws IOException;

    Collection<WebinarInfoDto> findAvailableWebinars();

    List<WebinarInfoDto> listUpcomingWebinar();

}
