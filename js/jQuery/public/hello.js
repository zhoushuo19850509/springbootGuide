$(document).ready(function() {
    $.ajax({
        url: "http://192.168.0.28:8080/greetingcors"
    }).then(function(data) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
    });
});
