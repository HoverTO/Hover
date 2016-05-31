/**
 * Created by Malik on 2016-05-21.
 */
app.controller('MainController', ['$scope', 'forecast', function($scope, forecast) {
    forecast.success(function(data) {
        $scope.Matches = data;
    });
}]);
