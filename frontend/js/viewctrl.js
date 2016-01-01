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
