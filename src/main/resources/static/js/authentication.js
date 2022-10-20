$("form").submit(function (e) {

    e.preventDefault(); // avoid to execute the actual submit of the form.

    var form = $(this);
    var actionUrl = form.attr('action');

    $.ajax({
        type: "POST",
        url: actionUrl,
        data: form.serialize(), // serializes the form's elements.
        success: function (data) {
            window.location.replace("/" + $("#username").val());
        },
        error: function (data) {
            checkValidation(data.responseJSON.message)
        },
    });

});

function checkValidation(message) {

    let $inputButtonUsername = $("#username")

    let $inputButtonPassword = $("#password")

    let $usernameFeedBack = $inputButtonUsername.parent().children().last()

    let $passwordFeedBack = $inputButtonPassword.parent().children().last()

    $inputButtonUsername.addClass("is-invalid")

    $inputButtonPassword.addClass("is-invalid")

    $usernameFeedBack.text(message)

    $passwordFeedBack.text(message)

}

$('#lang').on('change', function (e) {

    let valueSelected = this.value;

    let actionUrl = window.document.location.origin + window.document.location.pathname

    window.location.replace(actionUrl + "?lang=" + valueSelected)
});