app.service("MeTodoListService", function ($http) {
  var todoList = [];

  this.getTodoList = function () {
    return todoList;
  };

  this.setTodoList = function (data) {
    todoList = data;
  };

  this.fetchTodoList = function (idProject) {
    return $http.get(apiMemberTodoList + "/" + idProject).then(
      function (response) {
        if (response.status === 200) {
          todoList = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});
