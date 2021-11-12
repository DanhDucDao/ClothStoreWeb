var queryString = window.location.href;
if(queryString.indexOf('?') > 0)
	{
	var params = queryString.split("?")[1].split("&");
	}
else
	var params = '';
var newQuery;


if(document.getElementById('previousPage')) {
	var elPre = document.getElementById('previousPage');
	newQuery = queryString.split("?")[0] + '?';
	for(var i = 0; i < params.length; i++) {
		if(params[i].match('page=')) {
		var pageIndex = params[i].replace('page=', '');
		var previousIndex = pageIndex - 1;
		newQuery += 'page=' + previousIndex + '&';
		}
		else {
			newQuery += params[i] + "&";
		}
	}
	newQuery = newQuery.substr(0, newQuery.length-1);

	elPre.setAttribute('href', newQuery);
}

if(document.getElementById('nextPage')) {
	var elPre = document.getElementById('nextPage');
	newQuery = queryString.split("?")[0] + '?';
	for(var i = 0; i < params.length; i++) {
		if(params[i].match('page=')) {
		var pageIndex = params[i].replace('page=', '');
		var previousIndex = ++pageIndex;
		newQuery += 'page=' + previousIndex + '&';
		}
		else {
			newQuery += params[i] + "&";
		}
	}

	if(newQuery.match('page=') == null) {
		newQuery += 'page=2' + '&';
	}
	newQuery = newQuery.substr(0, newQuery.length-1);

	elPre.setAttribute('href', newQuery);
}

// display filter
var btnOpenFilter = document.getElementById('btn-open-filter');
console.log(btnOpenFilter)
btnOpenFilter.addEventListener('click', displayFilterOption, false);
function displayFilterOption() {
	var elOption = document.getElementById('filter-bar');
	elOption.setAttribute('class', 'active');

}
