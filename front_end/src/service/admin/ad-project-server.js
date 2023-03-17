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


  app.service("AdGetAllProject", function ($http) {
    var project = [];
    var totalpages = 0;
  
    this.getProject = function () {
      return project;
    };
  
    this.getTotalpages = function () {
      return totalpages;
    };
  
    this.setProject = function (data) {
      project = data;
    };
  
    this.fetchProject = function () {
      return $http.get(projcetAPI).then(
        function (response) {
          if (response.status === 200) {
            project = response.data.data.data;
            totalpages = response.data.data.totalPages;
          }
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
    };
  
    this.searchProject = function (inputSearch) {
      return $http
        .get(  projcetAPI +"/search"+"?name="+inputSearch)
        .then(
          function (response) {
            if (response.status === 200) {
              project = response.data.data.data;
              console.log(project);
              totalpages = response.data.data.totalPages;
            }
            return response;
          },
          function (errors) {
            console.log(errors);
          }
        );
    };
  
    this.pageProject = function (inputSearch, currentPage) {
      return $http
        .get(
          projcetAPI +"/search"+
            "?name=" +
            inputSearch +
            "&page=" +
            currentPage
        )
        .then(
          function (response) {
            if (response.status === 200) {
              project = response.data.data.data;
              totalpages = response.data.data.totalPages;
            }
            return response;
          },
          function (errors) {
            console.log(errors);
          }
        );
    };
  }); 

 
