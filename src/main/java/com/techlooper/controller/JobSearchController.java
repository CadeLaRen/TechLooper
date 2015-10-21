package com.techlooper.controller;

import com.techlooper.entity.SalaryReviewEntity;
import com.techlooper.model.VNWJobSearchRequest;
import com.techlooper.model.VNWJobSearchResponse;
import com.techlooper.service.JobSearchService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class JobSearchController {

  @Resource
  private JobSearchService vietnamWorksJobSearchService;

  @Value("${vnw.api.configuration.category.it.software.en}")
  private String category;

  @Resource
  private Mapper dozerMapper;

//  @SendTo("/topic/jobs/search")
//  @MessageMapping("/jobs/search")
//  public JobSearchResponse searchJobs(JobSearchRequest searchJobsRequest) {
//    final VNWJobSearchRequest vnwJobSearchRequest = convertToVNWJobSearchRequest(searchJobsRequest);
//    final VNWJobSearchResponse vnwJobSearchResponse = vietnamWorksJobSearchService.searchJob(vnwJobSearchRequest);
//    return convertToJobSearchResponse(vnwJobSearchResponse);
//  }

  @SendTo("/topic/jobs/searchJobAlert")
  @MessageMapping("/jobs/searchJobAlert")
  public VNWJobSearchResponse searchJobAlert(SalaryReviewEntity salaryReviewEntity) {
    VNWJobSearchRequest vnwJobSearchRequest = dozerMapper.map(salaryReviewEntity, VNWJobSearchRequest.class);
    VNWJobSearchResponse vnwJobSearchResponse = vietnamWorksJobSearchService.searchJob(vnwJobSearchRequest);
    vnwJobSearchResponse.getData().setJobs(null);//remove jobs because we dont need it
    return vnwJobSearchResponse;
  }

//  private VNWJobSearchRequest convertToVNWJobSearchRequest(JobSearchRequest jobSearchRequest) {
//    return new VNWJobSearchRequest(jobSearchRequest.getTerms(), category, jobSearchRequest.getPageNumber());
//  }

//  private JobSearchResponse convertToJobSearchResponse(VNWJobSearchResponse vnwJobSearchResponse) {
//    final JobSearchResponse jobSearchResponse = new JobSearchResponse();
//    jobSearchResponse.setTotal(vnwJobSearchResponse.getData().getTotal());
//    final Stream<VNWJobSearchResponseDataItem> responseDataItemStream = vnwJobSearchResponse.getData().getJobs().stream();
//    jobSearchResponse.setJobs(responseDataItemStream.map(VNWJobSearchResponseDataItem::toJobResponse).collect(Collectors.toSet()));
//    return jobSearchResponse;
//  }
}
