techlooper.controller("topicsController", function ($scope, apiService, jsonValue, utils) {
  utils.sendNotification(jsonValue.notifications.loading);
  apiService.getlatestTopics().success(function(response) {
    $scope.topics = response.topics;
    console.log($scope.topics);
  }).finally(function () {
    utils.sendNotification(jsonValue.notifications.loaded);
  });

});