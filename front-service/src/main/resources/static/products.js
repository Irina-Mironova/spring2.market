angular.module('app', ['ngStorage']).controller('productsController', function ($scope, $http, $localStorage) {
   $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.productMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

   $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
   };

   $scope.clearUser = function () {
        delete $localStorage.productMarketUser;
        $http.defaults.headers.common.Authorization = '';
   };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.productMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.authCheck = function () {
            $http.get('http://localhost:5555/auth/auth_check').then(function (response) {
                alert(response.data.value);
            });
        };


    if ($localStorage.productMarketUser) {
            try {
                let jwt = $localStorage.productMarketUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.productMarketUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }

            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.productMarketUser.token;
        }


    $scope.loadProducts = function () {
            $http.get('http://localhost:5555/core/api/v1/products').then(function (response) {
                $scope.productsList = response.data;
            });
    }

    $scope.loadCart = function (){
        $http.get('http://localhost:5555/cart/api/v1/cart').then(function (response){
           $scope.cart = response.data;
        });
    }

    $scope.addProductToCart = function (productId){
       $http.get('http://localhost:5555/cart/api/v1/cart/add/'+ productId).then(function(response){
         $scope.loadCart();
       });
    }

    $scope.removeProductFromCart = function (productId, quantity){
       $http.get('http://localhost:5555/cart/api/v1/cart/remove?productId='+ productId+"&quantity="+quantity).then(function (response) {
         $scope.loadCart();
       });
    }

    $scope.showProductInfo = function (productId) {
        $http.get('http://localhost:5555/core/api/v1/products/' + productId).then(function (response) {
            alert(response.data.title);
        });
    }

    $scope.deleteProductById = function (productId) {
        $http.delete('http://localhost:5555/core/api/v1/products/' + productId).then(function (response) {
            $scope.loadProducts();
        });
    }

   $scope.deleteCart = function(){
       $http.get('http://localhost:5555/cart/api/v1/cart/clear').then(function (response) {
          $scope.loadCart();
       });
   }

   $scope.createOrder = function(){
          $http.post('http://localhost:5555/core/api/v1/orders', $scope.user).then(function (response) {
             $scope.loadCart();
             alert("Заказ оформлен");
          });
      }

   $scope.filter = function(){
             $http.post('http://localhost:5555/core/api/v1/products', $scope.user).then(function (response) {
                $scope.productsList = response.data;
             });
         }


   $scope.loadProducts();
   $scope.loadCart();

});