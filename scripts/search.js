var Spotify = require('spotify-web-api-js');
var s = new Spotify();

function onButtonClick() {
    const response = s.searchTracks(document.getElementById('searchfor').value);

    const h1 = document.createElement('h1');
    h1.innerHtml = response[0].name;
    document.getChildById('body').appendChild(h1);
}
