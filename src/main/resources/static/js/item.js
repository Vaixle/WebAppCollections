let username = window.location.pathname.split("/")[1]

let url = window.location.origin

function redirect(url) {
    window.location.replace(url);
}

$("#search").on('keypress', function (e) {
    if (e.which == 13) {
        window.location.replace("/search?text=" + $("#search").val().replace("#", ""));
        e.preventDefault();
    }
});


function saveFieldValue(e) {

    e.preventDefault()

    let elem = e.target

    let actionUrl = "/api/app-collections/fields-initialize/" + elem.id + "?field"

    let type = "PUT"

    let formField;

    if (e.target.hasAttribute('text-area')) {
        formField = {
            id: elem.id,
            value: $(elem).parent().find('textarea').val()
        }
    } else if (e.target.hasAttribute('logic')) {
        formField = {
            id: elem.id,
            value: $(elem).parent().find('input:checked').val()
        }
    } else{
        formField = {
            id: elem.id,
            value: $(elem).parent().find('input').val()
        }
    }

    $.ajax({
        type: type,
        url: actionUrl,
        data: JSON.stringify(formField),
        contentType: "application/json",
        success: function (data) {
            window.location.reload();
        },
    });
}


for (let elem of $('#fields button')) {
    if ($(elem).text() != "Save" && $(elem).text() != "Сохранить") {
        continue
    }
    elem.addEventListener('click', saveFieldValue)
}

function addLike(elem) {

    let actionUrl = "/api/app-collections/items/"+ window.location.pathname.split("/").pop() +"?like";

    $.ajax({
        type: "POST",
        url: actionUrl,
        success: function (data) {
            window.location.reload();
        },
    });
}

function addLikeToComment(elem) {

    let id = $(elem).attr('id')

    let actionUrl = "/api/app-collections/comments/" + id + "?like";

    $.ajax({
        type: "POST",
        url: actionUrl,
        success: function (data) {
            window.location.reload();
        },
    });
}


function toggleDisplay(elem) {

    $(elem).parent().next().toggle("slow")
}

function connectToWS(username) {

    console.log("connecting to chat...")

    let socket = new SockJS(url + '/ws');

    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {


        stompClient.subscribe("/topic/messages/" + username, function (response) {
            let data = JSON.parse(response.body);

                render(data.message, data.fromUsername, data.createdAt, data.id, data.language)
        });
    });
}

function sendMsg(elem) {

    let text = $('#comment-text').val()

    stompClient.send("/app/comments/" + username, {}, JSON.stringify({
        message: text,
        createdAt: new Date().toLocaleString("ru-RU"),
        itemId: window.location.pathname.split("/").pop(),
        language: $(elem).attr('lang')
    }));
}

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

    $('div.comment').toggleClass('comment-dark')

    $('div.bg-light').toggleClass('bg-dark')

    $('div.bg-light').toggleClass('text-white')

}

if ($('#change-style').val() == "dark") {
    changeStyle()
}

function render(message, from, createdAt, id, language ) {

    let commentTemplate

    if(language == "ru") {
        commentTemplate =     '                <div class="comment comment rounded mb-2 pl-2 pb-2 pt-2">\n' +
            '                <div><b>От: </b>' + from + '</div>\n' +
            '                <div><b>Дата: </b>' + createdAt + '</div>\n' +
            '                <div>' + message + '</div>\n' +
            '                <div>\n' +
            '                 <p><b>Лайки: </b><span></span></p>\n' +
            '                 <button id="' +id +'" class="like btn btn-primary btn-sm" onClick="addLikeToComment(this)">\n' +
            '                 <i class="fa-solid fa-thumbs-up"></i></button>\n' +
            '                </div>\n' +
            '                </div>\n'

    } else {
        commentTemplate =     '                <div class="comment comment rounded mb-2 pl-2 pb-2 pt-2">\n' +
            '                <div><b>From: </b>' + from + '</div>\n' +
            '                <div><b>Date: </b>' + createdAt + '</div>\n' +
            '                <div>' + message + '</div>\n' +
            '                <div>\n' +
            '                 <p><b>Likes: </b><span></span></p>\n' +
            '                 <button id="' +id +'" class="like btn btn-primary btn-sm" onClick="addLikeToComment(this)">\n' +
            '                 <i class="fa-solid fa-thumbs-up"></i></button>\n' +
            '                </div>\n' +
            '                </div>\n'
    }



    $('#comments').append(commentTemplate)
}

connectToWS(username)