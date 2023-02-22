app.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/trang-chu", {
      // templateUrl: "../src/pages/chi-tiet-don-hang.html",
      // controller: ChiTietDonHangController,
    })
    .otherwise({
      redirectTo: "/trang-chu",
    });
});
