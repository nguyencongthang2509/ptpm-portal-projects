window.MemberProjectIsParticipatingController = function (
  $scope,
  MeProjectService
) {

  document.body.style.backgroundImage = "url('" + "')";

  MeProjectService.fetchProjects().then(function () {
    $scope.listProjectById = MeProjectService.getProjects();
    console.log($scope.listProjectById);
  });
};
