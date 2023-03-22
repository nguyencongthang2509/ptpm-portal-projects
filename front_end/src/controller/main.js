app.controller("myCtrl", function ($scope) {});

app.directive("myTooltip", function () {
  return {
    restrict: "A",
    scope: {
      tooltipContent: "@",
    },
    link: function (scope, element, attrs) {
      $(element).attr("data-bs-toggle", "tooltip");
      $(element).attr("title", scope.tooltipContent);
      $(element).attr("data-bs-placement", "top");
      $(element).attr("data-bs-color", "#FFFFFF"); 
      $(element).attr("data-bs-text-color", "#FFFFFF"); 
      $(element).tooltip();
    },
  };
});
