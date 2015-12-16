package com.techlooper.entity;

import com.techlooper.model.SocialProvider;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class UserEntity {

    private String id;

    private String emailAddress;

    private String firstName;

    private String lastName;

    private String createdDateTime;

    private SocialProvider loginSource;

    private Map<SocialProvider, Object> profiles;

    private AccessGrant accessGrant;

    private String username;

    private Integer salary;

    private String profileImageUrl;

    public String key() {
        return StringUtils.hasText(emailAddress) ? emailAddress : id;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccessGrant getAccessGrant() {
        return accessGrant;
    }

    public void setAccessGrant(AccessGrant accessGrant) {
        this.accessGrant = accessGrant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SocialProvider getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(SocialProvider loginSource) {
        this.loginSource = loginSource;
    }

    public Map<SocialProvider, Object> getProfiles() {
        return profiles;
    }

    public void setProfiles(Map<SocialProvider, Object> profiles) {
        this.profiles = profiles;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public static class UserEntityBuilder {
        private UserEntity userEntity;

        private UserEntityBuilder() {
        }

        public static UserEntityBuilder userEntity(UserEntity userEntity) {
            UserEntityBuilder builder = new UserEntityBuilder();
            builder.userEntity = userEntity;
            return builder;
        }

        public UserEntityBuilder withId(String id) {
            userEntity.id = id;
            return this;
        }

        public UserEntityBuilder withLoginSource(SocialProvider loginSource) {
            userEntity.loginSource = loginSource;
            return this;
        }

        public UserEntityBuilder withAccessGrant(AccessGrant accessGrant) {
            userEntity.accessGrant = accessGrant;
            return this;
        }

        public UserEntity build() {
            return userEntity;
        }

        public UserEntityBuilder withProfile(SocialProvider provider, Object profile) {
            if (userEntity.profiles == null) {
                userEntity.profiles = new HashMap<>();
            }
            userEntity.profiles.put(provider, profile);
            return this;
        }

        public UserEntityBuilder withCreatedDateTime(String createdDateTime) {
            userEntity.createdDateTime = createdDateTime;
            return this;
        }
    }
}
