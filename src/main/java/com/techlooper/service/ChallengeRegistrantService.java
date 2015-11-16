package com.techlooper.service;

import com.techlooper.dto.ChallengeWinnerDto;
import com.techlooper.entity.ChallengeRegistrantDto;
import com.techlooper.entity.ChallengeRegistrantEntity;
import com.techlooper.model.ChallengePhaseEnum;
import com.techlooper.model.ChallengeRegistrantPhaseItem;
import com.techlooper.model.ChallengeWinner;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ChallengeRegistrantService {

    Map<ChallengePhaseEnum, ChallengeRegistrantPhaseItem> countNumberOfRegistrantsByPhase(Long challengeId);

    Long countNumberOfFinalists(Long challengeId);

    int countNumberOfWinners(Long challengeId);

    Set<ChallengeRegistrantDto> findRegistrantsByChallengeIdAndPhase(Long challengeId, ChallengePhaseEnum phase, String ownerEmail);

    Set<ChallengeRegistrantDto> findWinnerRegistrantsByChallengeId(Long challengeId);

    Set<ChallengeWinner> saveWinner(ChallengeWinnerDto challengeWinner, String loginUser);

    List<ChallengeRegistrantEntity> findRegistrantsByChallengeId(Long challengeId);

    ChallengeRegistrantEntity findRegistrantById(Long registrantId);
}
