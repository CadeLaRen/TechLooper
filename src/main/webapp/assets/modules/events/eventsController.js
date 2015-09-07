techlooper.controller("eventsController", function ($scope, apiService, utils, jsonValue) {

  utils.sendNotification(jsonValue.notifications.loading, $(window).height());

  apiService.findAvailableWebinars()
    .success(function (webinars) {//group by startDate
      var group = [];
      var lastVisitWebinars = [];
      for (var i = 0; i < webinars.length; i++) {
        if ($.inArray(webinars[i], lastVisitWebinars) >= 0) continue;
        var startDate = moment(webinars[i].startDate, jsonValue.dateTimeFormat);
        if (!startDate.isValid()) continue;
        var web = {startDate: startDate.format(jsonValue.dateFormat)};
        group.push(web);
        lastVisitWebinars = [];
        for (var j = i; j < webinars.length; j++) {
          if ($.inArray(webinars[j], lastVisitWebinars) >= 0) continue;
          if (startDate.isSame(moment(webinars[j].startDate, jsonValue.dateTimeFormat), "day")) {
            lastVisitWebinars.push(webinars[j]);
          }
        }
        web.webinars = lastVisitWebinars;
      }
      $scope.webinarsGroup = group;
    })
    .finally(function () {
      utils.sendNotification(jsonValue.notifications.loaded);
    });

});