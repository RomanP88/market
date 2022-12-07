angular.module('market-front').controller('orderDetailsController', function ($scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:5555/cart/api/v1/cart/';
    let sizeListProduct;
    $scope.loadCart = function () {
        $http.get(contextPath + $localStorage.webGuestCartId)
            .then(function successCallback(response) {
                console.log(response);
                $scope.cart = response;
                sizeListProduct = response.data.items.length;
            }, function errorCallback(response) {
                console.log(response);
            });
    }

    $scope.sendOrder = function () {
        $http.post('http://localhost:5555/order/api/v1/orders/createOrder', $scope.detailsForOrder).then (function successCallback(response) {
            console.log(response);
            // $scope.clearCart();
            $location.path('/order_pay/' + response.data);
        }, function errorCallback(response) {
            console.log(response);
        });

    }

    $scope.isCartEmpty = function () {
        return sizeListProduct <= 0;
    };

    $scope.clearCart = function () {
        $http.get(contextPath + $localStorage.webGuestCartId + '/clear').then(function (response) {
            console.log(response);
            $scope.loadCart();
        });
    }


    $scope.incrementProduct = function (item) {
        $http.get(contextPath + $localStorage.webGuestCartId + '/add/' + item.productId).then(function (response) {
            console.log(response);
            $scope.loadCart();
        })
    }

    $scope.decrementProduct = function (item) {
        $http.get(contextPath + $localStorage.webGuestCartId + '/decrement/' + item.productId).then(function (response) {
            console.log(response);
            $scope.loadCart();
        })
    }

    $scope.deleteProduct = function (item) {
        $http.get(contextPath + $localStorage.webGuestCartId + '/remove/' + item.productId).then(function (response) {
            console.log(response);
            $scope.loadCart();
        });
    }
    $scope.loadCart();

});