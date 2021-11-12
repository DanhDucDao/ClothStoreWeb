function calTotal(e) {
	var elPrice = document.getElementsByClassName('price');
	var totalPrice = 0;
	for(var i = 0; i<elPrice.length; i++) {
		totalPrice += Number(elPrice[i].textContent);
	}
	$('#total-price').text(totalPrice.toFixed(1));
};

// cal total cash when page loaded
window.addEventListener('load', function(e) {calTotal(e);}, false);

//return to shopping 
$('#orderForm button').on('click', function(e) {
	e.preventDefault();
	window.location.href = "/ClothStoreWebDevelopement/product";
})

//Handle action performed
$('input[type=submit]').on('click', function(event) {
	event.preventDefault();

	var isLegit = checkInputFill();
	if(isLegit == true) {
		var name = $('#nameInput').val();
		var email = $('#emailInput').val();
		var phone = $('#phoneInput').val();
		var address = $('#addressInput').val();
		var subDistrict = $('#subDistrictInput').val();
		var district = $('#districtInput').val();
		var city = $('#cityInput').val();
		var payMethod = $('#payMethod').val();

		var requestParam = {};
		requestParam.name = name;
		requestParam.email = email;
		requestParam.phone = phone;
		requestParam.street = address;
		requestParam.subDistrict = subDistrict;
		requestParam.district = district;
		requestParam.city = city;
		requestParam.payMethod = payMethod;
		sendToServer(requestParam);
	}
})

function checkInputFill() {
	var isLegit = true;

	var name = $('#nameInput').val();
	var email = $('#emailInput').val();
	var phone = $('#phoneInput').val();
	var address = $('#addressInput').val();
	var subDistrict = $('#subDistrictInput').val();
	var district = $('#districtInput').val();
	var city = $('#cityInput').val();
	var payMethod = $('#payMethod').val();
	if(name == '') {
		isLegit = false;
		$('#nameHint').removeClass('text-muted').addClass('text-danger');
		$('#nameHint').text('Tên không được bỏ trống!')
	}
	if(email == '') {
		isLegit = false;
		$('#emailHint').removeClass('text-muted').addClass('text-danger');
		$('#emailHint').text('Email Không được bỏ trống!')
	}
	if(phone == '') {
		isLegit = false;
		$('#phoneHint').removeClass('text-muted').addClass('text-danger');
		$('#phoneHint').text('Số điện thoại không được bỏ trống!')
	}
	if(address == '') {
		isLegit = false;
		$('#addressHint').removeClass('text-muted').addClass('text-danger');
		$('#addressHint').text('Địa chỉ không được bỏ trống!')
	}
	if(subDistrict == '') {
		isLegit = false;
		$('#subDistrictHint').removeClass('text-muted').addClass('text-danger');
		$('#subDistrictHint').text('Phường xã không được bỏ trống!')
	}
	if(district == '') {
		isLegit = false;
		$('#districtHint').removeClass('text-muted').addClass('text-danger');
		$('#districtHint').text('Quận Huyện không được bỏ trống!')
	}
	if(name == '') {
		isLegit = false;
		$('#cityHint').removeClass('text-muted').addClass('text-danger');
		$('#cityHint').text('Tỉnh thành phố không được bỏ trống!')
	}
	if($('input[name=pay]:checked').length == 0) {
		isLegit = false;
		$('#payHint').removeClass('text-muted').addClass('text-danger');
		$('#payHint').text('Bắt buộc chọn phương thức thanh toán!')
	}
	
	var trCount = $('tr').length;
	if(trCount<=1) {
		isLegit = false;
		$('#responseModal .modal-title').text('Thông báo');
		$('#responseModal .modal-body').text('Giỏ đồ của bạn trống !');
		$('#responseModal').modal('show');
	}

	return isLegit;
}

function sendToServer(requestParam) {
	$.ajax('/ClothStoreWebDevelopement/checkout', {
		type: 'POST',
		timeout: 2000,
		data: $.param( requestParam ),
		
		success: function (data) {
			var message = JSON.parse(data);
			if(message == '-1') {
				$('#responseModal .modal-title').text('Thông báo');
				$('#responseModal .modal-body').text('Có lỗi xảy ra!');
				$('#responseModal').modal('show');
			}
			else {
				$('#responseModal .modal-title').text('Thông báo');
				$('#responseModal .modal-body').text('Đã lưu vào cơ sở dữ liệu! Mã đơn hàng của bạn là ' + message);
				$('#responseModal').modal('show');
			}

			$('#responseModal button').on('click', function(e) {
				$('#responseModal').modal('hide');
				window.location.href = "/ClothStoreWebDevelopement/product";
			})
			
		}
	})
}