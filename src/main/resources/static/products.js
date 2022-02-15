angular.module('app', []).controller('productsController', function ($scope, $http) {
    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/market/api/v1/products').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.loadProductsInCart = function (){
        $http.get('http://localhost:8189/market/api/v1/cart').then(function (response){
           $scope.cartProductList = response.data;
        });
    }

    $scope.addProductToCart = function (productId){
       $http.get('http://localhost:8189/market/api/v1/cart/'+ productId).then(function(response){
         $scope.cartProductList = response.data;
       });
    }

    $scope.deleteProductFromCart = function (productId){
       $http.delete('http://localhost:8189/market/api/v1/cart/'+ productId).then(function (response) {
         $scope.loadProductsInCart();
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

    $scope.loadProducts();
    $scope.loadProductsInCart();

});