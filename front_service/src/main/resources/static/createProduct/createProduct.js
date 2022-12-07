angular.module('market-front').controller('createProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:5555/core/api/v1';

    $scope.loadCategories = function () {
        $http.get(contextPath + '/categories/').then(function (response) {
            console.log(response);
            $scope.categories = response;
        })
    }
    $scope.createNewProduct = function () {
        if ($scope.new_product == null) {
            alert("The form is not completed to the end");
            return;
        }
        $http.post(contextPath + '/products/createProduct', $scope.new_product)
            .then(function successCallback(response) {
                $scope.new_product = null;
                alert("The product was successfully created");
                $location.path('/store');
            }, function failCallback(response) {
                alert(response.data.messages);
            });
    }
    $scope.loadCategories();
});
