
var respondentList;

var activeNo;
var activeName;
var activeStatus;
var activeComment;


$(function() {
   $( "#progressbar" ).progressbar({
      value: false
   });
});

// init処理
// 一覧取得
$(function init() {
  $.ajax({
      url: 'http://eatdata.azurewebsites.net/eatdata/api/respondent',
// url: 'http://localhost:8080/eatdata/api/respondent',
    dataType: 'jsonp', // 追加
    type: "GET",
    success: function(res) {
      respondentList = res;
      console.log('init success');
      console.log(res);
      // progress bar hide
      $( "#progressbar" ).hide();
      getSumValue();
      // 取得したレコードをテーブルで表示する
      addRowToBottom();
    },
    error: function(xhr, status ,err){
      console.log(xhr);
      console.log('status' + status);
      console.log('err' + err);
    }
  });
});

// ステータス毎の合計値をフォームにセット
function getSumValue(){
  sumarry_table.rows[1].cells[0].innerText = respondentList[0].sumAttend;
  sumarry_table.rows[1].cells[1].innerText = respondentList[0].sumAbsent;
  sumarry_table.rows[1].cells[2].innerText = respondentList[0].sumUndecided;
}


// サーバから取得したレコードをテーブルで表示する
function addRowToBottom(){
  console.log('addRowToBottom');


	for(i = 0; i < respondentList.length; i++)
	{
    var data = respondentList[i];
    var tr = $('<tr/>');
    console.log(respondentList[i]);

        $('<td/>').append($('<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#editModal" onclick="getrow()"></button>').text(data.employeeId)).appendTo(tr);
    		$('<td/>').text(data.employeeNm).appendTo(tr);
        $('<td/>').append($('<select id="initstatus" disabled />').append($('<option>').val(data.respondentStatus.code).text(data.respondentStatus.text))).appendTo(tr);
        $('<td/>').text(data.comment).appendTo(tr);
    		$('#listbody').append(tr);
  }
}

//行番号取得
function getrow(){
  console.log('getrow');
    $("tr").click( function(){
      rowinfo($("tr").index(this));
    });
}

// 選択した行の各値を取得する
function rowinfo(rownum){
  var cells = list.rows[rownum].cells;
  console.log("activeRow=>" + rownum);
  //社員番号
  activeNo   = $(list.rows[rownum].cells[0]).text();
  //氏名
  activeName = $(list.rows[rownum].cells[1]).text();
  //参加状況
  activeStatus  = $(list.rows[rownum].cells[2]).find("select").val();
  //その他
  activeComment = $(list.rows[rownum].cells[3]).text();

  console.log('氏名' + $(list.rows[rownum].cells[1]).text());
  console.log('rowcnt==>' + cells.length); // 列数を出力
}

// 押下した行の編集用モーダルを表示
$(function() {
  $('#editModal').on('show.bs.modal', function (event) {
    console.log('===modal open====');
    console.log('activeNo:' + activeNo);
    console.log('activeStatus:' + activeStatus);
    console.log('activeComment:' + activeComment);

    var modal = $(this);
    modal.find('.modal-body #current-userno').val(activeNo);
    modal.find('.modal-body #current-username').val(activeName);
    modal.find('.modal-body #current-status').val(activeStatus);
    modal.find('.modal-body #current-comment').val(activeComment);

  });
});

//　更新ボタン押下時の処理
//  モーダルに入力した値で更新を行う
function updateStatus(){
  console.log('updateStatus');
  var applyNo = $("#current-userno").val();
　var applyName = $("#current-username").val();
  var applyStatus = $("#current-status").val();
  var applyComment = $("#current-comment").val();

  console.log('employeeId:' + applyNo);
  console.log('employeeNm:' + applyName);
  console.log('respondentStatus:' + applyStatus);
  console.log('comment:' + applyComment);

    // 各フィールドから値を取得してJSONデータを作成
  var data = {
      employeeId:      applyNo,
      employeeNm:      applyName,
      respondentStatus:applyStatus,
      comment:         applyComment
  };

  $.ajax({
    url: 'http://eatdata.azurewebsites.net/eatdata/api/regist',
//    url: 'http://localhost:8080/eatdata/api/regist',
    dataType: 'json', // 追加
    type: 'POST',
    contentType: 'application/json',
    data:JSON.stringify(data),
    success: function(res) {
      $('#editModal').modal('hide');
      console.log(res);
      console.log('post success');
      location.reload();
    },
    error: function(xhr, status ,err){
      console.log(xhr);
      console.log('status' + status);
      console.log('err' + err);
    }
  });
}
