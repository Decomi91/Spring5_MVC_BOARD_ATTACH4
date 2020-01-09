$(document).ready(function(){
	$('form').submit(function(){
		if($.trim($('input:eq(1)').val()) == ""){
			alert("비밀번호 입력 필요");
			$('input:eq(1)').focus();
			return false;
		}
		
		if($.trim($('input:eq(2)').val()) == ""){
			alert("글제목 입력 필요");
			$('input:eq(2)').focus();
			return false;
		}
		
		if($.trim($('textarea').val()) == ""){
			alert("내용 입력 필요");
			$('textarea').focus();
			return false;
		}
	});
	
	$("#upfile").change(function(){
    	$('#filevalue').val('');
    	console.log($(this).val());
    	var inputfile=$(this).val().split('\\');
    	$('#filevalue').text(inputfile[inputfile.length-1]);
    });
		
});