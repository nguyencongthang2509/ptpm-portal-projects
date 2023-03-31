app.service("MeTodoService", function ($http) {
  var todo = [];

  this.getTodo = function () {
    return todo;
  };

  this.setTodo = function (data) {
    todo = data;
  };

  this.fetchTodo = function (idPeriod, idTodoList) {
    return $http
      .get(
        apiMemberTodo + "?idPeriod=" + idPeriod + "&idTodoList=" + idTodoList
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
    return $http.get(apiMemberTodo + "/" + id).then(
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
    return $http.get(apiMemberTodo + "/detail/" + id).then(
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

app.service("ConvertLongToDateString", function () {
  let result = "";
  let resultMonthDay = ""

  this.setMonthDay = function (dateString) {
    const date = new Date(dateString);
    const monthNames = [
      "Jan",
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "Jul",
      "Aug",
      "Sep",
      "Oct",
      "Nov",
      "Dec",
    ];
    const day = date.getDate();
    const monthIndex = date.getMonth();
    const monthName = monthNames[monthIndex];
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const formattedTime = `${monthName} ${day}`;
    resultMonthDay = formattedTime;
    return formattedTime;
  };

  this.setDateString = function (dateString) {
    const date = new Date(dateString);
    const monthNames = [
      "Jan",
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "Jul",
      "Aug",
      "Sep",
      "Oct",
      "Nov",
      "Dec",
    ];
    const day = date.getDate();
    const monthIndex = date.getMonth();
    const monthName = monthNames[monthIndex];
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const formattedHours = hours.toString().padStart(2, "0");
    const formattedMinutes = minutes.toString().padStart(2, "0");
    const formattedTime = `${monthName} ${day} at ${formattedHours}:${formattedMinutes}`;
    result = formattedTime;
    return formattedTime;
  };

  this.getDateString = function () {
    return result;
  };

  this.getMonthDay = function () {
    return resultMonthDay;
  };
});
