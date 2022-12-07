angular.module('market-front').controller('updateProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:5555/core/api/v1';

    $scope.loadCategories = function () {
        $http.get(contextPath + '/categories/').then(function (response) {
            console.log(response);
            $scope.categories = response;
        })
    }

    $scope.updateProduct = function () {
        console.log($scope.update_product);
        $http.put(contextPath + '/products/', $scope.update_product)
            .then(function successCallback(response) {
                $scope.update_product = null;
                alert("The product has been successfully updated!");
                $location.path('/store');
            }, function failCallback(response) {
                alert(response.data.messages);
            });
    }

    $scope.prepareProductForUpdate = function () {
        $http.get(contextPath + '/products/' + $routeParams.productId)
            .then(function successCallback(response) {
                $scope.update_product = response.data;
            }, function failCallback(response) {
                alert(response.data.messages);
                $location.path('/store');
            })
    }

    $scope.loadCategories();
    $scope.prepareProductForUpdate();
});
