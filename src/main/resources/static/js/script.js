const toggleSidebar = () => {
	if ($(".sidebar").is(":visible")) {

		$(".sidebar").css("display", "none");
		$(".content").css("margin-left", "0%");
	} else {

		$(".sidebar").css("display", "block");
		$(".content").css("margin-left", "20%");
	}
}

const search = () => {
	let query = $("#search-input").val();
	if (query == "") {
		$(".search-result").hide();
	} else {
		console.log("else block");
		let url = `http://localhost:8080/search/${query}`;
		fetch(url)
			.then((response) => {
				return response.json();
			})
			.then((data) => {

				let text = `<div class='list-group'>`;
				data.forEach((contact) => {
					text += `<a href='/user/contact/${contact.cId}' class='list-group-item list-group-item-action'> ${contact.name} </a>`
				});

				text += `</div>`;
				$(".search-result").html(text);
				$(".search-result").show();
			});
	}
}









//for razer pay integration

const paymentStart = () => {

	console.log("payment started");

	var amount = $("#payment_field").val();
	console.log(amount);
	if (amount == '' || amount == null) {
		alert("amount is required");
		return;
	}

	//ajax for sendig order req to the server

	$.ajax(
		{
			url: "/librarian/create_order",
			data: JSON.stringify({ amount: amount, info: "order_request" }),
			contentType: "application/json",
			type: "POST",
			dataType: "json",
			success: function(response) {
				//invoke if success
				console.log(response)

				if (response.status == "created") {
					//opening the payment form

					var options = {
						"key": "rzp_test_MxDm4phYN6zBXl",
						"amount": response.amount,
                        "currency": "INR",
						"name": "MY LSM APP",
						"description": "Donation for lsm",
						"image": "",
						"order_id": response.id,
						
					"handler": function (response) {
						console.log(response.razorpay_payment_id);
						console.log(response.razorpay_order_id);
						console.log(response.razorpay_signature);
						alert("payment successful")
					},
					"prefill": {
						"name": "",
							"email": "",
								"contact": ""
					},
					"notes": {
						"address": "Library Managemeny System"
 
					},
					"theme": {
						"color": "#3399cc"
					}
				};
				let rpc = new Razorpay(options);
				rpc.on('payment.failed', function(response) {
					console.log(response.error.code);
					console.log(response.error.description);
					console.log(response.error.source);
					console.log(response.error.step);
					console.log(response.error.reason);
					console.log(response.error.metadata.order_id);
					console.log(response.error.metadata.payment_id);
					alert("payment failed")
				});
				
				rpc.open();
				
			}
		},
		error: function(error) {
			//invoked if any arror
			console.log(error)
			alert("something went wrong")
		}
			}
	)

}




















