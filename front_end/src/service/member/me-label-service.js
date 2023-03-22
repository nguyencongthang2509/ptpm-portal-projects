app.service("MeLabelService", function ($http) {
  var labelDetail = [];
  var labels = [];

  this.getLabels = function () {
    return labelDetail;
  };

  this.getAllLabels = function () {
    return labels;
  };

  this.fetchLabel = function (idTodo) {
    return $http.get(apiMemberLabel + "?idTodo=" + idTodo).then(
      function (response) {
        if (response.status === 200) {
          labelDetail = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };

  this.fetchLabels = function(){
    return $http.get(apiMemberLabel + "/list").then(
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
  }
});
