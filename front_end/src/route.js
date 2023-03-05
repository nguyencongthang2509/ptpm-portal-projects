app.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/trang-chu", {
      templateUrl: "./pages/trang-chu.html",
      // controller: ChiTietDonHangController,
    })
    .when("/pages", {
      templateUrl: "./pages/pages.html",
      // controller: ChiTietDonHangController,
    })
    .otherwise({
      redirectTo: "/trang-chu",
    });
});
