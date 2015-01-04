'use strict';

angular.module('appAdministratorProjectApp')
    .controller('AdminCtrl', function($scope, $http, $log, $translate, $filter, resourcesService) {
        $scope.project = [];
        $scope.project.name = '';
        $scope.project.description = '';
        $scope.project.dateStart = '';
        $scope.project.duration = '';

        $scope.typesResources = [{
            id: 1,
            name: 'Humano',
        }, {
            id: 2,
            name: 'Tecnologico'
        }];

        $scope.projectSave = function() {
            $scope.project.description = 'rrr';
        };

        $scope.projectCancel = function() {
            $scope.project.description = 'eeee';
        };

        resourcesService.getAllResources(function(results) {
            $scope.collectionResources = angular.fromJson(results);
        });

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