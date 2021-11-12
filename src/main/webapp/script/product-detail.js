// click color -> change image
var elColorChoosers = document.getElementsByClassName('colorChooser');
for (var i = 0; i < elColorChoosers.length; i++) {
	elColorChoosers[i].addEventListener('click', function (e) { changeImage(e, i); }, false);
}

function changeImage(e, index) {
	var elChosen = e.target;
	var imageUrl = elChosen.getAttribute('data-image');
	var elMainImage = document.getElementById('mainImage');
	elMainImage.setAttribute('src', imageUrl);
}

// JQuery for submit form
$('#actionForm').on('submit', function (e) {
	e.preventDefault();

	if ($('input[name=color]').length > 0) {
		if ($('input[name=color]:checked').length < 1) {
			alert('Choose at least one color');
			return;
		}
	}


	if ($('input[name=size]').length > 0) {
		if ($('input[name=size]:checked').length < 1) {
			alert('Choose at least one size');

			return;
		}
	}

	var details = $('#actionForm').serialize();
	$.ajax({
		type: 'POST',
		url: '/ClothStoreWebDevelopement/addToCart',
		timeout: 2000,
		data: details,

		success: function (data) {
			$('#responseModal .modal-title').text('Thông báo');
			$('#responseModal .modal-body').text('Đã thêm vào giỏ hàng');
			$('#responseModal').modal('show');
		}
	})
})