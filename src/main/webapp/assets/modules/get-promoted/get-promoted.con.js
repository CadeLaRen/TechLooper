techlooper.controller('getPromotedController', function ($scope, utils, vnwConfigService, $q, userPromotionService, $http, $location) {
  $scope.selectize = vnwConfigService;

  //var formSubmitted = function(form, prop) {
  //  return form && (form[prop].$touched || form.$submitted);
  //}

  var state = {
    default: {
      showView: function (viewName) {
        switch (viewName) {
          case "enable-do-suggestion":
            return true;
        }
      }
    },

    result: {
      showResult: true,

      showView: function (viewName) {
        var promotionResult = $scope.masterPromotion.result;
        var promotionEmailForm = $scope.promotionEmailForm;
        var hasPromotionResult = promotionResult && $.type(promotionResult.salaryMin) === "number" && $.type(promotionResult.salaryMax) === "number";
        var surveyFormHasSubmitted = utils.isFormSubmitted($scope.promotionSurveyForm, "isUnderstandable");

        switch (viewName) {
          case "error-email-is-submitted":
            return utils.isFormSubmitted($scope.promotionEmailForm, "promotionEmail");

          case "error-required-survey-is-understandable":
            var errorRequired = $scope.promotionSurveyForm.isUnderstandable.$error.required;
            console.log($scope.promotionSurveyForm.isUnderstandable);
            return errorRequired && surveyFormHasSubmitted;

          case "no-promotion-result":
            return !hasPromotionResult;

          case "has-promotion-result":
            return hasPromotionResult;

          case "sent-promoted-email-no-result":
            var sentEmail = promotionEmailForm && promotionEmailForm.$sentEmail;
            return !hasPromotionResult && sentEmail;

          case "sent-promoted-email-has-result":
            var sentEmail = promotionEmailForm && promotionEmailForm.$sentEmail;
            return hasPromotionResult && sentEmail;

          case "not-sent-promoted-email":
            var sentEmail = promotionEmailForm && promotionEmailForm.$sentEmail;
            return !sentEmail;
        }
        return false;
      }
    }
  }

  $scope.viewsDefers = {
    getPromotedForm: $q.defer()
  };

  //var viewsPromises = utils.toPromises($scope.viewsDefers);
  $q.all($scope.viewsDefers.getPromotedForm).then(function (data) {
    var doPromotionWithParam = function (promotionInfo, forceValidation) {
      $scope.promotionInfo = angular.copy(userPromotionService.refinePromotionInfo(promotionInfo));
      $scope.doPromotion(forceValidation);
    }

    //http://localhost:8080/#/get-promoted?jobTitle=java&jobLevelIds=[5,6]&jobCategoryIds=[35,55,57]&lang=en&utm_source=getpromotedemail&utm_medium=skilltrendsbutton&utm_campaign=howtogetpromoted
    var param = $location.search();
    if (param.id) {
      $http.get("getPromotedResult/" + param.id).success(function (data, status, headers, config) {
        doPromotionWithParam(data, true);
      });
    }
    else if (!$.isEmptyObject(param)) {
      param = utils.toObject(param);
      doPromotionWithParam(param);
    }
  });

  $scope.changeState = function (st) {
    var pState = angular.copy(state[st] || st);
    $scope.state = pState;
    $scope.$emit("stateChanged");
  }

  $scope.changeState("default");
});