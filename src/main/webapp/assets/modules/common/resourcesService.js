techlooper.factory("resourcesService", function ($translate, $q, apiService, $filter) {
  var registrantsFilterOptions = [
    {translate: "allContestants", id: "registrantId"},
    {translate: "allSubmission", id: "challengeSubmission"}
  ];

  var registrantsPhaseOptions = [
    {translate: "allPhase", id: "ALL_PHASE"},
    {translate: "registration", id: "REGISTRATION"},
    {translate: "idea", id: "IDEA"},
    {translate: "uiux", id: "UIUX"},
    {translate: "prototype", id: "PROTOTYPE"},
    {translate: "finalApp", id: "FINAL"}
  ];
  var reviewStyleOptions = [
    {translate: "contestOwnerSignOff", id: "contestOwnerSignOff"}
  ];

  var qualityIdeaOptions = [
    {translate: "hasAcceptableTradeoffs", id: "hasAcceptableTradeoffs"},
    {translate: "theSolutionAchievesTheStatedGoals", id: "theSolutionAchievesTheStatedGoals"},
    {translate: "theSolutionIsPracticalAndReliable", id: "theSolutionIsPracticalAndReliable"},
    {translate: "theSolutionIsInnovative", id: "theSolutionIsInnovative"}
  ];

  var emailTemplateOptions = [
    {translate: "chooseATemplate", id: 0},
    {translate: "welcomeContestant", id: 1},
    {translate: "askContestantSubmission", id: 2},
    {translate: "disqualifyContestant", id: 3},
    {translate: "announceWinnerToAllContestants", id: 4}
  ];

  var paymentOptions = [
    {translate: "hourlyByByHour", reviewTranslate: "hourlyJob", id: "hourly"},
    {translate: "fixedPricePayByProject", reviewTranslate: "fixedPrice", id: "fixedPrice"}
  ];

  var estimatedDurationOptions = [
    {translate: "more6m", id: "more6m"},
    {translate: "3to6m", id: "3to6m"},
    {translate: "1to3m", id: "1to3m"},
    {translate: "lt1m", id: "lt1m"},
    {translate: "lt1w", id: "lt1w"}
  ];

  var estimatedWorkloadOptions = [
    {translate: "gt30hrsw", id: "gt30hrsw"},
    {translate: "lt30hrsw", id: "lt30hrsw"},
    {translate: "dontKnow", id: "dontKnow"}
  ];

  var titleSelectize = function (key) {
    return {
      create: false,
      valueField: 'title',
      labelField: 'title',
      maxItems: 1,
      plugins: ["techlooper"],
      selectizeDeffer: $q.defer(),
      getSelectize: function () {
        if (instance[key].selectizeDeffer) return instance[key].selectizeDeffer.promise;
        instance[key].selectizeDeffer = $q.defer();
        return instance[key].selectizeDeffer.promise;
      },
      onInitialize: function (selectize) {
        instance[key].selectizeDeffer.resolve(selectize);
      }
    }
  }

  var idSelectize = function (key) {
    return {
      create: false,
      valueField: 'id',
      labelField: 'title',
      maxItems: 1,
      plugins: ["techlooper"],
      selectizeDeffer: $q.defer(),
      getSelectize: function () {
        if (instance[key].selectizeDeffer) return instance[key].selectizeDeffer.promise;
        instance[key].selectizeDeffer = $q.defer();
        return instance[key].selectizeDeffer.promise;
      },
      onInitialize: function (selectize) {
        instance[key].selectizeDeffer.resolve(selectize);
      }
    }
  }

  var instance = {
    registrantsFilterConfig: $.extend(true, {}, {options: registrantsFilterOptions}, idSelectize("registrantsFilterConfig")),
    registrantsPhaseConfig: $.extend(true, {}, {options: registrantsPhaseOptions}, idSelectize("registrantsPhaseConfig")),
    reviewStyleConfig: $.extend(true, {}, {options: reviewStyleOptions}, titleSelectize("reviewStyleConfig")),
    qualityIdeaConfig: $.extend(true, {}, {options: qualityIdeaOptions}, titleSelectize("qualityIdeaConfig")),
    paymentConfig: $.extend(true, {}, {options: paymentOptions}, idSelectize("paymentConfig")),
    estimatedDurationConfig: $.extend(true, {}, {options: estimatedDurationOptions}, idSelectize("estimatedDurationConfig")),
    estimatedWorkloadConfig: $.extend(true, {}, {options: estimatedWorkloadOptions}, idSelectize("estimatedWorkloadConfig")),
    emailTemplateConfig: $.extend(true, {}, {options: emailTemplateOptions}, idSelectize("emailTemplateConfig")),

    inOptions: function (title, config) {
      var index = -1;
      $.each(config.options, function (i, opt) {
        if (opt.title === title) {
          index = i;
          return false;
        }
      });
      return index;
    },

    getOption: function (id, config) {
      var option = undefined;
      $.each(config.options, function (i, opt) {
        if (opt.id === id) {
          option = opt;
          return false;
        }
      });
      return option;
    },

    getEmailTemplates: function() {
      var deffer = $q.defer();
      if (instance.emailTemplates) {
        deffer.resolve(instance.emailTemplates);
        return deffer.promise;
      }
      apiService.getAvailableEmailTemplates()
        .success(function (templateList) {
          templateList.unshift({templateId: 0, body: "", bodyVariables: [], language: $translate.use(),
            subject: "", subjectVariables: [], templateName: "None"});
          _.each(templateList, function (template) {
            template.text = $filter('translate')(template.templateName);
            template.value = template.templateId;
          });
          instance.emailTemplates = templateList;
          deffer.resolve(instance.emailTemplates);
        });
      return deffer.promise;
    }
  }

  var translations = [
    {ins: instance.registrantsFilterConfig, placeholder: "exRegistrantsFilterConfig"},
    {ins: instance.registrantsPhaseConfig, placeholder: "exRegistrantsPhaseConfig"},
    {ins: instance.reviewStyleConfig, placeholder: "exContestOwnerSignOff"},
    {ins: instance.qualityIdeaConfig, placeholder: "exQualityIdeaConfig"},
    {ins: instance.paymentConfig, placeholder: "exPaymentConfig"},
    {ins: instance.estimatedDurationConfig, placeholder: "exEstimatedDurationConfig"},
    {ins: instance.estimatedWorkloadConfig, placeholder: "exEstimatedWorkloadConfig"},
    {ins: instance.emailTemplateConfig, placeholder: "exChooseATemplate"}
  ];

  $.each(translations, function (i, item) {
    item.ins.getSelectize().then(function ($selectize) {
      item.ins.placeholder = $filter("translate")(item.placeholder);
      $selectize.setPlaceholder(item.ins.placeholder);

      $.each(item.ins.options, function (i, row) {
        if ($.type(row) == "array") {
          item.ins.options = [];
          $.each(row, function (i, r) {
            r.title = $filter("translate")(r.translate);
            item.ins.options.push(r);
          });
          return;
        }
        row.title = $filter("translate")(row.translate);
      });
    });
  });

  return instance;
});