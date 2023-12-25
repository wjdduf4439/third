//serializeArray data function, $("#frm").serializeArray() 구문의 json 형식의 문자열 만들기
function objectifyForm(formArray) {
	var returnArray = {};
	for (var i = 0; i < formArray.length; i++) {
		returnArray[formArray[i]['name']] = formArray[i]['value'];
	}
	return returnArray;
}
