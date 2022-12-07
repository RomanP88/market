angular.module('market-front').controller('statisticController', function ($scope, $http, $localStorage) {
    let stompClient = null;

    function connect() {
        let socket = new SockJS('http://localhost:5555/core/ws');
        console.log(socket);
        stompClient = Stomp.over(socket);
        console.log(stompClient);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/getFileAboutProducts', function (response) {
                console.log(response.body);
                $localStorage.isCreatedReport = response.body;
                $localStorage.linkForDownloadReport = contextPath + '/getFileAboutProducts';
                location.reload();
                disconnect();
            });
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/getFileAboutOrders', function (response) {
                console.log(response.body);
                $localStorage.isCreatedReport = response.body;
                $localStorage.linkForDownloadReport = contextPath + '/getFileAboutOrders';
                location.reload();
                disconnect();
            });
        });
    }

    function disconnect() {
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    const contextPath = 'http://localhost:5555/core/api/v1/statistic';
    $scope.loadStatistic = () => {
        $http.get(contextPath + '/')
            .then(function (response) {
                console.log(response);
                $scope.statistic = response.data;
            });
    }
    $scope.getFileProducts = function () {
        stompClient.send('/app/giveMeFileAboutProducts');
    }
    // $scope.getFileOrders = function () {
    //     stompClient.send('/app/giveMeFileAboutOrders');
    // }
    if ($localStorage.isCreatedReport == null || $localStorage.isCreatedReport === false) {
        connect();
    }

    $scope.loadStatistic();
});
