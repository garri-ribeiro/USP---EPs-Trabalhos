angular.module('somDoClaudioApp', ['ui.router'])

.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('login');

    $stateProvider

    .state('login', {
        url:'/login',
        templateUrl: 'front/pages/login.html',
        controller: 'LoginController'
    })
    .state('app', {
        url:'/app',
        abstract: true,
        templateUrl: 'front/pages/app.html',
        controller  : 'AppController'
    })
    .state('app.home', {
        url:'/home/:userId',
        templateUrl: 'front/pages/home.html',
        controller: 'HomeController'
    })
    .state('app.profile', {
        url:'/profile/:userId',
        templateUrl: 'front/pages/profile/profile.html',
        controller: 'ProfileController'
    })
    .state('app.profileEdit', {
        url:'/profile/:userId/edit',
        templateUrl: 'front/pages/profile/profile-edit.html',
        controller: 'ProfileEditController'
    })

    .state('app.events', {
        url:'/events',
        templateUrl: 'front/pages/event/events.html',
        controller: 'EventController'
    })

    .state('app.eventView', {
        url:'/event/:eventId',
        templateUrl: 'front/pages/event/event-view.html',
        controller: 'EventController'
    })
    .state('app.eventCreate', {
        url:'/eventCreate',
        params: {
            type:'create'
        },
        templateUrl: 'front/pages/event/event-save.html',
        controller: 'EventSaveController'
    })
    .state('app.eventEdit', {
        url:'/event/edit/:eventId',
        params: {
            type:'edit'
        },
        templateUrl: 'front/pages/event/event-save.html',
        controller: 'EventSaveController'
    })

}])

.controller('AppController', function() {
})

.controller('LoginController', ['$scope',function($scope) {

}])
.controller('HomeController', ['$scope', '$http','$stateParams',function($scope, $http, $stateParams) {
    $scope.openPost = false;

    $scope.open = function(){
        $scope.openPost = true;
    }

    $scope.cancel = function(){
        $scope.openPost = false;
    }

    $http.get('back/posts').then(function success(response) {
        $scope.posts = response.data;
		console.log("RESPONSE", response.data);
    });

    $scope.savePost = function(){
        console.log("UE");
        $http.post('back/post/user/'+$stateParams.userId, $scope.post).then(function success(){
            console.log("DEU BOM, MEU BOM");
        })
        $scope.openPost = false;
    }

}])
.controller('ProfileController', ['$scope', '$http', '$stateParams','$state','$timeout',function($scope, $http, $stateParams,$state,$timeout) {

    $http.get('back/posts/user/'+$stateParams.userId).then(function success(response) {
        $scope.posts = response.data;
        //console.log("RESPONSE", response.data);
    });

	$http.get('back/relacionamento/'+$stateParams.userId+'/1').then(function success(response) {
        $scope.relacionamento = response.data[0];
        //console.log("RESPONSE", response.data);
    });

    $http.get('back/user/'+$stateParams.userId).then(function success(response) {
        $scope.user = response.data[0];
		//console.log("RESPONSE", response);
    });

	$scope.solicitar = function() {
        //$http.put('back/relacionamento/solicitar/'+$stateParams.userId, $scope.user).then(function success(){
            $http.post('back/relacionamento/solicitar/1/'+$stateParams.userId).then(function success(){
			})
			$timeout({},300);
			$state.reload();
    }

	$scope.excluir = function() {
        //$http.put('back/relacionamento/solicitar/'+$stateParams.userId, $scope.user).then(function success(){
            $http.delete('back/relacionamento/excluir/1/'+$stateParams.userId).then(function success(){
        })
		$timeout({},300);
		$state.reload();
    }
}])

.controller('ProfileEditController', ['$scope', '$http', '$stateParams',function($scope, $http, $stateParams) {

    $http.get('back/user/'+$stateParams.userId).then(function success(response) {
        $scope.user = response.data[0];
    });

    $scope.saveProfile = function() {
        $http.put('back/user/'+$stateParams.userId, $scope.user).then(function success(){
            console.log("DEU BOM");
        })
    }

}])

.controller('EventController', ['$scope', '$http', '$stateParams','$state',function($scope, $http, $stateParams,$state) {

    $http.get('back/events').then(function success(response) {
        $scope.events = response.data;
    });

    $scope.deleteEvent = function(id){
        $http.delete('back/event/'+id).then(function success(){
            console.log("DEU BOM DELECAO");
        })
        $state.reload()
    }

}])

.controller('EventSaveController', ['$scope', '$http', '$stateParams','$state',function($scope, $http, $stateParams, $state) {

    if ($stateParams.type == "edit") {
        $http.get('back/event/'+$stateParams.eventId).then(function success(response) {
            $scope.event = response.data[0];
            console.log("EVENTO RESOTA", $scope.event);
        });
    }

    $scope.saveEvent = function(item) {
        $scope.event.IDusuario = 1;
        var date = new Date($scope.event.data);
        // console.log("DATA", date.toISOString().slice(0, 10).replace('T', ' '));
        $scope.event.eventoData = date.toISOString().slice(0, 10).replace('T', ' ');
        if ($stateParams.type == "edit") {
            $http.put('back/event/'+$stateParams.eventId, $scope.event).then(function success(){
                console.log("DEU BOM EDICAO");
            })
        } else {
            $http.post('back/event/create', $scope.event).then(function success(){
                console.log("DEU BOM CRICAO");
                $state.go('app.events')
            })
        }
    }

}])
