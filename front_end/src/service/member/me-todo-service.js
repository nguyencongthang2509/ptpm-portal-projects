app.service("MeTodoService", function ($http) {
  var todo = [];

  this.getTodo = function () {
    return todo;
  };

  this.setTodo = function (data) {
    todo = data;
  };

  this.fetchTodo = function (idPeriod, statusTodo) {
    return $http
      .get(
        apiMemberTodo + "?idPeriod=" + idPeriod + "&statusTodo=" + statusTodo
      )
      .then(
        function (response) {
          if (response.status === 200) {
            todo = response.data.data;
          }
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
  };
});

app.service("MeDetailTodoService", function ($http) {
  var todo = [];
  var todoDetail = [];

  this.getTodo = function () {
    return todo;
  };

  this.getTodoDetail = function () {
    return todoDetail;
  };

  this.setTodo = function (data) {
    todo = data;
  };

  this.fetchTodo = function (id) {
    return $http
      .get(
        apiMemberTodo + "/" + id
      )
      .then(
        function (response) {
          if (response.status === 200) {
            todo = response.data.data;
          }
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
  };

  this.fetchDetailTodo = function (id) {
    return $http
      .get(
        apiMemberTodo + "/detail/" + id
      )
      .then(
        function (response) {
          if (response.status === 200) {
            todoDetail = response.data.data;
          }
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
  };
});
