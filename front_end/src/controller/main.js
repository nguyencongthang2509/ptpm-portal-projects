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
      $(element).tooltip();
      $(element).click(function () {
        $(element).tooltip("dispose");
      });
      $(element).mouseenter(function () {
        $(element).tooltip();
      });
      scope.$watch("tooltipContent", function (newVal) {
        $(element).tooltip("dispose");
        $(element).attr("data-bs-toggle", "tooltip");
        $(element).attr("title", newVal);
        $(element).attr("data-bs-placement", "top");
        $(element).tooltip();
      });
    },
  };
});
