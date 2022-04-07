var baseURL='/tmchat/';
function Member()
{
this.name="";
this.username="";
this.password="";
}
function TMChatService()
{
this.authenticate=function(username,password,onSuccess,onFailure,onError)
{
var user={
"username" : username,
"password": password
};
$.ajax({
"url": baseURL+"authenticate",
"type": "POST",
"dataType": "json",
"contentType": "application/json",
"data" : JSON.stringify(user),
"success": function(responseJSON){
if(responseJSON.success) onSuccess();
else onFailure();
},
"error": function(){
onError();
}
});
}; // end of authenticate
this.register=function(member,onSuccess,onFailure,onError){
$.ajax({
"url": baseURL+"register",
"type": "POST",
"dataType": "json",
"contentType": "application/json",
"data": JSON.stringify(member),
"success": function(responseJSON){
if(responseJSON.success) onSuccess();
else onFailure(responseJSON.exception);
},
"error":function(){
onError();
}
});
};

this.isAvailable=function(username,onResponse,onError)
{
$.ajax({
"url": "isUsernameAvailable",
"contentType": "application/json",
"type": "POST" ,
"dataType": "Json" ,
"data": JSON.stringify({
"username":username
}),
"success": function (responseJSON)
{
onResponse(responseJSON.available);
},
"error":function()
{
onError();
}
});
};






function isUsernameAvailable()
{
}
function acceptFriendRequest()
{
}
function removeFriend()
{
}
function sendFriendRequest()
{
}
function deleteFriendRequest()
{
}
function updateProfile()
{
}
function changePassword()
{
}
function sendMessage()
{
}
function closeAccount()
{
}
function getListOfFriends()
{
}
function getUsers()
{
}
function getNotifications()
{

}
function removeNotification()
{
}
} // end of TMChatService class