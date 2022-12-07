angular.module('market-front').controller('ordersController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:5555/order/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'api/v1/orders/findAllOrdersByUsername')
            .then(function (response) {
                $scope.MyOrders = response.data;
                console.log(response);
            });
    }

    $scope.goToPay = function (orderId) {
        $location.path('/order_pay/' + orderId);
    }
    $scope.isPaid = function (status) {
        return status === 'Оплачен';

    }
    $scope.loadOrders();
});