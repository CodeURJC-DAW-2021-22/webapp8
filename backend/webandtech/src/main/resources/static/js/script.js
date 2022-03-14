let chatContainer;
let sendButtonMessage;
let messageText;
let listchat;
let stompClient;
let selectedUser;
var pageusers;
var useractual;
let token;
var pageProduct = 1;
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

// function viewMoreProducts(category, typeProduct) {
//     $.ajax({
//         type: "GET",
//         contentType: "application/json",
//         url: ('/products/moreProducts?page=0&size=10&sort=idproduct&direction=asc'),
//         success: function (result) {
//             console.log(result);
//         }
//     });
// }
function viewMoreProducts(category, typeProduct, token) {
    var base = '';
    size = 10;
    sort = 'idproduct';
    console.log(category, typeProduct);
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: ('/products/getMoreProductsPage?page=' + pageProduct + '&size=' + size + '&sort=' + sort + '&productType=' + typeProduct + '&productcategory=' + category),
        success: function (result) {
            console.log(result);
            $.each(result.content, function (index, value) {
                base = base.concat("<div class='col-md-3 product-men'>");
				base = base.concat("<div class='men-pro-item simpleCart_shelfItem'>");
				base = base.concat("	<div class='men-thumb-item'>");
                base = base.concat("			<img src='https://localhost:8443/productImg1/"+value.idproduct+"' alt='' class='pro-image-front'>");
                base = base.concat("			<img src='https://localhost:8443/productImg1/"+value.idproduct+"' alt='' class='pro-image-back'>");
				base = base.concat("			<div class='men-cart-pro'>");
				base = base.concat("				<div class='inner-men-cart-pro'>");
				base = base.concat("					<a href='/products/"+value.idproduct+"' class='link-product-add-cart'>Quick View</a>");
				base = base.concat("				</div>");
				base = base.concat("			</div>");
				base = base.concat("		</div>");
				base = base.concat("		<div class='item-info-product '>");
				base = base.concat("			<h4><a href='/products/"+value.idproduct+"'>"+value.nameproduct+"</a></h4>");
				base = base.concat("			<div class='info-product-price'>");
				base = base.concat("				<span class='item_price'>"+value.price+"€</span>");
				base = base.concat("			</div>");
				base = base.concat("			<form action='/carShop' method='post'>");
				base = base.concat("				<input type='hidden' name='_csrf' value='"+token+"'/>");
				base = base.concat("				<input type='hidden' name='idproduct' value='"+value.idproduct+"'/>");
				base = base.concat("				<input type='submit' class='item_add single-item hvr-outline-out button2' value='Añadir al carro'>");
				base = base.concat("			</form>");
				base = base.concat("		</div>");
				base = base.concat("	</div>");
				base = base.concat("</div>");
                
                $(".single-pro").append(base);
            });
            
            if (pageProduct + 1 <= result.totalPages) {
                pageProduct++;
            } else {
                $("#viewMore").remove();
            }
        }
    });
};

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
   