(function () {
    'use strict';
    angular
        .module('fexcoPostcodeApp')
        .factory('AddressByPostcode', AddressByPostcode);

    AddressByPostcode.$inject = ['$resource'];

    function AddressByPostcode($resource) {
        var resourceUrl = 'api/addresses/postcode/:postcode';

        return $resource(resourceUrl, {}, {
            'query': {method: 'GET', isArray: true}
        });
    }
})();
