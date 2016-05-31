app.factory('forecast', ['$http', function(json) {
    return json.get('/Users/Malik/Documents/Engineering Science/[D]congestant/Hover_Website/Hover/Hover_Test/src/user_database.json')
        .success(function(data) {
            return data;
        })
        .error(function(err) {
            return err;
        });
}]);/**
 * Created by Malik on 2016-05-21.
 */
