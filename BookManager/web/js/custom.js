$(document).ready(function(){
   ajax();
   askBeforeDelete(); 
   timeOutAlert();
   search();
   sort();
   filter();
   cart();
});

// general
function askBeforeDelete(){
    $('#list-table .delete-action').click(function(e){
        let r   = confirm("Do you want to delete this item?");
        if (r == true){
            let link    = $(this).attr("href");
            window.location.replace(link);
        }
       e.preventDefault();
    });
}

function timeOutAlert(){
    try{
        setTimeout(function(){
            $('#alert').fadeOut();
        }, 1500);
    }catch (e){
        
    }
    
}

function setSearchDropdown(){
    $('#search-dropdown .dropdown-menu a').click(function(e){
       let text     = $(this).text();
       let name     = $(this).attr("name");
       let button   = $("#search-by-btn");
       button.text(text);
       button.attr("name", name);
       e.preventDefault(); 
    });
}












// CART =========================
var cartSelector            = $('#cart-table');
var cartForm                = $('#cart-form');
function cart(){
    clickAddBook();
    clickChangeQty();
    clickDeleteBook();
}

    // click add book
    function clickAddBook(){
        $('#list-table').on("click", "a.add-action", function(e){ 
            let id              = $(this).attr('item-id');
            let qty             = getItemQuantity($(this));
            let bookInCart      = $('#cart-table tr[book_id="'+id+'"]');
            if (bookInCart.length > 0) increaseQtyBookInCart(qty, bookInCart)
            else addNewBookInCart(id, qty);
            e.preventDefault();
        });
    }

        function increaseQtyBookInCart(addQty, book){
            let oldQty      = getQtyBookInCart(book);
            let newQty      = parseInt(addQty) + oldQty;
            let bookPrice   = getPriceBookInCart(book);

            // set qty
            setQtyBookInCart(book, newQty);
            updateTotalPrice(bookPrice, newQty, oldQty);   
            changeDataToCartHiddenInput(book);            
        }

        function addNewBookInCart(id, qty){
                let action          = "getBookRowInCartHtml";
                let lastBookInCart  = getLastBookInCart();
                let lastOrder       = getLastOrderOfBookInCart(lastBookInCart); 

                let url     = getPureUrl().replace('form', 'ajaxsave');
                let data    = {action: action, type: "", id:id, qty:qty, lastOrder:lastOrder};
                $.ajax({
                    url: url,
                    data: data,
                    type: "post",
                    success: function(data, status){
                        if (data=="null") data = "error server";
                        let obj     = $.parseJSON(data);
                        if (obj.message == "success"){
                            appendBookToCartXhtml(lastBookInCart, obj.xhtml);
                        }else{
                            alert(failMessage);
                        }
                    }
                });
        }

    // click change qty
    function clickChangeQty(){
        let selector    = 'input.cart-qty-input';
        // old val      
        cartSelector.on('focus', selector, function(){
            $(this).attr('old_value', $(this).val());
        });

        // new val
        cartSelector.on('blur', selector, function(){
            let oldQty  = $(this).attr('old_value');
            let newQty  = $(this).val();
            let book    = getBookIncartById($(this).parents('tr').attr('book_id'));
            updateTotalPrice(getPriceBookInCart(book), newQty, oldQty);
            changeDataToCartHiddenInput(book);
        });
    }

    // click delete book
    function clickDeleteBook(){
        let selector    = 'a.delete-action';
        cartSelector.on('click', selector, function(e){
            let book    = getBookIncartById($(this).parents('tr').attr('book_id'));
            updateTotalPrice(getPriceBookInCart(book), 0, getQtyBookInCart(book));
            book.detach();
            if (isCartEmpty()) $('#total-price-row').detach();
            deleteDataToCartHiddenInput(book);
            e.preventDefault();
        });
    }




// SUPPORT CART FUNCTIONS
function appendBookToCartXhtml(lastBook, newBook){
    if (lastBook.length > 0) lastBook.after(newBook);        
    else $('#cart-table > tbody').append(newBook);
    
    let latestBook    = getLastBookInCart();
    if (lastBook.length > 0) updateTotalPrice(getPriceBookInCart(latestBook), getQtyBookInCart(latestBook), 0);
    addDataToCartHiddenInput(latestBook);
}

