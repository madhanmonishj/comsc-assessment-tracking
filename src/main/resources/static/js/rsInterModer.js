$(document).ready(function() {
    $('#moduleSetterForm').submit(function(event) {
        event.preventDefault();

        // Getting form data
        let formData = {
            feedback: $('#responseToModerator').val(),
            editsMade: $('#editsMade').is(':checked'),
            completedDate: $('#dateCompleted').val(),
            panelNotify: $('#moderationPanelNotified').is(':checked'),
            notifyDate: $('#dateNotified').val(),
            assessmentId : decodeURIComponent(new URLSearchParams(window.location.search).get("id")) || 0
        };
        // Using AJAX to send data to the server
        $.ajax({
                url: 'RSTInterModule',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(data) {
                console.log('Success:', data);
                alert("Form saved successfully");
                window.history.back();
            },
            error: function(error) {
                console.error('Error:', error);
            }
        });
    });
});
