
    $("form").submit(function (e) {

    e.preventDefault();

    var form = $(this);
    var actionUrl = form.attr('action');

    $.ajax({
    type: "POST",
    url: actionUrl,
    data: form.serialize(),
    success: function (data) {
    window.location.replace("/login");
},
    error: function (data) {
    let response = data.responseJSON;

    if(response.message) {
    return checkUsernameEmailExist(response.message)
}

    runValidate(response)
},
});

});

    function checkValidation(error, status, elem) {

    let $inputButton = status ? $("#" + error.field) : elem;

    let $divFeedBack = $inputButton.parent().children().last()

    let classInputValidDelete = status ? "is-valid" : "is-invalid"

    let classInputValidAdd = status ? "is-invalid" : "is-valid"

    let classFeedBackDelete = status ? "valid-feedback" : "invalid-feedback"

    let classFeedBackAdd = status ? "invalid-feedback" : "valid-feedback"

    let message = status ? error.defaultMessage : "Looks good!"

    if ($inputButton.hasClass(classInputValidDelete)) {
    $inputButton.removeClass(classInputValidDelete)
}

    $inputButton.addClass(classInputValidAdd)

    if ($divFeedBack.hasClass(classFeedBackDelete)) {
    $divFeedBack.removeClass(classFeedBackDelete)
}
    $divFeedBack.addClass(classFeedBackAdd)
    $divFeedBack.text(message)
}

    function runValidate(response) {

    $(".form-outline").each(function(i) {

        let $inputButtonId = $(this).find("input")
        let errorStatus = false

        for (let j = 0; j < response.length; j++) {

            let error = response[j]

            if($inputButtonId.attr("id") === error.field) {
                checkValidation(error, true)
                errorStatus = true
                break
            }
        }
        if(!errorStatus) {
            checkValidation(null, false, $inputButtonId)
        }
    })
}

    function checkUsernameEmailExist(message) {
    if(message.includes("Email")) {
    checkValidation({field: "email", defaultMessage: message}, true)
}

    if(message.includes("Username")) {
    checkValidation({field: "username", defaultMessage: message}, true)
}
}
