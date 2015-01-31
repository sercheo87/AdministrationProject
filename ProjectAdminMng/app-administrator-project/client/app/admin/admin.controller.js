'use strict';

angular.module('appAdministratorProjectApp')
    .controller('AdminCtrl', function($scope, $http, $log, $translate, $filter, resourcesService, growl) {
        $scope.project = {
            name: '',
            description: '',
            dateStart: new Date(2015, 1, 1),
            duration: ''
        };

        $scope.typesResources = [{
            id: 1,
            name: 'Humano',
        }, {
            id: 2,
            name: 'Tecnologico'
        }];

        $scope.projectSave = function() {
            $log.info($scope.formProject);
            $scope.formProject.$setError('name', 'Unknown error!');
            growl.warning("Override global ttl setting", {});
        };

        $scope.projectCancel = function() {
            $scope.project.description = 'eeee';
        };

        resourcesService.getAllResources(function(results) {
            $scope.collectionResources = angular.fromJson(results);
        });
        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };
        $scope.showTypeResource = function(typeResource) {
            var selected = [];
            if (typeResource.typeResource) {
                selected = $filter('filter')($scope.typesResources, {
                    id: typeResource.typeResource.id
                });
            }
            return selected.length ? selected[0].name : 'Not set';
        };

        $scope.addResource = function() {
            $scope.inserted = {
                id: $scope.collectionResources.length + 1,
                name: '',
                typeResource: null
            };
            $scope.collectionResources.push($scope.inserted);
        };

        $scope.saveResource = function(itemResource) {
            resourcesService.saveResource(itemResource);
        };

    });