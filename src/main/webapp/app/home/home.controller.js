(function () {
    'use strict';

    angular
        .module('fexcoPostcodeApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'AddressByPostcode', 'AlertService', 'ParseLinks', 'Postcode'];

    function HomeController($scope, Principal, LoginService, $state, AddressByPostcode, AlertService, ParseLinks, Postcode) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.search = search;
        vm.loadPage = loadPage;
        vm.getPostcodesSuggestions = getPostcodesSuggestions;
        vm.addresses = [];

        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function (account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }

        function register() {
            $state.go('register');
        }

        function search() {
            vm.addresses = [];
            loadPage(0)
        }

        function loadAll() {

            AddressByPostcode.query({
                page: vm.page,
                size: 20,
                postcode: vm.postcode,
                sort: sort()
            }, onSuccess, onError);

            function sort() {
                var result = ['id'];
                return result;
            }

            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                for (var i = 0; i < data.length; i++) {
                    vm.addresses.push(data[i]);
                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function loadPage(page) {
            vm.page = page;
            if (vm.postcode) {
                loadAll();
            }
        }

        function getPostcodesSuggestions(val) {
            if (val) {
                return Postcode.query({postcode: val}).$promise;
            }
        }
    }
})();
