
function clearSearch()
{
    window.location.href = window.location.origin + window.location.pathname;
}

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
		const data = {
			title : title.value,
			author : author.value,
			description : description.value,
			price : price.value,
			stockQuantity : stockQuantity.value,
			isbn : isbn.value
		};

		fetch(`/api/v1/books`, {
			method: 'POST',
			body: JSON.stringify(data),
			headers: {
				'Content-Type': 'application/json',
			},
		}).then(data => {
			return data.json();
		}).then(response => {
			if (response.statusCode === 201) {
				// 성공
				alert(response.message);
				const books = response.data;
				console.log("books.booksUID = " + books.booksUID); // 고유 번호
                window.location.href = window.location.origin + "/book/" + books.booksUID;
			}
			else
			{
				// 실패
				alert(response.error.message);
			}
		}).catch(error => {
			console.error('There was a problem with the fetch operation:', error);
		})
	}
}
// books Update API 
function booksUpdate(uid) {
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
        console.log(inputColumn);
        console.log(inputColumn.value);
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
		const data = {
			title : title.value,
			author : author.value,
			description : description.value,
			price : price.value,
			stockQuantity : stockQuantity.value,
			isbn : isbn.value
		};

		fetch(`/api/v1/books/`+uid, {
			method: 'PUT',
			body: JSON.stringify(data),
			headers: {
				'Content-Type': 'application/json',
			},
		}).then(data => {
			return data.json();
		}).then(response => {
			if (response.statusCode === 202) {
				// 성공
				alert(response.message);
				const books = response.data;
				console.log("books.booksUID = " + books.booksUID); // 고유 번호
                location.reload();
			}
			else
			{
				// 실패
				alert(response.error.message);
			}
		}).catch(error => {
			console.error('There was a problem with the fetch operation:', error);
		})
	}
}
async function deleteBook(uid)
{
    const p = prompt('정말 이 도서를 삭제 하시겠습니까?\r\n도서의 이름을 작성해 주세요.');
    if (!p)
    {
        return;
    }

    try
    {
        const data = {
            title : p
        };

        const response = await fetch(`/api/v1/books/${uid}`,
            {
                method: 'DELETE',
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json',
                },
            });

        const result = await response.json();
        if (result.data === uid && result.statusCode === 205)
        {
            alert(result.message);
            location.reload();
        }
        else
        {
            alert(result.error?.message || '삭제 실패');
        }
    }
    catch (error)
    {
        console.error('삭제 중 오류 발생!', error);
        alert('삭제 중 오류 발생');
    }
}