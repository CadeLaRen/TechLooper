package com.techlooper.service;

import com.techlooper.dto.JoinBySocialDto;
import com.techlooper.dto.WebinarInfoDto;
import com.techlooper.model.UserProfileDto;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by phuonghqh on 8/17/15.
 */
public interface WebinarService {

  WebinarInfoDto createWebinarInfo(WebinarInfoDto webinarInfoDto, UserProfileDto organiser) throws IOException;

  Collection<WebinarInfoDto> findAllWebinars();

  List<WebinarInfoDto> listUpcomingWebinar();

  WebinarInfoDto findWebinarById(Long id);

  WebinarInfoDto joinWebinar(JoinBySocialDto joinBySocialDto) throws IOException, MessagingException, TemplateException;
}
