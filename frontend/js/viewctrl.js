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

/*
$(function(){
  $('#myModal').on('initialize',function(event,initObj){
    $('#modalHead').text(initObj.head);
    $('#modalBody').text(initObj.body);
  });
});

*/

$(function(){
  $('#myModal').on('show',function(){
    console.log('modalshow');
    /*
    var initObj = {
      head: $('#headText').val(),
      body: $('#bodyText').val()
    };
    $('#myModal').trigger('initialize',[initObj]);
    */
  });
});


//$('#myModal').modal('show');

var array = [{"no" : 1 , "name" : "山田", "status" : "参加","comment" : "アレルギーあり"},
		{"no" : 2 , "name" : "田中", "status" : "欠席","comment" : "モチベ低下"},
		{"no" : 3 , "name" : "鈴木", "status" : "未定","comment" : "美味しいものがあるなら行きます"},
		{"no" : 4 , "name" : "川口", "status" : "参加","comment" : "ボーリング楽しみです♪"}];

//行番号取得
//var rownum;
function getrow(){
//    var rownum;
    $("tr").click( function(){
//      rownum = $("tr").index(this);
      rowinfo($("tr").index(this));
    });


}
var activeName;
var activeStatus;

function rowinfo(rownum){
//  var rows = list.rows; // 行オブジェクトの取得

  var cells = list.rows[rownum].cells;
  console.log("row=>" + rownum);

  //氏名
  activeName = $(list.rows[rownum].cells[1]).text();
  activeStatus = $(list.rows[rownum].cells[2]).text();


  console.log('指名' + $(list.rows[rownum].cells[1]).text());

  for (var j=0; j < cells.length; j++){
    console.log(cells.length); // 列数を出力
    console.log('rownum:' + ' j:' + $(list.rows[rownum].cells[j]).text());

  }

}

//
function updateStatus(){
  console.log('updateStatus');
  var rows = list.rows; // 行オブジェクトの取得
  var cells;

  console.log('activeName' + activeName);
  console.log('activeStatus' + activeStatus);

  var selectVal1 = $("#select_status").val();
  var selectVal2 = $("#comment_text").val();
  alert(selectVal1 + selectVal2);
  $('#myModal').modal('hide');
}

function addRowToBottom()
{
//	$('#listbody tr').remove();

	for(i = 0; i < array.length; i++)
	{
		var data = array[i];
		var tr = $('<tr/>');
    $('<td/>').append($('<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick="getrow()"></button>').text(data.no)).appendTo(tr);
		$('<td/>').text(data.name).appendTo(tr);
    $('<td/>').text(data.status).appendTo(tr);
    $('<td/>').text(data.comment).appendTo(tr);
//    $('<td/>').append('<input type="radio" name="attend1" value="1" checked>参加する<input type="radio" name="attend1" value="2" >欠席する').appendTo(tr);
//    $('<td/>').append('<input type="textbox" name=comment>').appendTo(tr);

		$('#listbody').append(tr);
	}
}
