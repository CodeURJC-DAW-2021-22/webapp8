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

$('#multi').mdbRange({
    width: '100%',
    direction: 'vertical',
    value: {
      min: 0,
      max: 100,
    },
    single: {
      active: true,
      value: {
        step: 1,
        symbol: ''
      },
      counting: false,
      countingTarget: null,
      bgThumbColor: '#4285F4',
      textThumbColor: '#fff',
      multi: {
        active: true,
        value: {
          step: 1,
          symbol: ''
        },
        counting: false,
        rangeLength: 2,
        countingTarget: null,
        bgThumbColor: '#4285F4',
        textThumbColor: '#fff'
      },
    }
});

    $('.flexslider').flexslider({
        animation: "slide",
        controlNav: "thumbnails"
    });

});
function viewMore(category, typeProduct) {
    size = 10;
    sort = 'idpost';
    console.log(category, typeProduct);
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: ('/getMorePostsUser?page=' + pagepostuser + '&size=' + size + '&sort=' + sort + ',desc' + '&username=' + user),
        success: function (result) {
            
            $.each(result.content, function (index, value) {
                var icon = "la la-heart-o";
                var type = "company";
                if (value.iduser.userprofile) {
                    type = "user";
                }
                if (likes.includes(value.idpost)) {
                    icon = "la la-heart";
                }
            //     $(".single-pro").append("<div class='col-md-3 product-men'><div class='men-pro-item simpleCart_shelfItem'><div class='men-thumb-item'><img src="https://localhost:8443/productImg1/{{idproduct}}" alt="" class="pro-image-front">
            //             <img src="https://localhost:8443/productImg1/{{idproduct}}" alt="" class="pro-image-back">
            //             {{/img1}}
            //             <div class="men-cart-pro">
            //                 <div class="inner-men-cart-pro">
            //                     <a href="/products/{{idproduct}}" class="link-product-add-cart">Quick View</a>
            //                 </div>
            //             </div>

            //         </div>
            //         <div class="item-info-product ">
            //             <h4><a href="/products/{{idproduct}}">{{nameproduct}}</a></h4>
            //             <div class="info-product-price">
            //                 <span class="item_price">{{price}}€</span>
            //             </div>
            //             <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
            //         </div>
            //     </div>
            // </div>");
                $(".single-pro").append("<div class='post-bar'><div class='post_topbar'><div class='row usy-dt'><div class='user-post-icon'><img src='https://localhost:8443/imageprofile/" + value.iduser.username + "' alt=''></div><div class='usy-name'><h3>" + value.iduser.username + "</h3></div></div></div><div class='epi-sec'><ul class='descp'><li><img src='images/icon8.png' alt=''><span>" + type + "</span></li><li><img src='images/icon9.png' alt=''><span>" + value.iduser.city + "</span></li></ul><ul class='bk-links'><li><a id='" + value.idpost + "' title=''><i onclick='like(" + value.idpost + ")' class='" + icon + "'></i></a></li><li><a href='./messages?to=" + value.iduser.username + "' title=''><i class='la la-envelope'></i></a></li></ul></div><div class='job_descp'><h3>" + value.title + "</h3><div class='row'><ul class='image-store'><li><img src='https://localhost:8443/imagepost/" + value.idpost + "' alt=''></li></ul></div><div class='row'><ul class='description-store'><li><p>" + value.description + "</p></li></ul></div><br><a id='readmore" + value.idpost + "' class='btn btn-primary stretched-link' onclick='readmore(" + value.idpost + ")' title=''>view more</a></div></div>");
            });
            if (pagepostuser + 1 <= result.totalPages) {
                pagepostuser++;
            } else {
                $("#viewMore").remove();
            }
        }
    });
};