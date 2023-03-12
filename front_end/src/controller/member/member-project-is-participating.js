window.MemberProjectIsParticipatingController = function (
  $scope,
  MeProjectService
) {
  MeProjectService.fetchProjects().then(function () {
    $scope.listProjectById = MeProjectService.getProjects();
    console.log($scope.listProjectById);
  });


};
