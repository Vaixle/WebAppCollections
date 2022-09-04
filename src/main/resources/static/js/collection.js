
    var simplemde = new SimpleMDE({element: document.getElementById("content")});

    $("#description").submit(function (e) {

    e.preventDefault(); // avoid to execute the actual submit of the form.

    let form = $(this);
    let actionUrl = form.attr('action');

    $.ajax({
    type: "POST",
    url: actionUrl,
    data: form.serialize(), // serializes the form's elements.
    success: function (data) {
    $(".description-value").html(data.description);
},
});

});


    function toggleDisplay(id) {

        $(id).toggle("slow")
    }

    function deleteItem(e) {


        let $elemLi = $(e).parent()

        let actionUrl = "/api/app-collections/items/" + $elemLi.attr("id");

        $.ajax({
            type: "DELETE",
            url: actionUrl, // serializes the form's elements.
            success: function (data) {
                $elemLi.remove();
            },
        });

    };

    function editDescription() {

        $('#edit-description').toggle("slow")
    }

    $("#fields").submit(function (e) {

    e.preventDefault();

    let form = $(this);
    let actionUrl = form.attr('action');

    $.ajax({
    type: "POST",
    url: actionUrl,
    data: form.serialize(),
    success: function (data) {
        window.location.reload();
    // $(".description-value").html(data.description);
},
});

});

    $("#create-item").submit(function (e) {

    e.preventDefault(); // avoid to execute the actual submit of the form.

    let form = $(this).serializeArray();

    form[1].value = form[1].value.split(' ');
    form[1].value = form[1].value.map(v => {return {name: v}});

    form = objectifyForm(form)

    let actionUrl = $(this).attr('action');

    $.ajax({
    type: "POST",
    url: actionUrl,

        data: JSON.stringify(form),
        contentType: "application/json",
        success: function (data) {
    window.location.replace(window.location.href +/items/ + data)
},
});

});


    function objectifyForm(formArray) {
        //serialize data function
        var returnArray = {};
        for (var i = 0; i < formArray.length; i++){
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    }

    function redirect(url) {
        window.location.replace(url);
    }

    $('#lang').on('change', function (e) {

        let valueSelected = this.value;

        let actionUrl = window.document.location.origin + window.document.location.pathname

        window.location.replace(actionUrl +"?lang="+valueSelected)
    });

    $("#search").on('keypress',function(e) {
        if(e.which == 13) {
            window.location.replace("/search?text=" + $("#search").val().replace("#", ""));
            e.preventDefault();
        }
    });

    $(function () {
        // Basic
        $('.basicAutoComplete').autoComplete({
            resolverSettings: {
                url: '/api/app-collections/tags?names'
            },
            minLength: 1
        });


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