// books List 조회 API 
function booksList() {
	protocolSendAjax(
		'GET',
		`/api/v1/books`,
		function(data) {
			console.log("success: ", data);
			
			data.forEach(item =>
			{
				console.log("item.title = " + item.title); // 제목
				console.log("item.author = " + item.author); // 저자
				console.log("item.description = " + item.description); // 내용
				console.log("item.price = " + item.price); // 가격
				console.log("item.stockQuantity = " + item.stockQuantity); // 재고
				console.log("item.isbn = " + item.isbn); // 바코드 번호
			});
		},
		function(error) {
			console.error(error);
		}
	);}
// books 고유 번호 조회 API 
function booksUID() {
	protocolSendAjax(
		'GET',
		`/api/v1/books/booksUID * booksUID 위치에 고유 번호 입력`,
		function(data) {
			console.log("success: ", data);
			
			const item = data.item;
			console.log("item.booksUID = " + item.booksUID); // 고유 번호
			console.log("item.title = " + item.title); // 제목
			console.log("item.author = " + item.author); // 저자
			console.log("item.description = " + item.description); // 내용
			console.log("item.price = " + item.price); // 가격
			console.log("item.stockQuantity = " + item.stockQuantity); // 재고
			console.log("item.isbn = " + item.isbn); // 바코드 번호
		},
		function(error) {
			console.error(error);
		}
	);}
// books Create API 
function books() {
	const title = document.getElementById("title");
	const author = document.getElementById("author");
	const description = document.getElementById("description");
	const price = document.getElementById("price");
	const stockQuantity = document.getElementById("stockQuantity");
	const isbn = document.getElementById("isbn");
	const arr = [title,author,description,price,stockQuantity,isbn];
	let bol = false;
	arr.some(inputColumn=>
	{
		if (inputColumn.value === '')
		{
			alert('Please enter your ' + inputColumn.id);
			bol = true;
			inputColumn.focus();
		}
		else
		{
			bol = false;
		}

		return bol;
	});

	if (!bol)
	{
		const jsonData = {
			title : title.value,
			author : author.value,
			description : description.value,
			price : price.value,
			stockQuantity : stockQuantity.value,
			isbn : isbn.value
		};

		protocolSendAjax(
			'POST',
			`/api/v1/books`,
			function(data) {
				console.log("success: ", data);
				
				const item = data.item;
			console.log("item.booksUID = " + item.booksUID); // 고유 번호
			},
			function(error) {
				console.error(error);
			}
			,jsonData
		);	}
}
