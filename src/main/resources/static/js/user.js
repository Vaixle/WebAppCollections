function getUserCollections () {

    let form = $(this);
    let actionUrl = "api/app-collections/collections?username=" + window.location.pathname.replace("/", "");

    $.ajax({
        type: "GET",
        url: actionUrl, // serializes the form's elements.
        success: function (data) {
            $('#collections').append(data)

        },
    });

};

$("form").submit(function (e) {

    e.preventDefault();

    let form = $(this);
    let actionUrl = form.attr('action') +"?username="+ window.location.pathname.replace("/", "");

    $.ajax({
        type: "POST",
        url: actionUrl,
        data: new FormData(this),
        contentType: false,
        processData: false,
        success: function () {
            window.location.replace(window.location.origin + window.location.pathname + "/collections/" + $("#name").val());
        }
    });

});

function deleteCollection(e) {


    let $elemLi = $(e).parent()

    let actionUrl = "/api/app-collections/collections/" + $elemLi.attr("id");

    $.ajax({
        type: "DELETE",
        url: actionUrl, // serializes the form's elements.
        success: function (data) {
            $elemLi.remove();
        },
    });

};

function redirect(url) {
    window.location.replace(url);
}

$("#search").on('keypress',function(e) {
    if(e.which == 13) {
        window.location.replace("/search?text=" + $("#search").val().replace("#", ""));
        e.preventDefault();
    }
});

$('#lang').on('change', function (e) {

    let valueSelected = this.value;

    let actionUrl = window.document.location.origin + window.document.location.pathname

    window.location.replace(actionUrl +"?lang="+valueSelected)
});

$('#change-style').on('change', changeStyle);

function changeStyle(e) {

    let valueSelected = this.value;

    let actionUrl = window.document.location.origin + window.document.location.pathname

    $.ajax({
        type: "GET",
        data:{"style" : valueSelected},
        url: actionUrl,
    });

    $('body').toggleClass('gradient-dark')

    $('button').toggleClass('btn-info')

    $('div.bg-light').toggleClass('bg-dark')

    $('div.bg-light').toggleClass('text-white')

}
getUserCollections ()

if ($('#change-style').val() == "dark") {
    changeStyle()
}

