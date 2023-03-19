window.MemberProjectIsParticipatingDetailController = function (
  $scope,
  $routeParams,
  MeDetailProjectService,
  MeMemberService,
  MeResourceService,
  MeGetAllPeriodById,
  MeTodoService,
  MeAssignService,
  MeLabelService,
  MeDetailTodoService
) {
  let idProject = $routeParams.id;
  $scope.projectId = idProject;
  // Get All
  async function fetchAllData(idProject) {
    await Promise.all([
      MeDetailProjectService.fetchProject(idProject),
      MeMemberService.fetchMembers(),
      MeResourceService.fetchResources(idProject),
      MeGetAllPeriodById.fetchPeriods(idProject),
    ]);

    $scope.detailProject = MeDetailProjectService.getProject();
    $scope.listMemberById = MeMemberService.getMembers();
    $scope.listResource = MeResourceService.getResources();
    $scope.listPeriodById = MeGetAllPeriodById.getPeriods();

    let periodCurrent = $scope.listPeriodById.filter((pe) => {
      return pe.status == 1;
    })[0];
    if (periodCurrent) {
      $scope.valuePeriod = periodCurrent.id;
    } else {
      $scope.valueInput = "";
    }
    $scope.loadDataTodo(periodCurrent.id);
  }

  fetchAllData(idProject);

  $scope.loadDataTodo = function (idPeriod) {
    $scope.listTask = [
      {
        id: 1,
        name: "VIỆC CẦN LÀM",
        todoList: [],
        checkShowAddCard: false,
      },
      {
        id: 2,
        name: "ĐANG DIỄN RA",
        todoList: [],
        checkShowAddCard: false,
      },
      {
        id: 3,
        name: "CẦN SỬA",
        todoList: [],
        checkShowAddCard: false,
      },
      {
        id: 4,
        name: "ĐÃ HOÀN THÀNH",
        todoList: [],
        checkShowAddCard: false,
      },
      {
        id: 5,
        name: "TẠM HOÃN",
        todoList: [],
        checkShowAddCard: false,
      },
    ];

    Promise.all(
      $scope.listTask.map((item) => {
        return MeTodoService.fetchTodo(idPeriod, item.id - 1).then(function () {
          item.todoList = MeTodoService.getTodo();
          if (item.todoList != []) {
            const assignPromises = item.todoList.map((td) => {
              return MeAssignService.fetchMember(td.id).then(function () {
                td.members = [];
                const idMembers = MeAssignService.getMembers();
                idMembers.forEach((meId) => {
                  const member = $scope.listMemberById.find(
                    (me) => meId === me.id
                  );
                  if (member) {
                    td.members.push(member);
                  }
                });
              });
            });

            const labelPromises = item.todoList.map((td) => {
              return MeLabelService.fetchLabel(td.id).then(function () {
                td.labels = MeLabelService.getLabels();
              });
            });

            return Promise.all([...assignPromises, ...labelPromises]);
          }
        });
      })
    );
  };

  $scope.changePeriod = function () {
    $scope.loadDataTodo($scope.valuePeriod);
  };

  // Drag And Drop

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

  // Add card

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

  //Detail Todo

  $scope.actionDetailTodo = function (id) {
    MeDetailTodoService.fetchTodo(id).then(function () {
      $scope.detailTodo = MeDetailTodoService.getTodo();
      $scope.detailTodo.listMemberDetailTodo = [];
      $scope.detailTodo.listLabelDetailTodo = [];
      MeAssignService.fetchMember(id).then(function () {
        const idMembers = MeAssignService.getMembers();
        idMembers.forEach((meId) => {
          const member = $scope.listMemberById.find((me) => meId === me.id);
          if (member) {
            $scope.detailTodo.listMemberDetailTodo.push(member);
          }
        });
      });
      MeLabelService.fetchLabel(id).then(function () {
        $scope.detailTodo.listLabelDetailTodo = MeLabelService.getLabels();
      });
    });
  };
};
