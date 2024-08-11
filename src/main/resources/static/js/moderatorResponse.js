
$(document).ready(function() {
 $("#saveFeedback").click(function (event) {
        event.preventDefault();

        // Collect form data
        var feedback = $('#feedback').val().trim();
        var editMade = $('#editMade').is(':checked');
        var assessmentSent = $('#assessmentSent').is(':checked');
        var dateCompleted = $('#dateCompleted').val();
        var dateSent = $('#dateSent').val();

        // Form validation
        if (!feedback) {
            alert('Please provide a response to the assessment panel feedback and actions.');
            return false;
        }

        if (!editMade && !assessmentSent) {
            alert('Please check at least one of the checkboxes.');
            return false;
        }

        // Prepare data for AJAX submission
        var data = {
            feedback: feedback,
            editMade: editMade,
            assessmentSent: assessmentSent,
            dateCompleted: dateCompleted,
            dateSent: dateSent,
            assessmentId: decodeURIComponent(new URLSearchParams(window.location.search).get("id")) || 0
        };

        // AJAX call to submit the form data
        $.ajax({
            type: 'POST',
            url: '/public/savingModerationForm',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                alert('Form saved successfully');

            },
            error: function(xhr, status, error) {
                alert('Error saving form: ' + error);

            }
        });
    });
});





