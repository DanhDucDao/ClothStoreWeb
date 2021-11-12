// handle login request
$('input[type=submit]').on('click', function(event) {
	event.preventDefault();
	var isLegit = checkFillInput();
	if(isLegit) {
		
	}
} )

function checkFillInput() {
	var username = $('input[name=username]').val();
	var password = $('input[name=password]').val();
	var isLegit = true;
	if(username == '') {
		alert('Tên không được bỏ trống')
		return false;
	}
	
	if(password == '') {
		$('#passwordHint').removeClass('text-muted').addClass('text-danger');
		$('#passwordHint').text('Tên không được bỏ trống!')
		return false;
	}
}