function getLastBookInCart(){
    return $('#cart-table > tbody > tr:nth-last-child(2)');
}

function getLastOrderOfBookInCart(lastBook){
    let lastOrder   = lastBook.children("th").text().trim();
    lastOrder       = (lastOrder == "") ? 0 : lastOrder;
    return lastOrder;
}

function getItemQuantity(object){
    let qty     = object.parents("tr").children('td[field="quantity"]').children(".qty-input").val();
    return qty;
} // (in book list table)

function updateTotalPrice(bookPrice, newQty, oldQty){
    let totalPrice  = getTotalPrice();
    totalPrice      = totalPrice - (bookPrice * oldQty) + (bookPrice * newQty);
    setTotalPrice(totalPrice);
}

function getTotalPrice(){
    let totalPrice  = $('#total-price').attr('value');
    return parseInt(totalPrice);
}

function setTotalPrice(totalPrice){
    try{
        let selector    = $('#total-price');
        selector.attr('value', totalPrice);
        selector.text(number_format(totalPrice));
    }catch (e){    
    }

}

function getPriceBookInCart(book){
    return parseInt(book.children('td[field="price"]').attr("value"));
}

function getQtyBookInCart(book){
    return parseInt(book.children('td[field="quantity"]').children('input').val());
}

function setQtyBookInCart(book, qty){
    return parseInt(book.children('td[field="quantity"]').children('input').val(qty));
}

function getBookIncartById(id){
    let book    = $('#cart-table > tbody').children('tr[book_id="'+id+'"]');
    if (book.length > 0) return book;
    else return null;
}

function isCartEmpty(){
    if (cartSelector.children('tbody').children().length == 1) return true;
    return false;
}

// cart hidden input
    function addDataToCartHiddenInput(book){
        let bookIdArr   = getBookIdArr();
        let qtyArr      = getQtyArr();
        bookIdArr.push(book.attr('book_id'));        
        qtyArr.push(getQtyBookInCart(book));
        setBookIdArr(bookIdArr);
        setQtyArr(qtyArr);
    }
    
    function changeDataToCartHiddenInput(book){
        let bookIdArr   = getBookIdArr();
        let qtyArr      = getQtyArr();
        for (let i=0; i<bookIdArr.length; i++){
            if (parseInt(bookIdArr[i]) == book.attr('book_id')){
                qtyArr[i]   = getQtyBookInCart(book);
                break;
            }
        }
        setBookIdArr(bookIdArr);
        setQtyArr(qtyArr);
    }
    
    function deleteDataToCartHiddenInput(book){
        let bookIdArr   = getBookIdArr();
        let qtyArr      = getQtyArr();
        for (let i=0; i<bookIdArr.length; i++){
            if (parseInt(bookIdArr[i]) == book.attr('book_id')){
                bookIdArr.splice(i, 1);
                qtyArr.splice(i, 1);
                break;
            }
        }
        setBookIdArr(bookIdArr);
        setQtyArr(qtyArr);
    }

    function getBookIdArr(){
        let array   = cartForm.children('input[name="booksString"]').val().split('-');
        if (array[0].trim() == "") array.shift();
        return array;
    }

    function setBookIdArr(array){
        cartForm.children('input[name="booksString"]').val(array.join('-'));
    }

    function getQtyArr(){
        array   = cartForm.children('input[name="qtysString"]').val().split('-');
        if (array[0].trim() == "") array.shift();
        return array;
    }

    function setQtyArr(array){
        cartForm.children('input[name="qtysString"]').val(array.join('-'));
    }









// GENERAL SUPPORTED FUNCTION
function removeLetterFromString(string, letter){
    let pattern = new RegExp(letter, 'g');
    return string.replace(pattern, '');
}

function number_format(num){      
    num  = strrev(num.toString());
    let numArr  = num.match(/.{1,3}/g);
    let result  = numArr.join(",");
    return strrev(result);
}

function strrev(string){
    return string.split("").reverse().join("");
}



