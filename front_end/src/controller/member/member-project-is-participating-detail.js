window.MemberProjectIsParticipatingDetailController = function (
  $scope,
  $routeParams,
  MeDetailProjectService,
  MeMemberService
) {
  let idProject = $routeParams.id;

  MeDetailProjectService.fetchProject(idProject).then(function () {
    $scope.detailProject = MeDetailProjectService.getProject();
  });

  MeMemberService.fetchMembers().then(function () {
    $scope.listMemberById = MeMemberService.getMembers();
  });

  $scope.items = [
    { name: "Item 1" },
    { name: "Item 2" },
    { name: "Item 3" },
    { name: "Item 1" },
    { name: "Item 2" },
    { name: "Item 3" },
    { name: "Item 1" },
    { name: "Item 2" },
    { name: "Item 3" },
  ];

  $scope.selected = null;
};
