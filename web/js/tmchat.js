 var baseURL='/tmchat/'; // just for clarity
var errorImage=new Image();
errorImage.src=baseURL+"/images/error.jpg";
function switchToErrorPage()
{
var body=$('body');
body.removeClass();
body.empty();
body.append(errorImage);
}


function signOut() {
alert("YO");
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      alert("User signed out.");
    });
  }



function addTheImage() {
    var img = document.createElement('img');
    img.src = "userAvatar";
img.class="user-image";
img.height="30";
img.width="30";
    document.body.appendChild(img);

}


function login()
{
var username=$.trim($("#username").val());
var password=$.trim($("#password").val());
var valid=true;
if(username.length==0)
{
$("#usernameGroup").addClass("has-error");
valid=false;
}
else
{
$("#usernameGroup").removeClass("has-error");
}
if(password.length==0)
{
$("#passwordGroup").addClass("has-error");
valid=false;
}
else
{
$("#passwordGroup").removeClass("has-error");
}
if(!valid) return;
var tmChatService=new TMChatService();
tmChatService.authenticate(username,password,function(){
$(location).attr("href","Home.jsp");
},function(){
$("#password").val("");
$("#loginErrorSection").removeClass("hidden");
},function(){
switchToErrorPage();
});
}
function register()
{
$("#formLevelError").hide();
var name=$.trim($("#name").val());
var username=$.trim($("#username").val());
var password=$.trim($("#password").val());
var retypedPassword=$.trim($("#retypedPassword").val());
var valid=true;
if(name.length==0)
{
$("#nameGroup").addClass("has-error");
valid=false;
}
else
{
$("#nameGroup").removeClass("has-error");
}
if(username.length==0)
{
$("#usernameGroup").addClass("has-error");
valid=false;
}
else
{
$("#usernameGroup").removeClass("has-error");
}
if(password.length==0)
{
$("#passwordGroup").addClass("has-error");
valid=false;
}
else
{
$("#passwordGroup").removeClass("has-error");
}
if(retypedPassword.length==0)
{
$("#retypedPasswordGroup").addClass("has-error");
valid=false;
}
else
{
$("#retypedPasswordGroup").removeClass("has-error");
}
if(retypedPassword.length>0 && password.length>0 )
{
if(retypedPassword!=password)
{
$("#retypedPassword").val('');
$("#retypedPasswordGroup").addClass("has-error");
$("#retypedPassword").attr('placeholder','Retype password correctly');
valid=false;
}
else
{
$("#retypedPasswordGroup").removeClass("has-error");
}
}
if(!valid) return;
var tmChatService=new TMChatService();
var member=new Member();
member.name=name;
member.username=username;
member.password=password;
tmChatService.register(member,function(){
var registerBoxBody=$("#registerBoxBody");
registerBoxBody.empty();
var hyperlink=$("<a>");
hyperlink.attr("href",baseURL+"/index.jsp");
hyperlink.attr("class","btn btn-success btn-block btn-flat");
hyperlink.text("Registration done, procced to login");
registerBoxBody.append(hyperlink);
},function(exception){
$("#formLevelError").html(exception);
$("#formLevelError").show();
},function(){
switchToErrorPage();
});
}




function checkUsernameAvailability()
{
var username=$.trim($("#username").val());
var tmChatService=new TMChatService();
//var member=new Member();
tmChatService.isAvailable(username,function(responseStatus)
{
if(responseStatus==true)
{
$("#usernameGroup").removeClass("has-error");
$("#formLevelError").hide();
$("#ok").show();
$("#wrong").hide();
$("#user").hide();
$("#error").hide();
}
else
{
$("#usernameGroup").addClass("has-error");
//$("#formLevelError").show();
$("#ok").hide();
$("#wrong").show();
$("#user").hide();
}
},function()
{
switchToErrorPage();
}
);
}


function FriendList(code)
{
var user={
"Member_code":code
};

$.ajax( 
{
type : "POST",
"url": "getFriend",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
                  $("#mytbl").append('<tr id="headerRow"></tr>');
          
                  $.each(data[0], function (a, b) {
/*
$("#headerRow").append(' <td style="padding:10px;background:red;color:white;">' + "FRIENDS" + '</td>'); 
alert(b);*/

                  });
            
                  $.each(data, function (a, b) {

                $("#mytbl").append('<tr id="' + a + '"></tr>');   
                     
                  

var dataRowId = '#' + a;
                      $.each(data[a], function (c, d) {
                       //   $(dataRowId).append('<td>' + addTheImage() + '</td>');
$(dataRowId).append('<td style="padding:10px;color:white;align:center">' + d+ '</td>');
                         
                      });

   });
              },
              failure : function () {
                  alert("error...");
              }
          });

}

function getMessage(code)
{
//alert("hy");
var user={
"Member_code":code
};

$.ajax( 
{
type : "POST",
"url": "getMessage",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
  
    $.each(data, function (i,obj) {
//alert(i);
$("p"+(i+1)).append(obj.USERNAME);
$("p"+(i+10)).append(obj.MESSAGE);
//$('#drop').append('<option value='+obj.NAME+'><b>'+obj.NAME+obj.MESSAGE+'</b></option>');
});

},
failure : function () {
                  alert("error...");
              }
          });
}



