angular.module('market-front').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/cart/api/v1/cart/';
    let sizeListProduct;
    $scope.loadCart = function () {
        $http.get(contextPath + $localStorage.webGuestCartId)
            .then(function successCallback(response) {
                console.log(response);
                $scope.cart = response;
                sizeListProduct = response.data.items.length;
                console.log(sizeListProduct);
            }, function errorCallback(response) {
                console.log(response);
            });
    }

    $scope.createOrder = function () {
        console.log($localStorage.webMarketUser)
        if ($localStorage.webMarketUser != null) {
            window.location = "#!/orderDetails";
            // window.open("#!/orderDetails")//обе операции выполняют одинаковую функцию
        } else {
            alert("You need to log in!")
        }


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
        $http.headers.append()
        $http.get(contextPath + $localStorage.webGuestCartId + '/remove/' + item.productId).then(function (response) {
            console.log(response);
            $scope.loadCart();
        });
    }
    $scope.loadCart();

});