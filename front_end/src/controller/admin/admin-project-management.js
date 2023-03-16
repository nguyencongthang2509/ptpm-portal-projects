window.AdminProjectManagementController = function (
  $scope,
  $http,
  $location,
  AdProjcetService
) {
  $scope.listProject = [];
  $scope.indexProject = -1;
  $scope.form_project = {
    id: "",
    code: "",
    name: "",
    descriptions: "",
    startTime: "",
    endTime: "",
    progress: 0,
    statusProject: "1",
  };

  $scope.form_project_update = {
    id: "",
    code: "",
    name: "",
    descriptions: "",
    startTime: "",
    endTime: "",
    progress: 0,
    statusProject: "1",
  };

  // load data
  function loadData() {
    // lấy hết dữ liệu ra
    AdProjcetService.fetchProject().then(function () {
      $scope.listProject = AdProjcetService.getProject();
    });
  }

  loadData();

  $scope.openModalAdd = function () {
    $scope.form_project.name = "";
    $scope.form_project.code = "";
    $scope.form_project.descriptions = "";
    $scope.form_project.startTime = "";
    $scope.form_project.endTime = "";
  };
  // thêm project
  $scope.addProject = function () {
    console.log($scope.form_project);
    $http
      .post(projcetAPI, {
        code: $scope.form_project.code,
        name: $scope.form_project.name,
        descriptions: $scope.form_project.descriptions,
        startTime: $scope.form_project.startTime.getTime(),
        endTime: $scope.form_project.endTime.getTime(),
        progress: $scope.form_project.progress,
        statusProject: $scope.form_project.statusProject,
      })
      .then(
        function (response) {
          toastr.success("Thêm thành công", "Thông báo!", {
            closeButton: true,
            progressBar: true,
            positionClass: "toast-top-center",
          });
          $("#exampleModal").modal("hide");
          loadData();
        },
        function (error) {
          toastr.error(error.data.message, "Thông báo!", {
            closeButton: true,
            progressBar: true,
            positionClass: "toast-top-center",
          });
        }
      );
  };

  // detail
  $scope.detail = function (event, index) {
    event.preventDefault();
    let project = $scope.listProject[index];
    console.log(project);
    $scope.form_project_update.id = project.id;
    $scope.form_project_update.code = project.code;
    $scope.form_project_update.name = project.name;
    $scope.form_project_update.startTime = new Date(project.startTime);
    $scope.form_project_update.endTime = new Date(project.endTime);
    $scope.form_project_update.progress = project.progress;
    $scope.form_project_update.statusProject = project.statusProject;
    $scope.form_project_update.descriptions = project.descriptions;
    $scope.indexProject = index;
  };

  $scope.Update = function (event) {
    event.preventDefault();
    let projectId = $scope.form_project_update.id;
    console.log(projectId);
    let api = projcetAPI + "/" + projectId;
    $http
      .put(api, {
        code: $scope.form_project_update.code,
        name: $scope.form_project_update.name,
        descriptions: $scope.form_project_update.descriptions,
        startTime: $scope.form_project_update.startTime.getTime(),
        endTime: $scope.form_project_update.endTime.getTime(),
        progress: $scope.form_project_update.progress,
        statusProject: $scope.form_project_update.statusProject,
      })
      .then(
        function (response) {
          console.log(response);
          $scope.listProject[$scope.indexProject] == response.data;
        },
        function (error) {
          console.log(error);
        }
      );
  };

  // tìm kiếm tên dự án
  $scope.searchProjectByName = function (event) {
    event.preventDefault();
    $scope.listProject = [];
    alert("okkkkkkkkkk");
    $http.get(projcetAPI + "/search").then(
      function (response) {
        if (response.status === 200) {
          $scope.listProject = response.data;
        }
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
};
