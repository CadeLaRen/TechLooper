techlooper.controller("postContestController", function ($scope, $http, jsonValue, $translate, $location, utils,
                                                         resourcesService) {
  var state = {
    challenge: {
      showChallenge: true,
      status: function (type) {
        switch (type) {
          case "is-form-valid":
            $scope.challengeForm.$setSubmitted();
            return $scope.challengeForm.$valid;

          case "challenge-tab-class":
            return "active showNavi";
        }
      },
      nextState: "timeline"
    },

    timeline: {
      showTimeline: true,
      status: function (type) {
        switch (type) {
          case "is-form-valid":
            $scope.timelineForm.$setSubmitted();
            return $scope.timelineForm.$valid;

          case "challenge-tab-class":
            return "active";

          case "timeline-tab-class":
            return "active showNavi";

          case "ex-start-date":
            return moment().add(7, 'day').format('DD/MM/YYYY');

          case "ex-register-date":
            return moment().add(21, 'day').format('DD/MM/YYYY');

          case "ex-submit-date":
            return moment().add(63, 'day').format('DD/MM/YYYY');

          case "start-date-wt-4w":
            var lastDate = moment().add(4, 'weeks');
            return moment($scope.contest.startDate, jsonValue.dateFormat).isBetween(moment(), lastDate, 'day');

          case "register-date-gt-start-date":
            var lastDate = moment($scope.contest.startDate, jsonValue.dateFormat);
            return moment($scope.contest.registrationDate, jsonValue.dateFormat).isAfter(lastDate, 'day');

          case "submit-date-gt-register-date":
            var lastDate = moment($scope.contest.registrationDate, jsonValue.dateFormat);
            return moment($scope.contest.submissionDate, jsonValue.dateFormat).isAfter(lastDate, 'day');
        }
      },
      nextState: "reward"
    },

    reward: {
      showReward: true,
      status: function (type, param) {
        switch (type) {
          case "is-form-valid":
            $scope.rewardForm.$setSubmitted();
            return $scope.rewardForm.$valid;

          case "challenge-tab-class":
          case "timeline-tab-class":
            return "active";

          case "reward-tab-class":
            return "active showNavi";

          case "place-reward-range":
            return param <= 5000 && param >= 100;

          case "quality-idea-list":
            var array = [""];
            return [];
        }
      },

      nextState: function () {
        var request = angular.copy($scope.contest);
        request.lang = $translate.use();
        utils.sendNotification(jsonValue.notifications.loading);
        $http.post("challenge/publish", request, {transformResponse: function (d, h) {return d;}})
          .then(function (response) {
            $location.path("/contest-detail" + "?" + response.data.id);
          })
          .catch(function (response) {
            console.error('Gists error', response.status, response.data);
          })
          .finally(function () {
            utils.sendNotification(jsonValue.notifications.loading);
          });
        //.success(function(data) {
        //  $location.path("contest-detail");
        //});
        return true;
      }
    }
  }

  $scope.changeState = function (st) {
    if (!st || ($scope.state && !$scope.state.status("is-form-valid"))) {
      return false;
    }

    if ($.type(st) === "function") {
      return st();
    }

    var pState = angular.copy(state[st] || st);
    $scope.state = pState;
  }

  $scope.nextState = function () {
    $scope.changeState($scope.state.nextState);
  }

  $scope.config = {
    reviewStyle: resourcesService.reviewStyleConfig,
    qualityIdea: resourcesService.qualityIdeaConfig
  }

  $scope.changeState(state.challenge);
});