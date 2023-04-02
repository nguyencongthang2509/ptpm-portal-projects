window.AdminLabelManagementController = function (
  $scope,
  $http,
  AdViewLabelService
) {
  document.body.style.backgroundImage = "url('" + "')";
  $scope.listLabel = [];
  //load data
  $scope.arrayPage = [];
  function loadDataListLabel() {
    AdViewLabelService.fetchLabel().then(function () {
      $scope.listLabel = AdViewLabelService.getLabel();
      $scope.totalPages = AdViewLabelService.getTotalpages();
      $scope.arrayPage = genArrayPage($scope.totalPages);
    });
  }

  loadDataListLabel();

  //search tên label
  $scope.input_search = "";
  $scope.search = function () {
    AdViewLabelService.searchLabel($scope.input_search).then(function () {
      $scope.listLabel = AdViewLabelService.getLabel();
      $scope.totalPages = AdViewLabelService.getTotalpages();
      $scope.arrayPage = genArrayPage($scope.totalPages);
    });
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
    AdViewLabelService.pageLabel($scope.input_search, currentPage).then(
      function () {
        $scope.listLabel = AdViewLabelService.getLabel();
        $scope.arrayPage = genArrayPage(AdViewLabelService.getTotalpages());
      }
    );
  };

  $scope.form_label = {
    id :"",
    code: "",
    name: "",
    colorLabel: "",
    statusLabel: "0",
  };

  $scope.setColor = function(color) {
    $scope.form_label.colorLabel = color;
    $scope.color = color;
    console.log($scope.form_label.colorLabel);
    
  };
  $scope.addlabel = function () {
    let api = admin_label;
    console.log($scope.form_label);
    console.log($scope.color);
    $http.post(api, $scope.form_label).then(
      function (response) {
        toastr.success("Thêm thành công", "Thông báo!", {
          closeButton: true,
          progressBar: true,
          positionClass: "toast-top-center",
        });
        $("#exampleModal_add_label").modal("hide");
        $scope.project_creat = response.data.data;
        loadDataListLabel();
      },
      function (error) {
        console.log(error);
        toastr.error(error.data.message, "Thông báo!", {
          closeButton: true,
          progressBar: true,
          positionClass: "toast-top-center",
        });
      }
    );
  };

  $scope.form_label_update = {
    id :"",
    code: "",
    name: "",
    colorLabel: "",
    statusLabel: "0",
  };
  $scope.indexUpdate = -1;
  $scope.detail = function(event,index){
    event.preventDefault();
    let label = $scope.listLabel[index];
    console.log(label);
    $scope.form_label_update.id = label.id;
    $scope.form_label_update.code = label.code;
    $scope.form_label_update.name = label.name;
    $scope.form_label_update.colorLabel = label.colorLabel;
    $scope.indexUpdate = index;
  };

  $scope.setColorUpadte = function(color) {
    $scope.form_label_update.colorLabel = color;
    $scope.color = color;
    
  };
  $scope.updateLabel = function (event) {
    event.preventDefault();
    let label = $scope.listLabel[ $scope.indexUpdate];
    let api = admin_label +"/"+label.id;
    $http.put(api , $scope.form_label_update).then(
      function (response) {
        toastr.success("Thêm thành công", "Thông báo!", {
          closeButton: true,
          progressBar: true,
          positionClass: "toast-top-center",
        });
        $("#exampleModal_update_label").modal("hide");
        $scope.project_creat = response.data.data;
        loadDataListLabel();
      },
      function (error) {
        console.log(error);
        toastr.error(error.data.message, "Thông báo!", {
          closeButton: true,
          progressBar: true,
          positionClass: "toast-top-center",
        });
      }
    );
  };
};
