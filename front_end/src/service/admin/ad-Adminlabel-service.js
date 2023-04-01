app.service("AdViewLabelService", function ($http) {
    var labels = [];
    var totalpages = 0;
  
    this.getLabel = function () {
      return labels;
    };
  
    this.getTotalpages = function () {
      return totalpages;
    };
  
    this.setLabel = function (data) {
        labels = data;
    };
  
    this.fetchLabel = function () {
      return $http.get(admin_label).then(
        function (response) {
          if (response.status === 200) {
            labels = response.data.data.data;
            console.log(labels);
            totalpages = response.data.data.totalPages;
          }
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
    };
  
    this.searchLabel = function (inputSearch) {
      return $http.get(admin_label + "?name=" + inputSearch).then(
        function (response) {
          if (response.status === 200) {
            labels = response.data.data.data;
            totalpages = response.data.data.totalPages;
          }
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
    };
  
    this.pageLabel = function (inputSearch, currentPage) {
      return $http
        .get(
            admin_label +"?name=" + inputSearch + "&page=" + currentPage
        )
        .then(
          function (response) {
            if (response.status === 200) {
              labels = response.data.data.data;
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