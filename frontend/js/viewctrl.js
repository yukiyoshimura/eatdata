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
    },
    error: function(xhr, status ,err){
      console.log(xhr);
      console.log('status' + status);
      console.log('err' + err);
    }
  });
});



/*
  url = 'http://localhost/api/eatdata/hello'
  $.get(url , function(data) {
    alert("request data" + data);
  });
});
*/
