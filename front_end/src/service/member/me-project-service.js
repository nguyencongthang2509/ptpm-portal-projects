app.service("MeProjectService", function ($http) {
  var projects = [];

  this.getProjects = function () {
    return projects;
  };

  this.setProject = function (data) {
    projects = data;
  };

  this.fetchProjects = function () {
    return $http.get(apiGetAllProjectById).then(
      function (response) {
        if (response.status === 200) {
          projects = response.data.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});

app.service("MeDetailProjectService", function ($http) {
  var project = {};

  this.getProject = function () {
    return project;
  };

  this.setProject = function (data) {
    project = data;
  };

  this.fetchProject = function (id) {
    return $http.get(apiDetailProject + id).then(
      function (response) {
        if (response.status === 200) {
          project = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});
