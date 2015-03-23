package com.techlooper.service;

import com.techlooper.entity.userimport.UserImportEntity;

import java.util.Map;

/**
 * Created by NguyenDangKhoa on 3/19/15.
 */
public interface UserEvaluationService {

    long score(UserImportEntity user);

    double rate(UserImportEntity user);

    Map<String,Integer> rank(UserImportEntity user);

    Map<String,Long> getSkillMap();

    Map<String,Long> getTotalNumberOfJobPerSkill();
}
