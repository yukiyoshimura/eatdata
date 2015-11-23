$(function(){
    $("#response").html("Response Values");

    $("#postButton").click( function(){
            var registData = {
                no: $("#value1").val(),
                name: $("#value2").val()
            };

        var url = 'http://localhost/api/eatdata/regist';
        $.ajax({
            type:'post',
            url:url,
            data:JSON.stringify(registData),
            contentType:'application/JSON',
            dataType:'JSON',
            scriptCharset:'utf-8',
            success:function(data) {

                // Success
                console.log("success");
                console.log(JSON.stringify(data));
                $("#response").html(JSON.stringify(data));
            },
            error: function(data) {

                // Error
                console.log("error");
                console.log(JSON.stringify(data));
                console.log("request" + JSON.stringify(registData));
                $("#response").html(JSON.stringify(data));
            }
        });
    })
})
