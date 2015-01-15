angular.module('Register').controller('registerController',
  function ($scope, connectionFactory, jsonValue, localStorageService, utils, registerService, userService, navigationService) {
    utils.sendNotification(jsonValue.notifications.switchScope, $scope);
    registerService.translation();
    $scope.authSource = jsonValue.authSource;
    $scope.openOathDialog = registerService.openOathDialog;
    $scope.hasProfile = registerService.hasProfile;
    userService.getUserInfo()
      .then(function () {
        localStorageService.remove(jsonValue.storage.back2Me, "true");
        utils.sendNotification(jsonValue.notifications.gotData);
        $('input').iCheck({
          checkboxClass: 'icheckbox_polaris',
          radioClass: 'iradio_polaris',
          increaseArea: '-10%' // optional
        });
      })
      .catch(function () {
        localStorageService.set(jsonValue.storage.back2Me, "true");
        utils.sendNotification(jsonValue.notifications.loginFailed);
      });
    navigationService.addSpaceforNavi();
    navigationService.reSetingPositionLangIcon();
    navigationService.keepNaviBar();
  });