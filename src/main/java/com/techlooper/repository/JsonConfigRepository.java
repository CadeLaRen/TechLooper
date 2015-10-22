package com.techlooper.repository;

import com.techlooper.model.SocialConfig;
import com.techlooper.model.TechnicalTerm;
import com.techlooper.util.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by NguyenDangKhoa on 11/25/14.
 */
@Repository
public class JsonConfigRepository {

    @Value("classpath:skill.json")
    private Resource skillJsonResource;

    @Value("classpath:socialConfig.json")
    private Resource socialConfigResource;

    @Value("classpath:commonTerm.json")
    private Resource commonTerm;

    /**
     * Load technical term and skill from JSON file and put them into the cache
     *
     * @return list of terms {@link com.techlooper.model.SocialConfig}
     */
    @Cacheable(value = "SOCIAL_CONFIG")
    public List<SocialConfig> getSocialConfig() {
        return JsonUtils.toList(socialConfigResource, SocialConfig.class).get();
    }

    @Cacheable(value = "SKILL_CONFIG")
    public List<TechnicalTerm> getSkillConfig() {
        return JsonUtils.toList(skillJsonResource, TechnicalTerm.class).get();
    }

    @Cacheable(value = "COMMON_TERM")
    public List<String> getCommonTerm() {
        return JsonUtils.toList(commonTerm, String.class).get();
    }

    /**
     * Intentionally, this method is just used to invalidate the cache and trigger reloading new term and skill from JSON file
     */
    @CacheEvict(value = {"SOCIAL_CONFIG", "SKILL_CONFIG", "COMMON_TERM"}, allEntries = true)
    public void refresh() {
    }

    /**
     * This method find the matching technical term
     *
     * @param termKey the term to look for
     * @return Correct instance of {@link com.techlooper.model.TechnicalTerm} related to termKey
     */
    public TechnicalTerm findByKey(String termKey) {
        return getSkillConfig().stream().filter(term -> term.getKey().equals(termKey)).findFirst().get();
    }

    public Resource getSkillJsonResource() {
        return skillJsonResource;
    }
}
