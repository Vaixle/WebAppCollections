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


if ($('#change-style').val() == "dark") {
    changeStyle()
}