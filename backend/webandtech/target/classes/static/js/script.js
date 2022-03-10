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

$('#visual').pignoseLayerSlider({
    play: '.btn-play',
    pause: '.btn-pause',
    next: '.btn-next',
    prev: '.btn-prev'
});

$('#horizontalTab').easyResponsiveTabs({
    type: 'default', //Types: default, vertical, accordion           
    width: 'auto', //auto or any width like 600px
    fit: true   // 100% fit in a container
});

$( "#slider-range" ).slider({
    range: true,
    min: 0,
    max: 5000,
    values: [ 0, 2000 ],
    slide: function( event, ui ) {  $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
    }
});
$( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) + " - $" + $( "#slider-range" ).slider( "values", 1 ) );

$(function () {
    // Slideshow 4
   $("#slider3").responsiveSlides({
       auto: true,
       pager: true,
       nav: false,
       speed: 500,
       namespace: "callbacks",
       before: function () {
   $('.events').append("<li>before event fired.</li>");
   },
   after: function () {
       $('.events').append("<li>after event fired.</li>");
       }
       });
   });

});