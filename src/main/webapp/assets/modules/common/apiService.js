techlooper.factory("apiService", function ($rootScope, $location, jsonValue, $http) {
  var instance = {

    login: function (techlooperKey) {
      return $http.post("login",
        $.param(techlooperKey), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}});
    },

    /**
     * Get current login user info
     * */
    getCurrentUser: function (type) {
      switch (type) {
        default:
          return $http.get("user/vnw-current");
      }
    },

    logout: function () {
      return $http.get("logout");
    },

    getFBLoginUrl: function() {
      return $http.get("social/FACEBOOK_REGISTER/loginUrl", {transformResponse: function (d, h) {return d;}});
    },

    getContestDetail: function(id) {
      return $http.get("challenge/" + id);
    },

    joinContest: function(contestId, registrantEmail, lang) {
      return $http.post("challenge/join",
        {challengeId: contestId, registrantEmail: registrantEmail, lang: lang}, {transformResponse: function (d, h) {return d;}});
    },

    searchContests: function() {
      return $http.get("challenge/list");
    },

    getSuggestSkills: function(text) {
      return $http.get("suggestion/skills/" + text);
    }
  }

  return instance;
});