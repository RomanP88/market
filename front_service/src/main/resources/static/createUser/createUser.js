angular.module('market-front').controller('createUserController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:5555/auth/api/v1';

    $scope.createNewUser = function () {
        if ($scope.new_user == null) {
            alert("The form is not completed to the end");
            return;
        }
        $scope.new_user.profile = $scope.profile;
        $http.post(contextPath + '/users/', $scope.new_user)
            .then(function successCallback(response) {
                $scope.new_user = null;
                alert("The user was successfully created");
                $location.path('/');
            }, function failCallback(response) {
                alert(response.data.messages);
            });
    }
});
