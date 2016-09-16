(function () {
    'use strict';

    angular
        .module('fexcoEircodeApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register($resource) {
        return $resource('api/register', {}, {});
    }
})();
