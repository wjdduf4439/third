function fn_checked(m){
	
		var placeRowVal = '';
		var placeWidthVal = '';
		
		var placeRowArr = [];
		var placeWidthArr = [];
		
		$("input:checkbox[name=placeRow]:checked").each(function(i){
			
			if(this.value != m){
				//체크하지 않은 부분의 width정보를 모아서 별도로 배열에 보관
				console.log(this.value + " - " + m);
				
				placeRowVal += this.value;
				placeRowVal += ',';
				
				//console.log($('#placeWidthOption'+i+'').val());
				placeWidthVal += $('#placeWidthOption'+this.value+'').val();
				placeWidthVal += ',';	
				
			}else{
				//체크한 부분은 공백값이 당연하기 때문에 배열 내에 빈 공간을 생성
				placeRowVal += ',';
				placeWidthVal += ',';
				
			}
			
		});
		placeRowVal = placeRowVal.substring(0,placeRowVal.length-1);
		placeWidthVal = placeWidthVal.substring(0,placeWidthVal.length-1);
		
		var placeRowArr = placeRowVal.split(",");
		var placeWidthArr = placeWidthVal.split(",");
		
		console.log("placeRowVal : " + placeRowVal);
		console.log("placeWidthVal : " + placeWidthVal);

	$("#lengthInput").empty();

	var template = '';
	
	$("input:checkbox[name=placeRow]:checked").each(function(i){ 
		
		template += '<input  type="number" id="placeWidthOption'+this.value+'" name="placeWidthOption'+this.value+'" class ="width150" value="" placeholder="속성'+(i+1)+'" /> </br>';
		
	})
	
	$("#lengthInput").append(template);
	
	//insert 시 체크박스를 다시 만들고 값을 다시 배열하는 작업
	
	$("input:checkbox[name=placeRow]:checked").each(function(i){
		
		if(this.value == placeRowArr[i]){
			
			console.log(this.value + " : " + placeRowArr[i]);
			$('#placeWidthOption'+this.value+'').val(placeWidthArr[i]);
			
		}
		
	});
	
	//추가한 체크박스의 값을 서로 매칭시켜 체크박스 추가를 시켜도 width 값이 유지되도록 함, update시 result값을 고정값으로 받고 template에 적용함
	//row와 width 값을 매칭시키는 이중배열 생성
	//새로운 row 를 체크했을 시 해당 배열에는 값을 미배정
	//if index i+2 == placerowwidth[i]의 값	
		
}

function fn_fileUploadTypeChecked(m){
	
	let fileUploadTypeVal = '';
	let fileUploadTypeArr = [];
	
	$("input:checkbox[name=fileUploadType_chkbox]:checked").each(function(i){ fileUploadTypeVal +=  this.value; fileUploadTypeVal +=  ',' });
	fileUploadTypeVal = fileUploadTypeVal.substring(0,fileUploadTypeVal.length-1);
	$('#fileUploadType').val(fileUploadTypeVal);
	
}