function getNewConnections(code)
{
$("#profile_update").hide();
$("#addForm").hide();
//alert(code);
var user={
"Member_code":code
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
//alert("Something went wrong");
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





function addFriendRequest(fromu,tou,id)
{
//alert(tou);
//alert(fromu+","+$.trim($(tou).html()));
//alert(id);
$("#"+id).hide();
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
alert("SUCCESS");
},
failure : function () {
                  alert("error...");
              }
          });
}



function cancelRequest(id)
{
//alert(id);
$("#"+id).hide();
}









function getNotification(code)
{
//alert(code);
var user={
"Member_code":code
};

$.ajax( 
{
type : "POST",
"url": "newRequests",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
if(data.success && typeof data.succes!="function")
{
alert("Something went wrong");
}
$.each(data, function (i,obj) {
$("p"+(i+200)).append(obj.USERNAME);

});
},
failure : function () {
                  alert("error...");
              }
          });
}






function acceptFriendRequest(tou,fromu,id)
{
//alert(tou);
//alert(tou+","+$.trim($(fromu).html()));
//alert(id);
$("#"+id).hide();
var user={
"requestSenderUsername":$.trim($(fromu).html()),
"requestAccepterUsername":tou
};

$.ajax( 
{
type : "POST",
"url": "acceptFriendRequest",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
alert("You are friends now ! ");
},
failure : function () {
                  alert("error...");
              }
          });
}







function rejectRequest(tou,fromu,id)
{
//alert(id);
$("#"+id).hide();
//alert(tou+","+$.trim($(fromu).html()));
//alert(id);
$("#"+id).hide();
var user={
"fromUsername":$.trim($(fromu).html()),
"toUsername":tou
};

$.ajax( 
{
type : "POST",
"url": "rejectRequest",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
alert("You have successfully deleted this request");
},
failure : function () {
                  alert("error...");
              }
          });
}



function showDetails()
{
$("#addForm").show();
}



function profileUpdate()
{
$("#addForm").hide();
var name=$.trim($("#name").val());
var username=$.trim($("#username").val());
var oldPassword=$.trim($("#oldPassword").val());
var newPassword=$.trim($("#newPassword").val());
var user={
"username":username,
"name":name,
"password":oldPassword,
"newPassword":newPassword
};

$.ajax( 
{
type : "POST",
"url": "profileUpdater",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
alert("You have successfully updated your profile.");
},
failure : function () {
                  alert("error...");
              }
          });
}








function displayProfile()
{
$("#newConn").hide();
$("#profile_update").show();
$("#deleteAccForm").hide();
}










function FriendListInProfile(code)
{
var user={
"Member_code":code
};
$.ajax( 
{
type : "POST",
"url": "getFriend",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
                  $("#mytbl1").append('<tr id="headerRow1"></tr>');
                  $.each(data[0], function (a, b) {
                  });
            
                  $.each(data, function (a, b) {

                $("#mytbl1").append('<tr id="' + a + '"></tr>');
var dataRowId1 = '#' + a;
                      $.each(data[a], function (c, d) {
                       //   $(dataRowId1).append('<td>' + addTheImage() + '</td>');
$(dataRowId1).append('<td style="padding:10px;color:black;align:center" id="hideTd">' + d+ '</td>');
$(dataRowId1).append('<td style="padding:10px;color:black;align:center" id="hideTd1"><button type="button" class="btn btn-info" onclick="removeFriend(\'' + d + '\')"> Remove Friend</button></td>');        
                      });
   });
              },
              failure : function () {
                  alert("error...");
              }
          });

}



function closeAccTable()
{
$("#deleteAccForm").show();
}



function deleteAccount(username)
{
var password=$.trim($("#pwd1").val());
//alert(username+","+password)
var user={
"username":username,
"password":password
};

$.ajax( 
{
type : "POST",
"url": "deleteAccount",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
alert("You have successfully deleted your account.");
},
failure : function () {
                  alert("error...");
              }
          });


}



function removeFriend(tou)
{
//alert(tou);
$("#hideTd").hide();
$("#hideTd1").hide();
var user={
"toUsername":tou
};

$.ajax( 
{
type : "POST",
"url": "removeFriend",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
alert("You have successfully removed your friend.");
},
failure : function () {
                  alert("error...");
              }
          });


}



function onSignIn(googleUser) {
        // Useful data for your client-side scripts:

        var profile = googleUser.getBasicProfile();

var name=profile.getName();
var username=profile.getName();
var password=profile.getGivenName();


var user={
"username":username,
"name":name,
"password":password
};

$.ajax( 
{
type : "POST",
"url": "register",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
var registerBoxBody=$("#registerBoxBody");
registerBoxBody.empty();
var hyperlink=$("<a>");
hyperlink.attr("href","index.jsp");
hyperlink.attr("class","btn btn-success btn-block btn-flat");
hyperlink.text("Registration done, procced to login");
registerBoxBody.append(hyperlink);
alert("You have successfully created your account.");
},
failure : function () {
                  alert("error...");
              }
          });


}


function onSignIn1(googleUser) {
// Useful data for your client-side scripts:
var profile = googleUser.getBasicProfile();
var username=profile.getName();
var password=profile.getGivenName();
var user={
"username":username,
"password":password
};

$.ajax( 
{
type : "POST",
"url": "authenticate",
contentType : "application/json", 
dataType : 'json', 
data :  JSON.stringify(user),
success : function (data) {
$(location).attr("href","Home.jsp");
},
failure : function () {
                  alert("error...");
              }
          });


}

