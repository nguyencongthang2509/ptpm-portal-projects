app.service("MeLabelService", function ($http) {
  var labels = [];

  this.getLabels = function () {
    return labels;
  };

  this.setLabel = function (data) {
    labels = data;
  };

  this.fetchLabel = function (idTodo) {
    return $http.get(apiMemberLabel + "?idTodo=" + idTodo).then(
      function (response) {
        if (response.status === 200) {
          labels = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});
