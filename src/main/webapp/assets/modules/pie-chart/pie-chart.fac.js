angular.module('Pie').factory('pieFactory', function (utils, jsonValue, termService) {
  var terms = [];
  var data4PieChart = {colors: [], data: [], labels: [], terms: []};
  var innerDonut = utils.isMobile() ? '0%' : '30%';
  var scope;
  //var labels = [];

  var $$ = {
    enableNotifications: function () {
      return utils.getView() === jsonValue.views.pieChart;
    },

    generateChartData: function ($terms) {
      terms = termService.toViewTerms($terms);
      var total = $$.getTotalJob(terms);
      $.each(terms, function (i, term) {
        var per = term.count/total*100;
        term.percent= per.toFixed(1);
      });
      scope.terms = terms;
      scope.$apply();

      $.each(terms, function (i, term) {
        data4PieChart.data.push([term.label, term.count]);
        data4PieChart.colors.push(term.color);
      });
      data4PieChart.terms = terms.toArray("term");
      data4PieChart.labels = terms.toArray("label");
    },

    switchScope: function ($scope) {
      scope = $scope;
      data4PieChart = {colors: [], data: [], labels: [], terms: []};
    },
    getTotalJob: function(terms){
      var total = 0;
      $.each(terms, function (index, item) {
        total = total + item.count;
      })
      return total;
    }
  }
  utils.registerNotification(jsonValue.notifications.switchScope, $$.switchScope, $$.enableNotifications);

  var instance = {
    renderView: function ($terms) {
      $$.generateChartData($terms);
      $('.pie-Chart-Container').highcharts({
        colors: data4PieChart.colors,
        chart: {
          backgroundColor: '#201d1e',
          plotBorderColor: '#efefef'
        },
        title: {
          text: ''
        },
        legend: {
          itemStyle: {
            color: '#efefef'
          },
          itemHoverStyle: {
            color: '#efefef'
          },
          itemHiddenStyle: {
            color: '#efefef'
          }
        },
        labels: {
          style: {
            color: '#efefef'
          }
        },
        tooltip: {
          formatter: function() {
            //console.log(this);
            //console.log(terms[data4PieChart.labels]);
            return sprintf("<b>%(salRange)s</b> <br/>a month in average for jobs in <b>%(label)s</b>",
              terms[data4PieChart.labels.indexOf(this.key)]);
          }
        },
        plotOptions: {
          pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
              enabled: true,
              format: '<b>{point.y}</b> jobs in <b>{point.name}</b>',
              style: {
                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
              }
            }
          },
          series: {
            dataLabels: {
              color: '#efefef'
            },
            marker: {
              lineColor: '#333'
            },
            animation: false
          },
          boxplot: {
            fillColor: '#505053'
          },
          candlestick: {
            lineColor: 'white'
          },
          errorbar: {
            color: 'white'
          }
        },
        series: [{
          type: 'pie',
          name: 'Jobs',
          innerSize: innerDonut,
          point: {
            events: {
              click: function (e) {
                utils.go2SkillAnalyticPage(scope, terms[data4PieChart.labels.indexOf(this.name)].term);
              }
            }
          },
          data: data4PieChart.data
        }]
      });
      $('tspan[dx=0]').css('font-size', '14px');
      $('text[text-anchor=end]').css('display', 'none');
    },

    updateViewTerm: function (term) {
      termService.refineTerm(term);
      Highcharts.charts[0].series[0]
        .data[data4PieChart.terms.indexOf(term.term)].update([term.term, term.count]);
    }
  }

  return instance;
});