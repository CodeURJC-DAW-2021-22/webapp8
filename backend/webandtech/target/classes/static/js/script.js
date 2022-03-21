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
var pageByPrice = 0;
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

    

});


function viewMoreProducts(category, typeProduct, token) {
    var base = '';
    size = 8;
    sort = 'idproduct';
    console.log(category, typeProduct);
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: ('/products/getMoreProductsPage?page=' + pageProduct + '&size=' + size + '&sort=' + sort + ',desc' + '&productType=' + typeProduct + '&productcategory=' + category),
        success: function (result) {
            console.log(result);
            $.each(result.content, function (index, value) {
                console.log(value);
                base = "<div class='col-md-3 product-men'>";
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
				base = base.concat("			<a hidden>"+value.productType+"</a>");
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
   
   function viewMoreTransactions(username) {
    var base = '';
    size = 10;
    sort = 'idorder';
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: ('/users/getMoreOrders?page=' + pageProduct + '&size=' + size + '&sort=' + sort + ',desc' + '&username=' + username ),
        success: function (result) {
            console.log(result);
            $.each(result.content, function (index, value) {
                base = "<tr>";

                base = base.concat("<td>"+value.idorder+"</td>");
                base = base.concat("<td>"+value.orderdate+"</td>");
                base = base.concat("<td>"+value.price+"</td>");

                base = base.concat("<tr>")
				
                
                $("#transaction").append(base);
            });
            
            if (pageProduct + 1 <= result.totalPages) {
                pageProduct++;
            } else {
                $("#viewMore").remove();
            }
        }
    });
};

function viewMoreOrders() {
    var base = '';
    size = 10;
    sort = 'idorder';
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: ('/admin/getMoreOrders?page=' + pageProduct + '&size=' + size + '&sort=' + sort + ',desc'),
        success: function (result) {
            console.log(result);
            $.each(result.content, function (index, value) {
                base = "<tr>";

                base = base.concat("<td>"+value.idorder+"</td>");
                base = base.concat("<td>Pedido #"+value.idorder+"</td>");
                base = base.concat("<td>id"+value.idorder+"</td>");
                base = base.concat("<td>"+value.price+"€</td>");
                base = base.concat("<td>"+value.orderdate+"</td>");
                base = base.concat("<td>"+value.iduser.completname+"</td>");

                base = base.concat("<tr>");
				
                
                $("#transaction").append(base);
            });
            console.log(base)
            if (pageProduct + 1 <= result.totalPages) {
                pageProduct++;
            } else {
                $("#viewMore").remove();
            }
        }
    });
};


function viewMoreUsers() {
    var base = '';
    size = 10;
    sort = 'username';
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: ('/admin/getMoreUsers?page=' + pageProduct + '&size=' + size + '&sort=' + sort + ',asc'),
        success: function (result) {
            console.log(result);
            $.each(result.content, function (index, value) {
                    base = "            <div class='card'>";
                    base = base.concat("            <div class='card-body'>");
                    base = base.concat("                <div class='row align-items-center'>");
                    base = base.concat("                   <div class='col-xl-9 col-lg-12 col-md-12 col-sm-12 col-12'>");
                    base = base.concat("                       <div class='user-avatar float-xl-left pr-4 float-none'>");
                    base = base.concat("                           <img src='https://localhost:8443/imageprofile/"+value.username+"' alt='User Avatar' class='rounded-circle user-avatar-xl'>");
                    base = base.concat("                       </div>");
                    base = base.concat("                       <div class='pl-xl-3'>");
                    base = base.concat("                           <div class='m-b-0'>");
                    base = base.concat("                               <div class='user-avatar-name d-inline-block'>");
                    base = base.concat("                                   <h2 class='font-24 m-b-10'>"+value.completname+"</h2>");
                    base = base.concat("                               </div>");
                    base = base.concat("                           </div>");
                    base = base.concat("                           <div class='user-avatar-address'>");
                    base = base.concat("                               <p class='mb-2'><i class='fa fa-map-marker-alt mr-2  text-primary'></i>"+value.address+"");
                    base = base.concat("                               </p>");
                    base = base.concat("                           </div>");
                    base = base.concat("                       </div>");
                    base = base.concat("                   </div>");
                    base = base.concat("               </div>");
                    base = base.concat("           </div>");
                    base = base.concat("       </div>");
				
                
                $(".user-list").append(base);
            });
            console.log(base)
            if (pageProduct + 1 <= result.totalPages) {
                pageProduct++;
            } else {
                $("#viewMore").remove();
            }
        }
    });

};


function SearchStatus1(filterValue) {
    var input, filter, ul, li, a, i, txtValue;
    console.log(filterValue);
    input = filterValue;
    filter = input.toUpperCase();
    ul = document.getElementById("products");
    li = ul.getElementsByClassName('product-men');
    for (i = 0; i < li.length; i++) {
        s = li[i].getElementsByClassName("type")[0];
        txtValue = s.textContent || s.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
};
function searchBarProducts() {
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById('myInput');
    filter = input.value.toUpperCase();
    ul = document.getElementById("products");
    li = ul.getElementsByClassName('product-men');
    for (i = 0; i < li.length; i++) {
        s = li[i].getElementsByClassName("item-info-product")[0];
        a = s.getElementsByTagName("h4")[0];
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
};