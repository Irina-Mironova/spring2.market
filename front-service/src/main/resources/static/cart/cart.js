angular.module('market').controller('cartController', function ($rootScope,$scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/cart/';
    const coreContextPath = 'http://localhost:5555/core/';

    $scope.loadCart = function () {
        $http.get(contextPath + 'api/v1/cart').then(function (response) {
            $scope.cart = response.data;
        });
    }


    $scope.deleteCart = function () {
        $http.get(contextPath + 'api/v1/cart/clear').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.removeProductFromCart = function (productId, quantity){
       $http.get(contextPath + 'api/v1/cart/remove?productId='+ productId+"&quantity="+quantity).then(function (response) {
         $scope.loadCart();
       });
    }

     $scope.addProductToCart = function (productId){
       $http.get(contextPath + 'api/v1/cart/add/' + productId).then(function (response) {
          $scope.loadCart();
       });
     }

    $scope.createOrder = function(){
      $http.post(coreContextPath + 'api/v1/orders', $scope.user).then(function (response) {
         alert("Заказ оформлен");
         $scope.loadCart();
      });
    }

    $rootScope.isUserLoggedIn = function () {
            if ($localStorage.productMarketUser) {
                return true;
            } else {
                return false;
            }
        };

    $scope.loadCart();

});