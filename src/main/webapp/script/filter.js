var btnFilter = document.getElementById('btn-filter');
btnFilter.addEventListener('click', function(e) {startFilter(e)}, false);
function startFilter(e) {
	e.preventDefault();
	var queryString = window.location.href;
	var newQuery = 'product?';
	if(queryString.indexOf('?') > 0)
	{
		var params = queryString.split("?")[1].split("&");
	}
		
	else {
		var params = '';
	}

		
	for(var i = 0; i<params.length; i++) {
		if(params[i].match('categoryId=')) {
			newQuery += params[i] + '&';
		}
	}

	var elColor = document.getElementsByClassName('colorChooser');
	for(var i = 0; i<elColor.length; i++) {
		if(elColor[i].checked) {
			newQuery+= 'colorId=' + elColor[i].getAttribute('value') + '&';
		}
	}

	var elSize = document.getElementsByClassName('sizeChooser');
	for(var i = 0; i < elSize.length; i++) {
		if(elSize[i].checked) 
			newQuery += 'sizeId=' + elSize[i].getAttribute('value') + '&';
	}

	
	var maxPrice = document.getElementById('maxPrice').value;
	var minPrice = document.getElementById('minPrice').value;
	if(maxPrice > 0 && minPrice > 0 && maxPrice > minPrice) {
		newQuery += 'price=' + minPrice + ":" + maxPrice + '&';
	}

	newQuery = newQuery.substring(0, newQuery.length-1);
	console.log(newQuery);

	window.location.href = newQuery;
}

// btn close option
var btnFilterClose = document.getElementById('btn-filter-close');
btnFilterClose.addEventListener('click', function(e) {closeFilter(e);}, false);
function closeFilter(e) {
	e.preventDefault();
	var elOption = document.getElementById('filter-bar');
	elOption.setAttribute('class', 'deactivate');
}