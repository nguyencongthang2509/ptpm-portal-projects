window.MemberPeriodProjectController = function (
  $scope,
  $http,
  $routeParams,
  MeDetailProjectService,
  MeGetAllPeriod,
  MeDetailPeriod
) {
  document.body.style.backgroundImage = "url('" + "')";
  let idProject = $routeParams.id;
  $scope.projectId = idProject;

  $scope.arrayPage = [];

  MeDetailProjectService.fetchProject(idProject).then(function () {
    $scope.detailProject = MeDetailProjectService.getProject();
  });

  function loadDataListPeriod() {
    MeGetAllPeriod.fetchPeriods(idProject).then(function () {
      $scope.listPeriod = MeGetAllPeriod.getPeriods();
      $scope.totalPages = MeGetAllPeriod.getTotalpages();
      $scope.arrayPage = genArrayPage($scope.totalPages);
    });
  }

  loadDataListPeriod();

  $scope.input_search = "";

  $scope.search = function () {
    MeGetAllPeriod.searchPeriods(idProject, $scope.input_search).then(
      function () {
        $scope.listPeriod = MeGetAllPeriod.getPeriods();
        $scope.totalPages = MeGetAllPeriod.getTotalpages();
        $scope.arrayPage = genArrayPage($scope.totalPages);
      }
    );
  };

  function genArrayPage(value) {
    let array = [];
    for (let i = 1; i <= value; i++) {
      array.push(i);
    }
    return array;
  }

  $scope.currentPage = 0;

  $scope.actionPage = function (currentPage) {
    $scope.currentPage = currentPage;
    MeGetAllPeriod.pagePeriod(idProject, $scope.input_search, currentPage).then(
      function () {
        $scope.listPeriod = MeGetAllPeriod.getPeriods();
        $scope.arrayPage = genArrayPage(MeGetAllPeriod.getTotalpages());
      }
    );
  };

  $scope.actionDetailPeriod = function (event, id) {
    event.preventDefault();
    MeDetailPeriod.fetchPeriod(id).then(function () {
      $scope.detailPeriod = MeDetailPeriod.getPeriod();
      $scope.detailPeriod.progress = $scope.detailPeriod.progress + " %";
      $scope.detailPeriod.startTime = new Date(
        $scope.detailPeriod.startTime
      ).toLocaleString();
      $scope.detailPeriod.endTime = new Date(
        $scope.detailPeriod.endTime
      ).toLocaleString();
    });
  };

  $scope.clearFormAdd = function () {
    $scope.tenGiaiDoanAdd = "";
    $scope.moTaAdd = "";
    $scope.ngayBatDauAdd = "";
    $scope.ngayKetThucAdd = "";
    $scope.mucTieuAdd = "";
    $scope.errorNamePeriodAdd = "";
    $scope.errorNgayBatDauAdd = "";
    $scope.errorNgayKetThucAdd = "";
  };

  $scope.actionAdd = function () {
    let check = 0;
    if ($scope.tenGiaiDoanAdd === "") {
      $scope.errorNamePeriodAdd = "Tên giai đoạn không được để trống";
      ++check;
    } else {
      $scope.errorNamePeriodAdd = "";
    }

    if ($scope.ngayBatDauAdd === "") {
      $scope.errorNgayBatDauAdd = "Ngày bắt đầu không được để trống";
      ++check;
    } else if (
      new Date($scope.ngayBatDauAdd).getTime() >
      new Date($scope.ngayKetThucAdd).getTime()
    ) {
      $scope.errorNgayBatDauAdd =
        "Ngày bắt đầu không được lớn hơn ngày kết thúc";
      ++check;
    } else {
      $scope.errorNgayBatDauAdd = "";
    }

    if ($scope.ngayKetThucAdd === "") {
      $scope.errorNgayKetThucAdd = "Ngày kết thúc không được để trống";
      ++check;
    } else {
      $scope.errorNgayKetThucAdd = "";
    }

    if (check == 0) {
      let dateTimeNgayBatDauAdd = new Date($scope.ngayBatDauAdd);
      let yearNgayBatDau = dateTimeNgayBatDauAdd.getFullYear();
      let monthNgayBatDau = String(
        dateTimeNgayBatDauAdd.getMonth() + 1
      ).padStart(2, "0");
      let dateNgayBatDau = String(dateTimeNgayBatDauAdd.getDate()).padStart(
        2,
        "0"
      );
      let hoursNgayBatDau = String(dateTimeNgayBatDauAdd.getHours()).padStart(
        2,
        "0"
      );
      let minutesNgayBatDau = String(
        dateTimeNgayBatDauAdd.getMinutes()
      ).padStart(2, "0");
      let secondsNgayBatDau = String(
        dateTimeNgayBatDauAdd.getSeconds()
      ).padStart(2, "0");

      let newFormatNgayBatDau = `${yearNgayBatDau}-${monthNgayBatDau}-${dateNgayBatDau} ${hoursNgayBatDau}:${minutesNgayBatDau}:${secondsNgayBatDau}`;

      let dateTimeNgayKetThucAdd = new Date($scope.ngayKetThucAdd);
      let yearNgayKetThuc = dateTimeNgayKetThucAdd.getFullYear();
      let monthNgayKetThuc = String(
        dateTimeNgayKetThucAdd.getMonth() + 1
      ).padStart(2, "0");
      let dateNgayKetThuc = String(dateTimeNgayKetThucAdd.getDate()).padStart(
        2,
        "0"
      );
      let hoursNgayKetThuc = String(dateTimeNgayKetThucAdd.getHours()).padStart(
        2,
        "0"
      );
      let minutesNgayKetThuc = String(
        dateTimeNgayKetThucAdd.getMinutes()
      ).padStart(2, "0");
      let secondsNgayKetThuc = String(
        dateTimeNgayKetThucAdd.getSeconds()
      ).padStart(2, "0");

      let newFormatNgayKetThuc = `${yearNgayKetThuc}-${monthNgayKetThuc}-${dateNgayKetThuc} ${hoursNgayKetThuc}:${minutesNgayKetThuc}:${secondsNgayKetThuc}`;

      let periodAdd = {
        name: $scope.tenGiaiDoanAdd,
        descriptions: $scope.moTaAdd,
        startTime: newFormatNgayBatDau,
        endTime: newFormatNgayKetThuc,
        target: $scope.mucTieuAdd,
        projectId: idProject,
      };

      $http.post(apiMemberGetAllPeriodByIdProject, periodAdd).then(
        function (response) {
          loadDataListPeriod();
          toastr.success("Thêm thành công", "Thông báo!", {
            closeButton: true,
            progressBar: true,
            positionClass: "toast-top-center",
          });
          $("#modal_show_add").modal("hide");
        },
        function (error) {
          toastr.error(error.data.message, "Thông báo!", {
            closeButton: true,
            progressBar: true,
            positionClass: "toast-top-center",
          });
        }
      );
    }
  };

  $scope.openModalUpdate = function (event, id) {
    event.preventDefault();
    $scope.idPeriodUpdate = id;
    MeDetailPeriod.fetchPeriod(id).then(function () {
      let updatePeriod = MeDetailPeriod.getPeriod();
      updatePeriod.progress = updatePeriod.progress + " %";
      let ngayBatDau = new Date(updatePeriod.startTime);
      let ngayKetThuc = new Date(updatePeriod.endTime);

      $scope.tenGiaiDoanUpdate = updatePeriod.name;
      $scope.moTaUpdate = updatePeriod.descriptions;
      $scope.ngayBatDauUpdate = new Date(
        ngayBatDau.toLocaleDateString("en-CA") +
          ", " +
          ngayBatDau.toLocaleTimeString()
      );
      $scope.ngayKetThucUpdate = new Date(
        ngayKetThuc.toLocaleDateString("en-CA") +
          ", " +
          ngayKetThuc.toLocaleTimeString()
      );
      $scope.mucTieuUpdate = updatePeriod.target;
    });
  };

  $scope.actionUpdate = function () {
    let check = 0;
    if ($scope.tenGiaiDoanUpdate === "") {
      $scope.errorNamePeriodUpdate = "Tên giai đoạn không được để trống";
      ++check;
    } else {
      $scope.errorNamePeriodUpdate = "";
    }

    if ($scope.ngayBatDauUpdate === "") {
      $scope.errorNgayBatDauUpdate = "Ngày bắt đầu không được để trống";
      ++check;
    } else if (
      new Date($scope.ngayBatDauUpdate).getTime() >
      new Date($scope.ngayKetThucUpdate).getTime()
    ) {
      $scope.errorNgayBatDauUpdate =
        "Ngày bắt đầu không được lớn hơn ngày kết thúc";
      ++check;
    } else {
      $scope.errorNgayBatDauUpdate = "";
    }

    if ($scope.ngayKetThucUpdate === "") {
      $scope.errorNgayKetThucUpdate = "Ngày kết thúc không được để trống";
      ++check;
    } else {
      $scope.errorNgayKetThucUpdate = "";
    }

    if (check == 0) {
      let dateTimeNgayBatDauUpdate = new Date($scope.ngayBatDauUpdate);
      let yearNgayBatDau = dateTimeNgayBatDauUpdate.getFullYear();
      let monthNgayBatDau = String(
        dateTimeNgayBatDauUpdate.getMonth() + 1
      ).padStart(2, "0");
      let dateNgayBatDau = String(dateTimeNgayBatDauUpdate.getDate()).padStart(
        2,
        "0"
      );
      let hoursNgayBatDau = String(
        dateTimeNgayBatDauUpdate.getHours()
      ).padStart(2, "0");
      let minutesNgayBatDau = String(
        dateTimeNgayBatDauUpdate.getMinutes()
      ).padStart(2, "0");
      let secondsNgayBatDau = String(
        dateTimeNgayBatDauUpdate.getSeconds()
      ).padStart(2, "0");

      let newFormatNgayBatDau = `${yearNgayBatDau}-${monthNgayBatDau}-${dateNgayBatDau} ${hoursNgayBatDau}:${minutesNgayBatDau}:${secondsNgayBatDau}`;

      let dateTimeNgayKetThucUpdate = new Date($scope.ngayKetThucUpdate);
      let yearNgayKetThuc = dateTimeNgayKetThucUpdate.getFullYear();
      let monthNgayKetThuc = String(
        dateTimeNgayKetThucUpdate.getMonth() + 1
      ).padStart(2, "0");
      let dateNgayKetThuc = String(
        dateTimeNgayKetThucUpdate.getDate()
      ).padStart(2, "0");
      let hoursNgayKetThuc = String(
        dateTimeNgayKetThucUpdate.getHours()
      ).padStart(2, "0");
      let minutesNgayKetThuc = String(
        dateTimeNgayKetThucUpdate.getMinutes()
      ).padStart(2, "0");
      let secondsNgayKetThuc = String(
        dateTimeNgayKetThucUpdate.getSeconds()
      ).padStart(2, "0");

      let newFormatNgayKetThuc = `${yearNgayKetThuc}-${monthNgayKetThuc}-${dateNgayKetThuc} ${hoursNgayKetThuc}:${minutesNgayKetThuc}:${secondsNgayKetThuc}`;

      let periodUpdate = {
        id: $scope.idPeriodUpdate,
        name: $scope.tenGiaiDoanUpdate,
        descriptions: $scope.moTaUpdate,
        startTime: newFormatNgayBatDau,
        endTime: newFormatNgayKetThuc,
        target: $scope.mucTieuUpdate,
        projectId: idProject,
      };

      $http
        .put(
          apiMemberGetAllPeriodByIdProject + "/" + periodUpdate.id,
          periodUpdate
        )
        .then(
          function (response) {
            loadDataListPeriod();
            toastr.success("Cập nhật thành công", "Thông báo!", {
              closeButton: true,
              progressBar: true,
              positionClass: "toast-top-center",
            });
            $("#modal_show_update").modal("hide");
          },
          function (error) {
            toastr.error(error.data.message, "Thông báo!", {
              closeButton: true,
              progressBar: true,
              positionClass: "toast-top-center",
            });
          }
        );
    }
  };
};
