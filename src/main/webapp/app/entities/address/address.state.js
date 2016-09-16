(function () {
    'use strict';

    angular
        .module('fexcoPostcodeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('address', {
                parent: 'entity',
                url: '/address',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Addresses'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/address/addresses.html',
                        controller: 'AddressController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {}
            })
            .state('address-detail', {
                parent: 'entity',
                url: '/address/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Address'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/address/address-detail.html',
                        controller: 'AddressDetailController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Address', function ($stateParams, Address) {
                        return Address.get({id: $stateParams.id}).$promise;
                    }],
                    previousState: ["$state", function ($state) {
                        var currentStateData = {
                            name: $state.current.name || 'address',
                            params: $state.params,
                            url: $state.href($state.current.name, $state.params)
                        };
                        return currentStateData;
                    }]
                }
            })
            .state('address-detail.edit', {
                parent: 'address-detail',
                url: '/detail/edit',
                data: {
                    authorities: ['ROLE_USER']
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'app/entities/address/address-dialog.html',
                        controller: 'AddressDialogController',
                        controllerAs: 'vm',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            entity: ['Address', function (Address) {
                                return Address.get({id: $stateParams.id}).$promise;
                            }]
                        }
                    }).result.then(function () {
                        $state.go('^', {}, {reload: false});
                    }, function () {
                        $state.go('^');
                    });
                }]
            })
            .state('address.new', {
                parent: 'address',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER']
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'app/entities/address/address-dialog.html',
                        controller: 'AddressDialogController',
                        controllerAs: 'vm',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    addressline1: null,
                                    addressline2: null,
                                    addressline3: null,
                                    summaryline: null,
                                    organisation: null,
                                    buildingname: null,
                                    premise: null,
                                    street: null,
                                    dependentlocality: null,
                                    posttown: null,
                                    county: null,
                                    postcode: null,
                                    number: null,
                                    pobox: null,
                                    departmentname: null,
                                    subbuildingname: null,
                                    dependentstreet: null,
                                    doubledependentlocality: null,
                                    recodes: null,
                                    morevalues: null,
                                    nextpage: null,
                                    totalresults: null,
                                    latitude: null,
                                    longitude: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function () {
                        $state.go('address', null, {reload: 'address'});
                    }, function () {
                        $state.go('address');
                    });
                }]
            })
            .state('address.edit', {
                parent: 'address',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER']
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'app/entities/address/address-dialog.html',
                        controller: 'AddressDialogController',
                        controllerAs: 'vm',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            entity: ['Address', function (Address) {
                                return Address.get({id: $stateParams.id}).$promise;
                            }]
                        }
                    }).result.then(function () {
                        $state.go('address', null, {reload: 'address'});
                    }, function () {
                        $state.go('^');
                    });
                }]
            })
            .state('address.delete', {
                parent: 'address',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER']
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'app/entities/address/address-delete-dialog.html',
                        controller: 'AddressDeleteController',
                        controllerAs: 'vm',
                        size: 'md',
                        resolve: {
                            entity: ['Address', function (Address) {
                                return Address.get({id: $stateParams.id}).$promise;
                            }]
                        }
                    }).result.then(function () {
                        $state.go('address', null, {reload: 'address'});
                    }, function () {
                        $state.go('^');
                    });
                }]
            });
    }

})();
