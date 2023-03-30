window.AdminProjectManagementDetailController = function (
  $scope,
  $http,
  $location,
  AdProjcetService
) {

  document.body.style.backgroundImage = "url('" + "')";
  
  $scope.listProject = [];
  $scope.form_project = {
    code: "",
    name: "",
    descriptions: "",
    startTime: "",
    endTime: "",
    progress: 0,
    statusProject: "1",
  };

  // lấy hết dữ liệu ra
  AdProjcetService.fetchProject().then(function () {
    $scope.listProject = AdProjcetService.getProject();
  });

  // detail project
  $scope.detail = function (event, index) {
    event.preventDefault();
    let project = $scope.listProject[index];
    console.log(project);
    $scope.form_project.code = project.code;
    $scope.form_project.name = project.name;
    $scope.form_project.startTime = project.startTime;
    $scope.form_project.endTime = project.endTime;
    $scope.form_project.progress = project.progress;
    $scope.form_project.statusProject = project.statusProject;
    $scope.form_project.descriptions = project.descriptions;
  };
};
