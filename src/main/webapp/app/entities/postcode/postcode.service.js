(function () {
    'use strict';
    angular
        .module('fexcoPostcodeApp')
        .factory('Postcode', Postcode);

    Postcode.$inject = ['$resource'];

    function Postcode($resource) {
        var resourceUrl = 'api/postcodes/:postcode';

        return $resource(resourceUrl, {}, {
            'query': {method: 'GET', isArray: true}
        });
    }
})();
