function admin(oInput) {
    let aInputs = document.getElementsByTagName("input");
        for (let i=0;i<aInputs.length;i++) {
            if (aInputs[i] != oInput) {
                aInputs[i].checked = oInput.checked;
            }
        }


}

 function manageUsers(e, actionUrl, type) {

    e.preventDefault();

    let form = $('#users-form');
    form = form.serializeArray().map(u => {return {id: u.value}});


    $.ajax({
        type: type,
        url: actionUrl,
        data: JSON.stringify(form),
        contentType: "application/json",
        success: function (data) {
            window.location.replace("/admin");
        },
    })

}



$('#block').click(e => manageUsers(e, '/admin/block', "PUT"));
$('#unblock').click(e => manageUsers(e, '/admin/unblock', "PUT"));
$('#delete').click(e => manageUsers(e, '/admin/delete', "DELETE"));
$('#user').click(e => manageUsers(e, '/admin/remove-admin-role', "PUT"));
$('#admin').click(e => manageUsers(e, '/admin/add-admin-role', "PUT"));