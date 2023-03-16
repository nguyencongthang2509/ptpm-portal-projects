app.service("MeGetAllPeriodById", function ($http) {
  var periods = [];

  this.getPeriods = function () {
    return periods;
  };

  this.setPeriod = function (data) {
    periods = data;
  };

  this.fetchPeriods = function (idProject) {
    return $http.get(apiMemberGetAllPeriodByIdProject + "/" + idProject).then(
      function (response) {
        if (response.status === 200) {
          periods = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});

app.service("MeDetailPeriod", function ($http) {
  var period = [];

  this.getPeriod = function () {
    return period;
  };

  this.setPeriod = function (data) {
    period = data;
  };

  this.fetchPeriod = function (idPeriod) {
    return $http.get(apiMemberDetailPeriod + idPeriod).then(
      function (response) {
        if (response.status === 200) {
          period = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});

app.service("MeGetAllPeriod", function ($http) {
  var periods = [];
  var totalpages = 0;

  this.getPeriods = function () {
    return periods;
  };

  this.getTotalpages = function () {
    return totalpages;
  };

  this.setPeriod = function (data) {
    periods = data;
  };

  this.fetchPeriods = function (idProject) {
    return $http.get(apiMemberGetAllPeriod + idProject).then(
      function (response) {
        if (response.status === 200) {
          periods = response.data.data.data;
          totalpages = response.data.data.totalPages;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };

  this.searchPeriods = function (idProject, inputSearch) {
    return $http
      .get(apiMemberGetAllPeriod + idProject + "?namePeriod=" + inputSearch)
      .then(
        function (response) {
          if (response.status === 200) {
            periods = response.data.data.data;
            totalpages = response.data.data.totalPages;
          }
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
  };

  this.pagePeriod = function (idProject, inputSearch, currentPage) {
    return $http
      .get(
        apiMemberGetAllPeriod +
          idProject +
          "?namePeriod=" +
          inputSearch +
          "&page=" +
          currentPage
      )
      .then(
        function (response) {
          if (response.status === 200) {
            periods = response.data.data.data;
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