// ajax
function ajax(){
    changeStatus();
    changeLevel();
}
var failMessage         = "Something went wrong";
var successMessage     = "Change item successfully";
function changeStatus(){
    $('#list-table').on("click", 'a.status-btn', function(e){
        let htmlObj = $(this);
        let action  = "changeStatus";
        let id      = $(this).attr("id");
        let value   = $(this).attr("value");
        let url     = getPureUrl().replace('index', 'ajaxsave')+"?action="+action+"&type="+value+"&id="+id;        
        $.ajax({
            url: url,
            success: function(data, status){
                let obj     = $.parseJSON(data);
                if (obj.message == "success"){
                    htmlObj.parent().html(obj.xhtml);
                    alert(successMessage);
                }else{
                    alert(failMessage);
                }
            }
        });
        e.preventDefault();
    });
}

function changeLevel(){
    $('#list-table').on("change", 'select.level-select', function(e){
        let htmlObj = $(this);
        let action  = "changeLevel";
        let id      = $(this).attr("id");
        let value   = $(this).val();
        let url     = getPureUrl().replace('index', 'ajaxsave')+"?action="+action+"&type="+value+"&id="+id;  
        $.ajax({
            url: url,
            success: function(data, status){
                let obj     = $.parseJSON(data);
                if (obj.message == "success"){
                    htmlObj.parent().html(obj.xhtml);
                    alert(successMessage);
                }else{
                    alert(failMessage);
                }
            }
        });
        e.preventDefault();
    });
}

// sort
function sort(){
    sendSort();
    formatSort();
}

function sendSort(){
    $('#list-table thead tr th.sort > a').click(function(e){
        let field               = $(this).attr("field");
        let currentOrder        = ($(this).attr("order") != undefined)? $(this).attr("order") : "desc";
        let newOrder            = (currentOrder == "asc") ? "desc" : "asc";
        let url                 = createUrlWithParams([
            {key: "sort_field", value: field},
            {key: "sort_order", value: newOrder}
        ]);
        window.location.replace(url);
        e.preventDefault();
    });
}

function formatSort(){
    let field   = getParam("sort_field");
    let order   = getParam("sort_order");
    if (field != null && order != null){
        let item        = $('#list-table thead tr th.sort > a[field="'+field+'"]');
        let oriented    = (order == "asc") ? "up" : "down";
        item.append('&nbsp;&nbsp;<i class=\"fas fa-sort-'+oriented+'\"></i>');
        item.attr("order", order);
    }
        
}

// filter
function filter(){
    sendFilter();
}

function sendFilter(){
    $('#cat-filter').change(function(){
        let value   = $(this).val();
        let url     = createUrlWithParams([
            {key: "cat_filter", value: value}, 
        ]);
       window.location.replace(url);
    });
}

// search
function search(){
    setSearchDropdown();
    pressSearchbtn();
    pressClearbtn();
    checkSearchEmpty();
}

function pressSearchbtn(){
    $('#search-btn').click(function(){
        if (checkSearchEmpty()){
            alert("Please type something to search");
        }else{
            let searchField     = $("#search-by-btn").attr("name").trim();
            let searchValue     = $("#search-input").val().trim();
            let url     = createUrlWithParams([
                {key: "search_field", value: searchField},
                {key: "search_value", value: searchValue}           
            ]);
            window.location.replace(url);
        }

    });
}

function pressClearbtn(){
    $('#clear-btn').click(function(){
        let url     = createUrlWithDeleteParams([
            {key: "search_field"},
            {key: "search_value"}           
        ]);
        window.location.replace(url);
    });
}

function checkSearchEmpty(){
    if ($('#search-input').val().trim() == "") return true;
    return false;
}

function createUrlWithParams(data){
    let params  = new URLSearchParams(location.search);
    for (var x of data){
        params.set(x.key, x.value);
    }
    params.delete("message");
    if (params.toString().trim()!="") return getPureUrl()+"?"+params.toString();
    else return getPureUrl();  
}

function createUrlWithDeleteParams(data){
    let params  = new URLSearchParams(location.search);
    for (var x of data){
        params.delete(x.key);
    }
    params.delete("message");
    if (params.toString().trim()!="") return getPureUrl()+"?"+params.toString();
    else return getPureUrl();  
}

function getParam(name){
    let params  = new URLSearchParams(location.search);
    return params.get(name);
}

function getPureUrl(){
    return window.location.protocol+"//"+window.location.hostname+":"+window.location.port+window.location.pathname;
}
