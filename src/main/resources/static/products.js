angular.module('app', []).controller('productsController', function ($scope, $http) {

    $scope.loadProducts = function () {
            $http.get('http://localhost:8189/market/api/v1/products').then(function (response) {
                $scope.productsList = response.data;
            });
        }

    $scope.loadCart = function (){
        $http.get('http://localhost:8189/market/api/v1/cart').then(function (response){
           $scope.cart = response.data;
        });
    }

    $scope.addProductToCart = function (productId){
       $http.get('http://localhost:8189/market/api/v1/cart/add/'+ productId).then(function(response){
         $scope.loadCart();
       });
    }

    $scope.removeProductFromCart = function (productId, quantity){
       $http.get('http://localhost:8189/market/api/v1/cart/deleteProduct?productId='+ productId+"&quantity="+quantity).then(function (response) {
         $scope.loadCart();
       });
    }

    $scope.showProductInfo = function (productId) {
        $http.get('http://localhost:8189/market/api/v1/products/' + productId).then(function (response) {
            alert(response.data.title);
        });
    }

    $scope.deleteProductById = function (productId) {
        $http.delete('http://localhost:8189/market/api/v1/products/' + productId).then(function (response) {
            $scope.loadProducts();
        });
    }

   $scope.deleteCart = function(){
       $http.get('http://localhost:8189/market/api/v1/cart/delete').then(function (response) {
          $scope.loadCart();
       });
   }

   $scope.loadProducts();
   $scope.loadCart();

});