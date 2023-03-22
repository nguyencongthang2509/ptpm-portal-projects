app.service("MeMemberProjectService", function ($http) {
    var members = [];
  
    this.getMembers = function () {
      return members;
    };
  
    this.fetchMembers = function (idProject) {
      return $http.get(apiMemnerProject + "/" + idProject).then(
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