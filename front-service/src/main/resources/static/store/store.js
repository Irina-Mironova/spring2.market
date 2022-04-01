angular.module('market').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
    const cartContextPath = 'http://localhost:5555/cart/';
    $scope.currentPage = 1;
    totalPages = 1;

        $scope.loadProducts = function (pageIndex) {
            if (pageIndex != $scope.currentPage) {
               $scope.currentPage = pageIndex;
            }
            $http.post(contextPath + 'api/v1/products?page=' + pageIndex,$scope.user).then(function (response) {
               $scope.productsList = response.data.content;
               totalPages = response.data.totalPages;
               $scope.paginationArray = $scope.generatePagesIndexes(1, totalPages);
            });
        }

        $scope.generatePagesIndexes = function (startPage, endPage) {
            let arr = [];
            for (let i = startPage; i < endPage + 1; i++) {
                arr.push(i);
            }
            return arr;
        }

        $scope.isPreviousPage = function () {
            return ($scope.currentPage == 1) ? false : true;
        }

        $scope.isNextPage = function () {
            return ($scope.currentPage == totalPages) ? false : true;
        }

        $scope.addProductToCart = function (productId){
            $http.get(cartContextPath + 'api/v1/cart/add/' + productId).then(function (response) {
               $scope.loadCart();
            });
        }

        $scope.showProductInfo = function (productId) {
            $http.get(contextPath + 'api/v1/products/' + productId).then(function (response) {
                alert(response.data.title);
            });
        }

        $scope.deleteProductById = function (productId) {
            $http.delete(contextPath + 'api/v1/products/' + productId).then(function (response) {
                $scope.loadProducts();
            });
        }

       $scope.filter = function(){
           $scope.currentPage = 1;
           $scope.loadProducts($scope.currentPage);
       }

       $scope.loadProducts($scope.currentPage);
});