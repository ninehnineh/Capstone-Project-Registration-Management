function getName(id)
{
alert("hy");

var user={
"Member_code":id
};

$.ajax( 
{
type : "POST",
"url": "newConnections",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
if(data.success && typeof data.succes!="function")
{
alert("Something went wrong");
}
$.each(data, function (i,obj) {
$("p"+(i+100)).append(obj.USERNAME);

});
},
failure : function () {
                  alert("error...");
              }
          });
}





function addFriendRequest(fromu,tou)
{
alert(tou);
alert(fromu+$.trim($(tou).html()));

var user={
"fromUsername":fromu,
"toUsername":$.trim($(tou).html())
};

$.ajax( 
{
type : "POST",
"url": "addFriendRequest",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
alert(data);
},
failure : function () {
                  alert("error...");
              }
          });
}