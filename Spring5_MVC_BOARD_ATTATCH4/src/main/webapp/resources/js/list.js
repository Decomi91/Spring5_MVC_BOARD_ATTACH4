function go(page){
	var limit = $('#viewcount').val();
	var data = "limit="+limit+"&state=ajax&page="+page;
	ajax(data);
}
//var data = {"page" : ${page}, "maxpage":${maxpage}, "startpage" : ${startpage}, "endpage" : ${endpage}, "listcount" : ${listcount}, "limit" : $(this).val(), "state":"ajax"};

function setPaging(href, digit){
	output += "<li class=page-item>";
	gray = "";
	if(href == ""){
		gray="gray";
	}
	anchor = "<a class='page-link " + gray + "' " + href + ">" + digit + "</a></li>";
	output += anchor;
}
function ajax(sdata){
	output="";
	$.ajax({
        method: "POST",
        data: sdata,
        url: "BoardList.bo",
		dataType : "json",
		cache:false,
		success : function(data){
			$('#viewcount').val(data.limit);
			$('table').find("font").text("글 개수 : " + data.listcount);
			if(data.listcount > 0){
				var num = data.listcount - (data.page - 1) * data.limit;
				console.log(num);
				
				$('tbody').empty();
				
				output += '<tr>'
					+'<th width="8%">번호</th>'
					+'<th width="50%"">제목</th>'
					+'<th width="14%">글쓴이</th>'
					+'<th width="17%">조회수</th>'
					+'<th width="11%">작성시간</th>'
					+'</tr>';
				
				$(data.boardlist).each(function(index, item){
					output += '<tr><td>' + (num--) + '</td>';
					blank_count = item.board_RE_LEV * 2 + 1;
					blank = '&nbsp;';
					for(var i=0; i<blank_count; i++) {
					    blank += '&nbsp;&nbsp;';
					}
					
					img="";
					if(item.BOARD_RE_LEV>0){
						img = "<img src='resources/image/AnswerLine.gif'>";
					}
					
					output += '<td><div>' + blank + img;
					output += '<a href="BoardDetailAction.bo?num='+ item.board_NUM + '&page=' + data.page + '">' + item.board_SUBJECT + '</a></div></td>';
					output +='<td><div>'+item.board_NAME+'</div></td>';
					output += '<td><div>'+item.board_READCOUNT+'</div></td>';
					output += '<td><div>'+item.board_DATE+'</div></td></tr>';
				});
				
				$('tbody').append(output);
				
				$(".pagination").empty();
				output = "";
				
				digit = '이전&nbsp;';
				href="";
				if(data.page > 1){
					href='href=javascript:go(' + (data.page - 1) + ')';
				}
				setPaging(href,digit);
						
				for(var i=data.startpage; i<=data.endpage; i++ ){
					digit = i;
					href="";
					if(i != data.page){
						href += 'href=javascript:go(' + i + ')';
					}
					setPaging(href, digit);
				};
				
				digit = "&nbsp;다음";
				href="";
				if(data.page < data.maxpage){
					href='href=javascript:go(' + (data.page+1) + ')';
				}
				setPaging(href, digit);
				
				$('.pagination').append(output);
			}else{
				$(".container table").remove();
				$(".center-block").remove();
				$(".container").append("<font size=5>등록된 글이 없습니다.</font>")
				
			}
		},
		error : function(){
			console.log('에러');
		}

    });
}

$(function(){
	$('#viewcount').change(function(){
		go(1);
	});
	
	$('#write').click(function(){
		location.href="BoardWrite.bo";
	});
});
