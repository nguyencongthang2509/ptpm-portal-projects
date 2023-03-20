app.service("MeAssignService", function ($http) {
  var members = [];

  this.getMembers = function () {
    return members;
  };

  this.setMember = function (data) {
    members = data;
  };

  this.fetchMember = function (idTodo) {
    return $http.get(apiMemberAssign + "?idTodo=" + idTodo).then(
      function (response) {
        if (response.status === 200) {
          members = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});
