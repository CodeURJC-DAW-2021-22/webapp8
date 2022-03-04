let chatContainer;
let sendButtonMessage;
let messageText;
let listchat;
let stompClient;
let selectedUser;
var pageusers;
var useractual;
let token;
var products = [];



$(window).on("load", function () {
    

//----------------------------------------------------------------------------UPLOAD IMAGE----------------------------------------------------------------

$(document).on("click", "i.del", function () {
    var input = $(this).parent().children('label').children();
    var imagepreview = $(this).parent().children('div');
    input.val('');
    imagepreview.css("background-image", "url()");
});


$(function () {
    $(document).on("change", ".uploadFile", function () {
        var uploadFile = $(this);
        var files = !!this.files ? this.files : [];
        if (!files.length || !window.FileReader) return; // no file selected, or no FileReader support

        if (/^image/.test(files[0].type)) { // only image file
            var reader = new FileReader(); // instance of the FileReader
            reader.readAsDataURL(files[0]); // read the local file

            reader.onloadend = function () { // set image data as background of div
                uploadFile.closest(".imgUp").find('.imagePreview').css("background-image", "url(" + this.result + ")");
            }
        }

    });
});


$(document).ready(function () {


    var readURL = function (input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('.profile-pic').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }


    $(".file-upload").on('change', function () {
        readURL(this);
    });

    $(".upload-button").on('click', function () {
        $(".file-upload").click();
    });
});




});