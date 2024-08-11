$(document).ready(function () {
    $('#assessmentForm').submit(function (event) {
        event.preventDefault();

        // Get form data
        let formData = {
            scoreDate: $('#scoreDate').val(),
            feedbackDate: $('#feedbackDate').val(),
            score: $('#score').val(),
            feedback: $('#feedback').val(),
            assessmentFeedbackReleased: $('#assessmentFeedbackReleased').is(":checked"),
            dateAssessmentFeedbackReleased: $('#dateAssessmentFeedbackReleased').val(),
            assessmentMarksEntered: $('#assessmentMarksEntered').is(":checked"),
            dateAssessmentMarksEntered: $('#dateAssessmentMarksEntered').val(),
            assessmentMarksTransferred: $('#assessmentMarksTransferred').is(":checked"),
            dateAssessmentMarksTransferred: $('#dateAssessmentMarksTransferred').val(),
            assessmentFeedbackEntered: $('#assessmentFeedbackEntered').is(":checked"),
            dateAssessmentFeedbackEntered: $('#dateAssessmentFeedbackEntered').val(),
            cohortFeedbackReturned: $('#cohortFeedbackReturned').is(":checked"),
            dateCohortFeedbackReturned: $('#dateCohortFeedbackReturned').val()
        };

        // Use AJAX to send data to the backend
        $.ajax({
            url: 'feedback',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (data) {
                console.log('Success:', data);
                alert('Form submitted successfully');
            },
            error: function (error) {
                console.error('Error:', error);
                alert('Error submitting form');
                // Handling error conditions
            }
        });
    });

    // Triggers an event when the button is clicked
    $('#calculateStatsBtn').click(function () {

        $.ajax({
            url: '/calculateStats',
            type: 'GET',
            success: function (data) {
                // Filling data into the dialog box
                $('#averageScore').text('Average Score: ' + data.averageScore);
                $('#minScore').text('Min Score: ' + data.minScore);
                $('#maxScore').text('Max Score: ' + data.maxScore);

                // Show dialog box
                $('#dialog').show();
            },
            error: function (error) {
                console.error('Error:', error);
                alert('Error calculating statistics');
            }
        });
    });

    // Close the dialog box when you click the close button
    $('#closeDialog').click(function () {
        $('#dialog').hide();
    });
});
