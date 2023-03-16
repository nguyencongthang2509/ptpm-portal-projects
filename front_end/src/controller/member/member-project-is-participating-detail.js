window.MemberProjectIsParticipatingDetailController = function (
  $scope,
  $routeParams,
  MeDetailProjectService,
  MeMemberService,
  MeResourceService,
  MeGetAllPeriodById
) {
  let idProject = $routeParams.id;
  $scope.projectId = idProject;

  MeDetailProjectService.fetchProject(idProject).then(function () {
    $scope.detailProject = MeDetailProjectService.getProject();
  });

  MeMemberService.fetchMembers().then(function () {
    $scope.listMemberById = MeMemberService.getMembers();
  });

  MeResourceService.fetchResources(idProject).then(function () {
    $scope.listResource = MeResourceService.getResources();
  });

  MeGetAllPeriodById.fetchPeriods(idProject).then(function () {
    $scope.listPeriodById = MeGetAllPeriodById.getPeriods();
  });


  $scope.listViecCanLam = [{ name: "Item 1" }, { name: "Item 2" }, { name: "Item 3" }];

  $scope.listDangDienRa = [
    { name: "Item 1" },
    { name: "Item 2" },
    { name: "Item 3" },
    { name: "Item 1" },
    { name: "Item 2" },
  ];

  $scope.listCanSua = [
    { name: "Item 1" },
    { name: "Item 2" },
    { name: "Item 3" },
    { name: "Item 1" },
  ];

  $scope.listDaHoanThanh = [
    { name: "Item 1" },
    { name: "Item 2" },
    { name: "Item 2" },
    { name: "Item 3" },
  ];

  $scope.listTamHoan = [
    { name: "Item 1" },
    { name: "Item 3" },
    { name: "Item 2" },
    { name: "Item 3" },
  ];

  $scope.listTask = [
    {
      id: 1,
      name: "VIỆC CẦN LÀM",
      todoList: $scope.listViecCanLam,
      checkShowAddCard: false,
    },
    {
      id: 2,
      name: "ĐANG DIỄN RA",
      todoList: $scope.listDangDienRa,
      checkShowAddCard: false,
    },
    {
      id: 3,
      name: "CẦN SỬA",
      todoList: $scope.listCanSua,
      checkShowAddCard: false,
    },
    {
      id: 4,
      name: "ĐÃ HOÀN THÀNH",
      todoList: $scope.listDaHoanThanh,
      checkShowAddCard: false,
    },
    {
      id: 5,
      name: "TẠM HOÃN",
      todoList: $scope.listTamHoan,
      checkShowAddCard: false,
    },
  ];

  $scope.selected = null;
  $scope.fromList = null;
  $scope.taskEnd = null;

  $scope.onDrop = function (event, index, item, external, type, xx) {
    console.log("Item", item, "dropped into", xx);
    $scope.taskEnd = xx;
    $scope.indexItemEnd = index;
  };

  $scope.onDragStart = function (index, list) {
    $scope.indexFirst = index;
    $scope.fromList = list;
  };

  $scope.onDragEnd = function (item, xx, event, index) {
    if (xx.id == $scope.taskEnd.id) {
      const indexItem = xx.todoList.indexOf(item);
      let temp = item;
      if (indexItem === -1 || $scope.indexItemEnd === indexItem) {
        return;
      } else if ($scope.indexItemEnd > indexItem) {
        xx.todoList.splice(indexItem, 1);
        xx.todoList.splice($scope.indexItemEnd - 1, 0, temp);
      } else {
        xx.todoList.splice(indexItem, 1);
        xx.todoList.splice($scope.indexItemEnd, 0, temp);
      }
    } else {
      $scope.fromList.splice($scope.indexFirst, 1);
      $scope.taskEnd.todoList.splice($scope.indexItemEnd, 0, item);
    }
  };

  $scope.addACard = function (value) {
    $scope.listTask[value - 1].checkShowAddCard = true;
  };

  $scope.valueInput = "";

  $scope.addNewCard = function (value) {
    let listTitle = document.querySelectorAll('[name="inputTitle"]');
    $scope.listTask[value - 1].todoList.push({
      name: listTitle[value].value,
    });
    listTitle[value - 1].value = "";
    $scope.listTask[value - 1].checkShowAddCard = false;
  };

  $scope.addNewResource = function () {};
};
