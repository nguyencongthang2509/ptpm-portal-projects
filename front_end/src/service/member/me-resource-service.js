app.service("MeResourceService", function ($http) {
  var resources = [];

  this.getResources = function () {
    return resources;
  };

  this.setResource = function (data) {
    resources = data;
  };

  this.fetchResources = function (idProject) {
    return $http.get(apiMemberResource + idProject).then(
      function (response) {
        if (response.status === 200) {
          resources = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});
