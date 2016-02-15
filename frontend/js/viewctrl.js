function attendChecked(onTarget,offTarget,ischecked){
   if( ischecked == true ) {
      // チェックが入っていたら有効化
      console.log("test");
      document.getElementById(onTarget).disabled = false;
      document.getElementById(offTarget).disabled = true;
   }
   else {
      // チェックが入っていなかったら無効化
      console.log("test1");
      document.getElementById(onTarget).disabled = true;
      document.getElementById(offTarget).disabled = false;
   }
}

$(function init() {
  $.ajax({
    url: 'http://localhost/api/eatdata/respondent',
    dataType: 'jsonp', // 追加
    type: "GET",
    success: function(res) {
      console.log(res);
      console.log(res.name);
      console.log($('table#list td#test').text());
      $('table#list td#test').text(res.name);
      addRowToBottom();
//      $('#test').val('satoshi@email.com');
    },
    error: function(xhr, status ,err){
      console.log(xhr);
      console.log('status' + status);
      console.log('err' + err);
    }
  });
});


$(function(){
  $('#myModal').on('initialize',function(event,initObj){
    $('#modalHead').text(initObj.head);
    $('#modalBody').text(initObj.body);
  });
});

$(function(){
  $('#myModal').on('show',function(){
    var initObj = {
//      head: $('#headText').val(),
//      body: $('#bodyText').val()
      head: $('#headText').val(),
      body: $('#bodyText').val()

    };
    $('#myModal').trigger('initialize',[initObj]);
  });
});

//$('#myModal').modal('show');

var array = [{"no" : 1 , "name" : "山田", "status" : "参加","comment" : "アレルギーあり"},
		{"no" : 2 , "name" : "田中", "status" : "欠席","comment" : "モチベ低下"},
		{"no" : 3 , "name" : "鈴木", "status" : "未定","comment" : "美味しいものがあるなら行きます"},
		{"no" : 4 , "name" : "川口", "status" : "参加","comment" : "ボーリング楽しみです♪"}];


function updateStatus()
{
  var selectVal1 = $("#select_status").val();
  var selectVal2 = $("#comment_text").val();
  alert(selectVal1 + selectVal2);
  alert('test');
}

function addRowToBottom()
{
//	$('#listbody tr').remove();

	for(i = 0; i < array.length; i++)
	{
		var data = array[i];
		var tr = $('<tr/>');
		$('<td/>').text(data.no).appendTo(tr);
		$('<td/>').text(data.name).appendTo(tr);
    $('<td/>').text(data.status).appendTo(tr);
    $('<td/>').text(data.comment).appendTo(tr);
//    $('<td/>').append('<input type="radio" name="attend1" value="1" checked>参加する<input type="radio" name="attend1" value="2" >欠席する').appendTo(tr);
//    $('<td/>').append('<input type="textbox" name=comment>').appendTo(tr);

		$('#listbody').append(tr);
	}
}
