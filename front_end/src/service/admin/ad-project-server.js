app.service("AdProjcetService", function ($http) {
    var projects = [];
  
    this.getProject = function () {
      return projects;
    };
  
    this.setProject = function (data) {
        projects = data;
    };
  
    this.fetchProject = function () {
      return $http.get(projcetAPI).then(
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


  app.service("getOneAdProjcetService", function ($http) {
    var projects = {};
  
    this.getProject = function () {
      return projects;
    };
  
    this.setProject = function (data) {
        projects = data;
    };
  
    this.fetchProject = function (idProject) {
      return $http.get( projcetAPI+"/"+idProject).then(
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

