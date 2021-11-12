// Handle update or change request
$('button').on('click', function(event) {
	event.preventDefault();
	var target = $(this);

	var clothId = target.attr('data-clothId');
	var colorId = target.attr('data-colorId');
	var sizeId = target.attr('data-sizeId');
	if(!colorId)
		colorId = '""';
	if(!sizeId)
		sizeId = '""'; 
	var quantity= $('input[data-colorId='+colorId+'][data-clothId='+clothId+'][data-sizeId='+sizeId+']').val();

	if(colorId == '""')
		colorId = -1;
	if(sizeId == '""')
		sizeId = -1;
	
	var requestParam = {};
	requestParam.op = target.attr('data-op');
	requestParam.colorId = colorId;
	requestParam.clothId = clothId;
	requestParam.sizeId = sizeId;
	requestParam.quantity = quantity;

	$.ajax('/ClothStoreWebDevelopement/cartUpdate', {
		type: 'POST',
		timeout: 2000,
		data: $.param( requestParam ),

		success: function(data) {
			
			if(data == '200') {
				$('#responseModal .modal-title').text('Thông báo');
				$('#responseModal .modal-body').text('Giỏ hàng của bạn đã được cập nhật !');
				$('#responseModal').modal('show');
			}

			if(data == '400') {
				$('#responseModal .modal-title').text('Cảnh báo');
				$('#responseModal .modal-body').text('Đã xảy ra lỗi!');
				$('#responseModal').modal('show');
			}

			$('#responseModal button').on('click', function(e) {
				$('#responseModal').modal('hide');
				if(target.attr = 'delete') 
					window.location.href = "/ClothStoreWebDevelopement/cart";
			})
			
		}
	}) 
})


// Trigger change quantity 
$('input[type=number]').change(function(event) {
	var el = $(event.target);
	var newQuantity = Number(el.val());
	var parent = $(el.parent());
	var price = $(parent.next('td'));
	var total = $(price.next('td'));

	var pricer = Number(price.text());
	var newPrice = pricer * newQuantity;
	total.text(newPrice.toFixed(1));

	calTotal(event);
})

// Handle total price after load
window.addEventListener('load', function(e) {calTotal(e); }, false);

// Function call Total
function calTotal(e) {
	var elPrice = document.getElementsByClassName('price');
	var totalPrice = 0;
	for(var i = 0; i<elPrice.length; i++) {
		totalPrice += Number(elPrice[i].textContent);
	}
	$('#total-price').text(totalPrice.toFixed(1));
};

// Update all data-colorId and data-sizeId if they don't exist
function updateData(event) {
	var el = $('button[data-colorId=""]');
	for(var i = 0; i<el.length; i++) {
		console.log(el[i]);
	}
}