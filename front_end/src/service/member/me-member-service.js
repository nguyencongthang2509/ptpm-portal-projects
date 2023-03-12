app.service("MeMemberService", function ($http) {
  var members = [];

  this.getMembers = function () {
    return members;
  };

  this.setMember = function (data) {
    members = data;
  };

  this.fetchMembers = function () {
    return $http.get(apiMember).then(
      function (response) {
        if (response.status === 200) {
          members = response.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});
